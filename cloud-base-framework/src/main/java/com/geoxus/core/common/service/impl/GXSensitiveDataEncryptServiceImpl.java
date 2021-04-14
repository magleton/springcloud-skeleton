package com.geoxus.core.common.service.impl;

import com.geoxus.core.common.annotation.GXSensitiveFieldAnnotation;
import com.geoxus.core.common.service.GXSensitiveDataEncryptService;
import com.geoxus.core.common.util.GXCommonUtils;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.Objects;

@Service
public class GXSensitiveDataEncryptServiceImpl implements GXSensitiveDataEncryptService {
    @Override
    public <T> T encrypt(Field[] declaredFields, T paramsObject) throws IllegalAccessException {
        for (Field field : declaredFields) {
            GXSensitiveFieldAnnotation sensitiveField = field.getAnnotation(GXSensitiveFieldAnnotation.class);
            if (!Objects.isNull(sensitiveField)) {
                field.setAccessible(true);
                Object object = field.get(paramsObject);
                // 只实现String类型的加密
                if (object instanceof String) {
                    String value = (String) object;
                    String s = GXCommonUtils.getAES().encryptBase64(value);
                    field.set(paramsObject, s);
                }
            }
        }
        return paramsObject;
    }
}
