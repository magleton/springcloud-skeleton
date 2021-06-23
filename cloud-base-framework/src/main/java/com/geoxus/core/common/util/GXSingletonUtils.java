package com.geoxus.core.common.util;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.lang.TypeReference;
import cn.hutool.core.text.CharSequenceUtil;
import com.github.benmanes.caffeine.cache.*;
import org.redisson.spring.cache.RedissonSpringCacheManager;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.core.io.ClassPathResource;

import java.util.Objects;

public class GXSingletonUtils {
    /**
     * 私有构造函数
     */
    private GXSingletonUtils() {

    }

    /**
     * 获取EhCacheCacheManager
     *
     * @return EhCacheCacheManager
     */
    public static EhCacheCacheManager getEhCacheCacheManager() {
        final String beanName = "a1cddb7233521d";
        EhCacheCacheManager ehCacheCacheManager = GXSpringContextUtils.getBean(EhCacheCacheManager.class);
        if (null != ehCacheCacheManager) {
            return ehCacheCacheManager;
        }
        ehCacheCacheManager = GXSpringContextUtils.getBean(beanName, EhCacheCacheManager.class);
        if (null != ehCacheCacheManager) {
            return ehCacheCacheManager;
        }
        EhCacheManagerFactoryBean ehCacheManagerFactoryBean = new EhCacheManagerFactoryBean();
        ehCacheManagerFactoryBean.setConfigLocation(new ClassPathResource("ehcache.xml"));
        ehCacheManagerFactoryBean.setShared(true);
        net.sf.ehcache.CacheManager cacheManager = ehCacheManagerFactoryBean.getObject();
        if (null == cacheManager) {
            cacheManager = new net.sf.ehcache.CacheManager();
        }
        EhCacheCacheManager manager = new EhCacheCacheManager(cacheManager);
        GXCommonUtils.registerSingleton(beanName, manager);
        return manager;
    }

    /**
     * 获取RedissonSpringCacheManager
     *
     * @return RedissonSpringCacheManager
     */
    public static RedissonSpringCacheManager getRedissonSpringCacheManager() {
        return GXSpringContextUtils.getBean(RedissonSpringCacheManager.class);
    }

    /**
     * 获取Caffeine的Cache对象
     *
     * @param configNameKey 缓存的KEY
     * @return Cache
     */
    public static <K, V> Cache<K, V> getCaffeineCache(String configNameKey) {
        final String beanName = configNameKey + "caffeine-cache";
        Cache<K, V> cache = Convert.convert(new TypeReference<Cache<K, V>>() {
        }, GXSpringContextUtils.getBean(beanName));
        if (Objects.nonNull(cache)) {
            return cache;
        }
        Caffeine<K, V> caffeine = getCaffeine(configNameKey);
        cache = caffeine.build();
        GXCommonUtils.registerSingleton(beanName, cache);
        return cache;
    }

    /**
     * 通过CacheLoader获取同步Cache对象
     *
     * @param configNameKey 缓存名字的KEY
     * @param cacheLoader   CacheLoader
     * @return LoadingCache
     */
    public static <K, V> LoadingCache<K, V> getCaffeineCache(String configNameKey, CacheLoader<K, V> cacheLoader) {
        final String beanName = configNameKey + "cache-loader-caffeine-cache";
        LoadingCache<K, V> cache = Convert.convert(new TypeReference<LoadingCache<K, V>>() {
        }, GXSpringContextUtils.getBean(beanName));
        if (Objects.nonNull(cache)) {
            return cache;
        }
        Caffeine<K, V> caffeine = getCaffeine(configNameKey);
        cache = caffeine.build(cacheLoader);
        GXCommonUtils.registerSingleton(beanName, cache);
        return cache;
    }

    /**
     * 获取异步Cache对象
     *
     * @param configNameKey 缓存名字的KEY
     * @return AsyncCache
     */
    public static <K, V> com.github.benmanes.caffeine.cache.AsyncCache<K, V> getAsyncCaffeine(String configNameKey) {
        final String beanName = configNameKey + "async-caffeine-cache";
        AsyncCache<K, V> cache = Convert.convert(new TypeReference<AsyncCache<K, V>>() {
        }, GXSpringContextUtils.getBean(beanName));
        if (Objects.nonNull(cache)) {
            return cache;
        }
        Caffeine<K, V> caffeine = getCaffeine(configNameKey);
        cache = caffeine.buildAsync();
        GXCommonUtils.registerSingleton(beanName, cache);
        return cache;
    }

    /**
     * 通过指定CacheLoader来获取指定的Cache对象
     *
     * @param cacheNameKey     缓存名字
     * @param asyncCacheLoader CacheLoader
     * @return AsyncLoadingCache
     */
    public static <K, V> com.github.benmanes.caffeine.cache.AsyncLoadingCache<K, V> getAsyncCaffeine(String cacheNameKey, AsyncCacheLoader<K, V> asyncCacheLoader) {
        final String beanName = cacheNameKey + "origin-async-cache-loader-caffeine-cache";
        AsyncLoadingCache<K, V> cache = Convert.convert(new TypeReference<AsyncLoadingCache<K, V>>() {
        }, GXSpringContextUtils.getBean(beanName));
        if (Objects.nonNull(cache)) {
            return cache;
        }
        Caffeine<K, V> caffeine = getCaffeine(cacheNameKey);
        cache = caffeine.buildAsync(asyncCacheLoader);
        GXCommonUtils.registerSingleton(beanName, cache);
        return cache;
    }

    /**
     * 获取Caffeine对象
     *
     * @param cacheNameKey 缓存名字的key
     * @return Caffeine
     */
    private static <K, V> Caffeine<K, V> getCaffeine(String cacheNameKey) {
        String spec = GXCommonUtils.getEnvironmentValue(cacheNameKey);
        if (CharSequenceUtil.isBlank(spec)) {
            spec = "maximumSize=1024";
        }
        final String beanName = spec + "caffeine-obj";
        Caffeine<K, V> caffeine = Convert.convert(new TypeReference<Caffeine<K, V>>() {
        }, GXSpringContextUtils.getBean(beanName));
        if (Objects.nonNull(caffeine)) {
            return caffeine;
        }
        caffeine = Convert.convert(new TypeReference<Caffeine<K, V>>() {
        }, Caffeine.from(spec));
        GXCommonUtils.registerSingleton(beanName, caffeine);
        return caffeine;
    }
}
