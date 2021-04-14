package com.geoxus.core.common.annotation;

import java.lang.annotation.*;

/**
 * 注解敏感信息类中敏感字段的注解
 *
 * @author britton <britton@126.com>
 */
@Inherited
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface GXSensitiveFieldAnnotation {
}