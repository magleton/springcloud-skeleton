package com.geoxus.shiro.entities;

import com.baomidou.mybatisplus.annotation.TableName;
import com.geoxus.core.common.annotation.GXFieldCommentAnnotation;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@TableName("role")
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class RoleEntity {
    @GXFieldCommentAnnotation(zhDesc = "主键ID")
    private Integer id;

    @GXFieldCommentAnnotation(zhDesc = "角色名字")
    private String roleName;

    @GXFieldCommentAnnotation(zhDesc = "扩展数据")
    private String ext;
}
