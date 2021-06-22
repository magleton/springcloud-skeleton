package com.geoxus.nacos.properties;

import com.alibaba.nacos.api.annotation.NacosProperties;
import com.alibaba.nacos.api.config.annotation.NacosConfigurationProperties;
import com.geoxus.core.datasource.properties.GXBaseDataSourceProperties;
import com.geoxus.nacos.factory.GXYamlPropertySourceFactory;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * 多数据源属性
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Component
@PropertySource(value = "nacos-extra.yml",
        factory = GXYamlPropertySourceFactory.class,
        ignoreResourceNotFound = false)
@NacosConfigurationProperties(dataId = "${nacos.datasource.dataId}", groupId = "${nacos.datasource.groupId}", autoRefreshed = true, properties = @NacosProperties(namespace = "${nacos.datasource.namespace}"))
@ConfigurationProperties(prefix = "dynamic")
@ConditionalOnClass(name = {"com.alibaba.nacos.api.config.ConfigFactory"})
public class GXDynamicDataSourceProperties extends GXBaseDataSourceProperties {
    private String name;
}
