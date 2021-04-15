package com.geoxus.core.common.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.spring.cache.RedissonSpringCacheManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.Resource;

import java.io.IOException;

@Configuration
@ConditionalOnClass(name = {"org.redisson.Redisson"})
public class GXRedissonSpringDataConfig {
    @Bean(destroyMethod = "shutdown")
    @Profile("local")
    public RedissonClient redissionClient(@Value("classpath:/redission-local.yml") Resource configFile) throws IOException {
        Config config = Config.fromYAML(configFile.getInputStream());
        return Redisson.create(config);
    }

    @Bean(destroyMethod = "shutdown")
    @Profile("dev")
    public RedissonClient redission(@Value("classpath:/redission.yml") Resource configFile) throws IOException {
        Config config = Config.fromYAML(configFile.getInputStream());
        return Redisson.create(config);
    }

    @Bean
    @ConditionalOnExpression("'${spring.cache.type}'.equals('redis')")
    public CacheManager cacheManager(RedissonClient redissonClient) {
        return new RedissonSpringCacheManager(redissonClient, "classpath:/redission-cache-config.yml");
    }
}