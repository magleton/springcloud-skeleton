package com.geoxus.core.common.service.impl;

import com.geoxus.core.common.annotation.GXSensitiveFieldAnnotation;
import com.geoxus.core.common.service.GXSensitiveDataDecryptService;
import com.geoxus.core.common.util.GXCommonUtils;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.Objects;

@Service
public class GXSensitiveDataDecryptServiceImpl implements GXSensitiveDataDecryptService {
    @Override
    public <T> T decrypt(T result) throws IllegalAccessException {
        Class<?> resultClass = result.getClass();
        Field[] declaredFields = resultClass.getDeclaredFields();
        for (Field field : declaredFields) {
            GXSensitiveFieldAnnotation sensitiveField = field.getAnnotation(GXSensitiveFieldAnnotation.class);
            if (Objects.nonNull(sensitiveField)) {
                field.setAccessible(true);
                Object object = field.get(result);
                // 只实现对String的解密
                if (object instanceof String) {
                    String value = (String) object;
                    String s = GXCommonUtils.getAES().decryptStr(value);
                    field.set(result, s);
                }
            }
        }
        return result;
    }
}
