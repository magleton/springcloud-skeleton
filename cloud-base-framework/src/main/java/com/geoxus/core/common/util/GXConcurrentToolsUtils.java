package com.geoxus.core.common.util;

import cn.hutool.core.thread.ExecutorBuilder;
import cn.hutool.core.thread.ThreadFactoryBuilder;
import org.slf4j.Logger;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.function.Supplier;

/**
 * 并发请求任务工具类
 */
public class GXConcurrentToolsUtils {
    /**
     * 日志对象
     */
    private static final Logger LOG = GXCommonUtils.getLogger(GXConcurrentToolsUtils.class);

    /**
     * 线程池对象
     */
    private static final ExecutorService EXECUTOR_SERVICE = ExecutorBuilder.create()
            .setCorePoolSize(Runtime.getRuntime().availableProcessors())
            .setMaxPoolSize(Runtime.getRuntime().availableProcessors() * 2)
            .setThreadFactory(ThreadFactoryBuilder.create().setNamePrefix("gx-concurrent-thread-pool").build())
            .setWorkQueue(new LinkedBlockingQueue<>(10000))
            .build();

    /**
     * 私有构造函数
     */
    private GXConcurrentToolsUtils() {

    }

    /**
     * 将任务对象包装为一个CompletableFuture对象
     *
     * @param callable  任务对象
     * @param results   承载计算结果的容器
     * @param resultKey 结果关联的KEY
     * @param <T>       泛型类名
     * @return T
     */
    public static <T> CompletableFuture<T> composerFuture(Supplier<T> callable, ConcurrentMap<String, Object> results, String resultKey) {
        final CompletableFuture<T> future = CompletableFuture.supplyAsync(callable, EXECUTOR_SERVICE);
        future.whenComplete((t, u) -> results.put(resultKey, t));
        future.handleAsync((t, ex) -> {
            if (Objects.nonNull(ex)) {
                LOG.error("发生了异常 {} , 异常堆栈信息为 : {} ", ex.getMessage(), ex);
            } else {
                LOG.error("异常信息为null");
            }
            return t;
        });
        future.exceptionally(ex -> {
            results.put(resultKey, null);
            future.completeExceptionally(ex);
            return null;
        });
        return future;
    }

    /**
     * @param completableFutures CompletableFuture对象列表
     * @return CompletableFuture对象
     */
    public static CompletableFuture<Void> allOf(List<CompletableFuture<?>> completableFutures) {
        return CompletableFuture.allOf(completableFutures.stream().toArray(value -> new CompletableFuture[completableFutures.size()]));
    }

    /**
     * @param completableFutures CompletableFuture对象列表
     * @return CompletableFuture对象
     */
    public static CompletableFuture<Object> anyOf(List<CompletableFuture<?>> completableFutures) {
        return CompletableFuture.anyOf(completableFutures.stream().toArray(value -> new CompletableFuture[completableFutures.size()]));
    }

    /**
     * 获取结果
     *
     * @param all CompletableFuture对象
     */
    public static void getResults(CompletableFuture<Void> all) {
        try {
            all.join();
        } catch (Exception e) {
            LOG.error("发生了异常 {} , 异常信息为 {}", e.getMessage(), e);
        }
    }
}
