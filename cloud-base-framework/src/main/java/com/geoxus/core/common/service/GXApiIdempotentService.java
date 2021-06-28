package com.geoxus.core.common.service;

import cn.hutool.core.lang.Dict;

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
    default boolean customApiIdempotentValidate(Dict condition) {
        return Boolean.TRUE;
    }
}
