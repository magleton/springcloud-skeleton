package com.geoxus.commons.aspect;

import cn.hutool.core.lang.Dict;
import cn.hutool.core.text.CharSequenceUtil;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.extra.servlet.ServletUtil;
import com.geoxus.core.common.service.GXApiIdempotentService;
import com.geoxus.core.common.util.GXHttpContextUtils;
import com.geoxus.core.common.util.GXResultUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class GXApiIdempotentAspect {
    private static final String API_IDEMPOTENT_TOKEN = "api-token";

    @Resource
    private GXApiIdempotentService apiIdempotentService;

    @Pointcut("@annotation(com.geoxus.commons.annotation.GXApiIdempotentAnnotation)")
    public void apiIdempotentPointCut() {
        // 切入点表达式, 只是作为一个入口标示
    }

    @Around("apiIdempotentPointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        HttpServletRequest request = GXHttpContextUtils.getHttpServletRequest();
        assert request != null;
        final String token = ServletUtil.getHeader(request, API_IDEMPOTENT_TOKEN, CharsetUtil.UTF_8);
        if (null != token && apiIdempotentService.checkApiIdempotentToken(token)) {
            return point.proceed(point.getArgs());
        }
        Dict dict = Dict.create().set("error", CharSequenceUtil.format("{} HEADERS NOT EXISTS", API_IDEMPOTENT_TOKEN));
        return GXResultUtils.error(dict);
    }
}
