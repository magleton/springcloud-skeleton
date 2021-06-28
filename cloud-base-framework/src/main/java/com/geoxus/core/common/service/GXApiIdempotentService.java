package com.geoxus.core.common.service;

import cn.hutool.core.lang.Dict;
import com.geoxus.core.common.util.GXHttpContextUtils;

import java.util.Set;

public interface GXApiIdempotentService {
    /**
     * API幂等的Token
     */
    String API_IDEMPOTENT_TOKEN = "api-token";

    /**
     * 创建Api的Token
     *
     * @param param 参数
     * @return String
     */
    default String createApiIdempotentToken(Dict param) {
        return "";
    }

    /**
     * 自定义接口幂等验证规则
     * <pre>
     * {@code
     * 1、 true 验证通过, 向下继续执行
     * 2、 false 验证失败, 不会向下继续执行
     * }
     * </pre>
     *
     * @param condition 验证条件
     * @return 验证是否通过
     */
    default boolean customApiIdempotentValidate(Object... condition) {
        return Boolean.TRUE;
    }
    
    /**
     * 批量获取header中的值
     *
     * @param headerNames 头的名字
     * @return 指定头名字的键值对
     */
    default Dict getValueByHeader(Set<String> headerNames) {
        final Dict dict = Dict.create();
        if (headerNames.isEmpty()) {
            return dict;
        }
        headerNames.forEach(key -> {
            final String header = GXHttpContextUtils.getHeader(key);
            dict.set(key, header);
        });
        return dict;
    }
}
