package com.geoxus.config;

import com.geoxus.core.common.util.GXTraceIdContextUtils;
import feign.Logger;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author britton
 * @date 2020-02-20 9:40
 */
@Configuration
public class GXFeignConfig implements RequestInterceptor {
    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    @Override
    public void apply(RequestTemplate requestTemplate) {
        requestTemplate.header(GXTraceIdContextUtils.TRACE_ID_KEY, GXTraceIdContextUtils.getTraceId());
    }
}
