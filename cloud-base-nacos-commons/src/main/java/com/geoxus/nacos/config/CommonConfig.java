package com.geoxus.nacos.config;

import com.alibaba.nacos.api.annotation.NacosProperties;
import com.alibaba.nacos.api.config.annotation.NacosConfigurationProperties;
import com.geoxus.nacos.factory.GXYamlPropertySourceFactory;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component("commonConfig")
@PropertySource(value = "nacos-extra.yml",
        factory = GXYamlPropertySourceFactory.class,
        ignoreResourceNotFound = true)
@NacosConfigurationProperties(dataId = "${nacos.common.dataId}", groupId = "${nacos.common.groupId}", autoRefreshed = true, properties = @NacosProperties(namespace = "${nacos.common.namespace}"))
@ConfigurationProperties(prefix = "common")
@Data
public class CommonConfig {
    private String request;
}
