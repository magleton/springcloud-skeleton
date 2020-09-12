package com.geoxus.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author zj chen <britton@126.com>
 * @version 0.1
 * @since 2020-02-18 17:27
 */
@Configuration
public class ApplicationContextConfig {
    @Bean
    //@LoadBalanced
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}