package com.geoxus.nacos.config;

import com.alibaba.nacos.api.annotation.NacosProperties;
import com.alibaba.nacos.api.config.annotation.NacosConfigurationProperties;
import com.geoxus.nacos.factory.GXYamlPropertySourceFactory;
import lombok.Data;
import org.redisson.spring.cache.CacheConfig;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;

@Data
@Configuration
@Component("gxRedissionCacheManagerConfig")
@PropertySource(value = "nacos-extra.yml",
        factory = GXYamlPropertySourceFactory.class,
        ignoreResourceNotFound = false)
@NacosConfigurationProperties(dataId = "${nacos.redissionCacheConfig.dataId}", groupId = "${nacos.redissionCacheConfig.groupId}", autoRefreshed = true, properties = @NacosProperties(namespace = "${nacos.redissionCacheConfig.namespace}"))
@ConditionalOnClass(name = {"org.redisson.Redisson", "com.alibaba.nacos.api.config.ConfigFactory"})
@ConfigurationProperties(prefix = "redission")
public class GXRedissionCacheManagerConfig {
    private Map<String, CacheConfig> config = new LinkedHashMap<>();
}
