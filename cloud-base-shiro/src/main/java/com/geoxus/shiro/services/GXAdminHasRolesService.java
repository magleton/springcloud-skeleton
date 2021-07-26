package com.geoxus.shiro.services;

import cn.hutool.core.lang.Dict;
import com.geoxus.core.common.service.GXBusinessService;
import com.geoxus.shiro.entities.GXAdminRolesEntity;

public interface GXAdminHasRolesService<T extends GXAdminRolesEntity> extends GXBusinessService<T> {
    /**
     * 获取当前人的角色列表
     *
     * @param adminId 为NULL是获取当前登录人的
     * @return
     */
    default Dict getAdminRoles(long adminId) {
        return Dict.create();
    }
}
