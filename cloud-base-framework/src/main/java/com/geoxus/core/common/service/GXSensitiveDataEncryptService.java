package com.geoxus.core.common.service;

import java.lang.reflect.Field;

public interface GXSensitiveDataEncryptService {
    /**
     * 加密
     *
     * @param declaredFields paramsObject所声明的字段
     * @param paramsObject   mapper中paramsType的实例
     * @return T
     * @throws IllegalAccessException 字段不可访问异常
     */
    <T> T encrypt(Field[] declaredFields, T paramsObject) throws IllegalAccessException;
}
