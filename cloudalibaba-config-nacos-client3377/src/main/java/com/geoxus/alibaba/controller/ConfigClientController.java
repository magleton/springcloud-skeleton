package com.geoxus.alibaba.controller;

import com.geoxus.core.common.util.GXCommonUtils;
import com.geoxus.core.datasource.properties.GXBaseDataSourceProperties;
import com.geoxus.nacos.config.CommonConfig;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author britton
 * @date 2020-02-23 17:02
 */
@RestController
@RefreshScope // 支持Nacos的配置动态刷新功能
public class ConfigClientController {
    @Value("${config.info}")
    private String configInfo;

    @Value("${common.request}")
    private String requestValue;

    @Resource
    private CommonConfig commonConfig;

    @Resource
    private GXBaseDataSourceProperties gxBaseDataSourceProperties;

    @Resource
    private RedissonClient redissonClient;

    @GetMapping("/config/info")
    public String getConfigInfo() {
        //redissonClient.getBucket("test-bucket").set("AAAAA");
        redissonClient.getBucket("test-bucket").set("ABC", 2, TimeUnit.MINUTES);
        GXCommonUtils.getRedissonCacheManager().getCache("testMap").putIfAbsent("MMM", "AAAMMMM");
        System.out.println(redissonClient.<String>getBucket("test-bucket").get());
        System.out.println(requestValue);
        System.out.println(commonConfig);
        System.out.println(gxBaseDataSourceProperties);
        return configInfo;
    }
}
