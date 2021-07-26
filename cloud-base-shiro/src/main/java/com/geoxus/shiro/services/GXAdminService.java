package com.geoxus.shiro.services;

import cn.hutool.core.lang.Dict;
import com.geoxus.core.common.service.GXBusinessService;
import com.geoxus.shiro.entities.GXAdminEntity;

public interface GXAdminService<T extends GXAdminEntity> extends GXBusinessService<T> {
    /**
     * 获取当前登录管理员的状态
     *
     * @param adminId 管理员ID
     * @return
     */
    Dict getStatus(long adminId);
}
