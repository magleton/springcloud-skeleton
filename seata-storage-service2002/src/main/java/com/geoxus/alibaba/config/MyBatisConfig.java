package com.geoxus.alibaba.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @auther britton
 * @date 2019-12-11 16:57
 */
@Configuration
@MapperScan({"com.geoxus.springcloud.alibaba.dao"})
public class MyBatisConfig {
}
