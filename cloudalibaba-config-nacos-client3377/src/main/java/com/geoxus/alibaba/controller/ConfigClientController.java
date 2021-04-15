package com.geoxus.alibaba.controller;

import com.geoxus.alibaba.config.CommonConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author britton
 * @date 2020-02-23 17:02
 */
@RestController
@RefreshScope // 支持Nacos的配置动态刷新功能
public class ConfigClientController {
    @Value("${config.info}")
    private String configInfo;

    @Value("${common.request}")
    private String requestValue;

    @Resource
    private CommonConfig commonConfig;

    @GetMapping("/config/info")
    public String getConfigInfo() {
        System.out.println(requestValue);
        System.out.println(commonConfig);
        return configInfo;
    }
}
