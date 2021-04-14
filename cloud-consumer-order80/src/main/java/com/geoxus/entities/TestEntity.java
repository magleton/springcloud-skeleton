package com.geoxus.entities;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.geoxus.core.common.annotation.GXSensitiveDataAnnotation;
import com.geoxus.core.common.annotation.GXSensitiveFieldAnnotation;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@TableName("test")
@Data
@GXSensitiveDataAnnotation
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class TestEntity {
    @TableId(type = IdType.AUTO)
    private Integer id;

    @GXSensitiveFieldAnnotation
    private String content;

    @GXSensitiveFieldAnnotation
    private String test;
}
