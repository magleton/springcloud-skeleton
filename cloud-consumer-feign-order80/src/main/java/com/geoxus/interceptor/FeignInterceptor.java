package com.geoxus.interceptor;

import com.geoxus.core.common.annotation.GxFeignHeaderAnnotation;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Component
@Slf4j
public class FeignInterceptor implements RequestInterceptor {
    private final String tokenHeader = "test";

    @Override
    public void apply(RequestTemplate template) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes != null) {
            HttpServletRequest request = attributes.getRequest();
            GxFeignHeaderAnnotation gxFeignHeaderAnnotation = template.methodMetadata().method().getAnnotation(GxFeignHeaderAnnotation.class);
            if (gxFeignHeaderAnnotation == null) {
                return;
            }
            headers(gxFeignHeaderAnnotation, request, template);
        }
    }

    private void headers(GxFeignHeaderAnnotation annotation, HttpServletRequest request, RequestTemplate template) {
        String[] names = annotation.names();
        if (names.length == 0) {
            return;
        }
        for (String name : names) {
            String header = request.getHeader(name);
            template.header(name, header);
        }
    }
}
