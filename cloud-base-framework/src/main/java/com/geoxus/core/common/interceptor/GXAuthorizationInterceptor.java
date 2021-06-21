package com.geoxus.shiro.interceptor;

import cn.hutool.core.lang.Dict;
import cn.hutool.core.text.CharSequenceUtil;
import com.geoxus.core.common.constant.GXTokenConstants;
import com.geoxus.core.common.exception.GXException;
import com.geoxus.core.common.exception.GXTokenEmptyException;
import com.geoxus.core.common.util.GXSpringContextUtils;
import com.geoxus.core.common.vo.common.GXResultCode;
import com.geoxus.shiro.annotation.GXLoginAnnotation;
import com.geoxus.shiro.services.GXUUserService;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/**
 * 前端用户Token验证
 */
@Component
@SuppressWarnings("all")
public class GXAuthorizationInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull Object handler) throws Exception {
        GXLoginAnnotation annotation;
        if (request.getMethod().equalsIgnoreCase("OPTIONS") || !(handler instanceof HandlerMethod)) {
            return true;
        }

        annotation = ((HandlerMethod) handler).getMethodAnnotation(GXLoginAnnotation.class);
        if (Objects.isNull(annotation)) {
            return true;
        }

        // 凭证为空
        if (CharSequenceUtil.isBlank(request.getHeader(GXTokenConstants.USER_TOKEN)) && CharSequenceUtil.isBlank(request.getParameter(GXTokenConstants.USER_TOKEN))) {
            throw new GXTokenEmptyException("请先进行登录", HttpStatus.UNAUTHORIZED.value());
        }

        String token = CharSequenceUtil.isBlank(request.getHeader(GXTokenConstants.USER_TOKEN)) ?
                request.getParameter(GXTokenConstants.USER_TOKEN) :
                request.getHeader(GXTokenConstants.USER_TOKEN);

        // 从数据库中获取数据
        Dict dict = Objects.requireNonNull(GXSpringContextUtils.getBean(GXUUserService.class)).verifyUserToken(token);

        if (dict == null || dict.isEmpty()) {
            throw new GXException(GXResultCode.TOKEN_TIMEOUT_EXIT.getMsg(), HttpStatus.UNAUTHORIZED.value());
        }

        // 设置userId到request里, 后续根据userId, 获取用户信息
        request.setAttribute(GXTokenConstants.USER_ID, dict.get(GXTokenConstants.USER_ID));

        return true;
    }
}
