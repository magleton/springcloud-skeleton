package com.geoxus.core.common.config;

import com.geoxus.core.common.filter.TraceIdRequestLoggingFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TraceIdRequestLoggingConfig {
    @Bean
    public TraceIdRequestLoggingFilter traceIdRequestLoggingFilter() {
        return new TraceIdRequestLoggingFilter();
    }
}