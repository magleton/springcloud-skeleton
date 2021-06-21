package com.geoxus.alibaba.config;

import cn.hutool.json.JSONUtil;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.spring.cache.CacheConfig;
import org.redisson.spring.cache.RedissonSpringCacheManager;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.util.Map;

@Configuration
@ConditionalOnClass(name = {"org.redisson.Redisson", "com.alibaba.nacos.api.config.ConfigFactory"})
public class GXRedissonSpringDataConfig {
    @Resource
    private GXRedissionConfig gxRedissionConfig;

    @Resource
    private GXRedissionCacheManagerConfig gxRedissionCacheManagerConfig;

    @Bean(destroyMethod = "shutdown")
    public RedissonClient redission() {
        final Config config = JSONUtil.toBean(JSONUtil.toJsonStr(gxRedissionConfig.getConfig()), Config.class);
        return Redisson.create(config);
    }

    @Bean
    @ConditionalOnExpression("'${spring.cache.type}'.equals('redis')")
    public CacheManager cacheManager(RedissonClient redission) {
        final Map<String, CacheConfig> config = gxRedissionCacheManagerConfig.getConfig();
        if (config.isEmpty()) {
            return new RedissonSpringCacheManager(redission);
        }
        return new RedissonSpringCacheManager(redission, config);
    }
}