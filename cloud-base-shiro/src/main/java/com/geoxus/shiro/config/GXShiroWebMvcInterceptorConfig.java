package com.geoxus.shiro.config;

import com.geoxus.core.common.config.GXWebMvcConfig;
import com.geoxus.core.common.config.GXWebMvcInterceptorConfig;
import com.geoxus.shiro.interceptor.GXAuthorizationInterceptor;
import com.geoxus.shiro.interceptor.GXLoginUserHandlerMethodArgumentResolver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

import java.util.List;

@Configuration
@Slf4j
public class GXShiroWebMvcInterceptorConfig extends GXWebMvcInterceptorConfig {
    @Autowired
    private GXAuthorizationInterceptor authorizationInterceptor;

    @Autowired
    private GXLoginUserHandlerMethodArgumentResolver loginUserHandlerMethodArgumentResolver;

    @Autowired
    private GXWebMvcConfig gxWebMvcConfig;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        final List<String> list = gxWebMvcConfig.getUrlPatterns();
        registry.addInterceptor(authorizationInterceptor).addPathPatterns(list);
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        super.addArgumentResolvers(argumentResolvers);
        argumentResolvers.add(loginUserHandlerMethodArgumentResolver);
    }
}