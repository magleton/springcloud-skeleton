package com.geoxus.nacos.config;

import com.alibaba.nacos.api.annotation.NacosProperties;
import com.alibaba.nacos.api.config.annotation.NacosConfigurationProperties;
import com.geoxus.nacos.factory.GXYamlPropertySourceFactory;
import com.geoxus.nacos.properties.GXRedissionProperties;
import lombok.Data;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;

@Data
@SuppressWarnings("all")
@Component()
@PropertySource(value = "nacos-extra.yml",
        factory = GXYamlPropertySourceFactory.class,
        ignoreResourceNotFound = false)
@NacosConfigurationProperties(dataId = "${nacos.redission.dataId}", groupId = "${nacos.redission.groupId}", autoRefreshed = true, properties = @NacosProperties(namespace = "${nacos.redission.namespace}"))
@ConditionalOnClass(name = {"org.redisson.Redisson", "com.alibaba.nacos.api.config.ConfigFactory"})
@ConfigurationProperties(prefix = "redission")
public class GXRedissionConfig {
    private Map<String, GXRedissionProperties> config = new LinkedHashMap<>();
}