package com.geoxus.core.common.filter;

import cn.hutool.core.text.CharSequenceUtil;
import com.geoxus.core.common.util.TraceIdContextUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.filter.AbstractRequestLoggingFilter;

import javax.servlet.http.HttpServletRequest;

public class TraceIdRequestLoggingFilter extends AbstractRequestLoggingFilter {
    @Override
    protected void beforeRequest(HttpServletRequest request, @NotNull String message) {
        String requestId = request.getHeader(TraceIdContextUtils.TRACE_ID_KEY);
        if (CharSequenceUtil.isNotEmpty(requestId)) {
            TraceIdContextUtils.setTraceId(requestId);
        } else {
            TraceIdContextUtils.setTraceId(TraceIdContextUtils.TraceIdGenerator.getTraceId());
        }
    }

    @Override
    protected void afterRequest(@NotNull HttpServletRequest request, @NotNull String message) {
        TraceIdContextUtils.removeTraceId();
    }
}
