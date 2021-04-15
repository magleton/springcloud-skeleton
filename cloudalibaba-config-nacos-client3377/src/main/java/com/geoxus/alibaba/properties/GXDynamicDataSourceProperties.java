package com.geoxus.alibaba.properties;

import com.alibaba.nacos.api.annotation.NacosProperties;
import com.alibaba.nacos.api.config.annotation.NacosConfigurationProperties;
import com.geoxus.alibaba.factory.GXYamlPropertySourceFactory;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 多数据源属性
 */
@Data
@Component
@PropertySource(value = "nacos-extra.yml",
        factory = GXYamlPropertySourceFactory.class,
        ignoreResourceNotFound = false)
@NacosConfigurationProperties(dataId = "${nacos.datasource.dataId}", groupId = "${nacos.datasource.groupId}", autoRefreshed = true, properties = @NacosProperties(namespace = "${nacos.datasource.namespace}"))
@ConfigurationProperties(prefix = "dynamic")
public class GXDynamicDataSourceProperties {
    private Map<String, GXDataSourceProperties> datasource = new LinkedHashMap<>();
}
