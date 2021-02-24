package com.geoxus.core.datasource.config;

import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.druid.pool.DruidDataSource;
import com.geoxus.core.datasource.properties.GXDataSourceProperties;
import com.geoxus.core.datasource.properties.GXDynamicDataSourceProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

/**
 * 配置多数据源
 */
@Configuration
@Slf4j
public class GXDynamicDataSourceConfig {
    @Autowired
    private GXDynamicDataSourceProperties dynamicDataSourceProperties;

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.druid")
    public GXDataSourceProperties dataSourceProperties() {
        GXDataSourceProperties gxDataSourceProperties = new GXDataSourceProperties();
        if (!StrUtil.isEmpty(gxDataSourceProperties.getUrl())) {
            return gxDataSourceProperties;
        }
        return dynamicDataSourceProperties.getDatasource().get("framework");
    }

    @Bean
    public GXDynamicDataSource dynamicDataSource(GXDataSourceProperties dataSourceProperties) {
        GXDynamicDataSource dynamicDataSource = new GXDynamicDataSource();
        dynamicDataSource.setTargetDataSources(getDynamicDataSource());
        //默认数据源
        DruidDataSource defaultDataSource = GXDynamicDataSourceFactory.buildDruidDataSource(dataSourceProperties);
        DataSource dataSource = wrapSeataDataSource(defaultDataSource);
        dynamicDataSource.setDefaultTargetDataSource(dataSource);
        return dynamicDataSource;
    }

    /**
     * 将普通的DataSource对象包装成Seata的DataSourceProxy
     *
     * @param dataSource
     * @return
     * @author britton <britton@126.com>
     * @since 2021-02-24
     */
    private DataSource wrapSeataDataSource(DataSource dataSource) {
        try {
            Class<?> forName = Class.forName("io.seata.rm.datasource.DataSourceProxy");
            Constructor<?> constructor = ReflectUtil.getConstructor(forName, DataSource.class);
            if (constructor == null) {
                return dataSource;
            }
            Object o = ReflectUtil.newInstance(forName, dataSource);
            if (o == null) {
                return dataSource;
            }
            return (DataSource) o;
        } catch (ClassNotFoundException e) {
            log.info("类未找到");
        }
        return dataSource;
    }

    protected Map<Object, Object> getDynamicDataSource() {
        Map<String, GXDataSourceProperties> dataSourcePropertiesMap = dynamicDataSourceProperties.getDatasource();
        Map<Object, Object> targetDataSources = new HashMap<>(dataSourcePropertiesMap.size());
        // TODO 此处可以通过在其他地方获取连接信息来新建连接池,比如从另外的数据库读取信息
        dataSourcePropertiesMap.forEach((k, v) -> {
            DruidDataSource druidDataSource = GXDynamicDataSourceFactory.buildDruidDataSource(v);
            DataSource dataSource = wrapSeataDataSource(druidDataSource);
            targetDataSources.put(k, dataSource);
        });
        return targetDataSources;
    }
}
