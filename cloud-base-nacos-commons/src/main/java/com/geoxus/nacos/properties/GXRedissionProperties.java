package com.geoxus.nacos.properties;

import lombok.Data;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.stereotype.Component;

@Data
@Component
@ConditionalOnClass(name = {"com.alibaba.nacos.api.config.ConfigFactory"})
public class GXRedissionProperties {
    /**
     * 链接地址
     */
    private String address;

    /**
     * 密码
     */
    private String password;

    /**
     * 数据库
     */
    private Integer database;
}
