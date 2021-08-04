package com.geoxus.commons.services;

import com.geoxus.commons.dto.GXDynamicCallParamAttributeDto;

import java.lang.reflect.InvocationTargetException;

public interface GXParseDynamicCallParamService {
    /**
     * 获取动态调用的方法的参数实参
     *
     * @return Object
     */
    Object getDynamicCallMethodParamValue(String jsonStr);

    /**
     * 值来自于token中指定的字段
     *
     * @param callParamDto 参数信息配置
     * @return Object
     */
    Object getValueFromToken(GXDynamicCallParamAttributeDto callParamDto);

    /**
     * 值来自于固定分配
     *
     * @param callParamDto 参数信息配置
     * @return Object
     */
    Object getValueFromAssign(GXDynamicCallParamAttributeDto callParamDto);

    /**
     * 参数的值来自于回调方法
     *
     * @param callParamDto 参数信息配置
     * @return Object
     */
    Object getValueFromCallback(GXDynamicCallParamAttributeDto callParamDto) throws InvocationTargetException, IllegalAccessException, ClassNotFoundException;
}
