package com.geoxus.config;

import com.geoxus.core.common.util.TraceIdContextUtils;
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
public class FeignConfig implements RequestInterceptor {
    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    @Override
    public void apply(RequestTemplate requestTemplate) {
        requestTemplate.header(TraceIdContextUtils.TRACE_ID_KEY, TraceIdContextUtils.getTraceId());
    }
}
