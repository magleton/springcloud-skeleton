package com.geoxus.shiro.services;

import cn.hutool.core.lang.Dict;
import com.geoxus.core.common.service.GXBusinessService;
import com.geoxus.core.common.validator.GXValidateDBExists;
import com.geoxus.shiro.entities.GXUUserEntity;

public interface GXUUserService<T extends GXUUserEntity> extends GXBusinessService<T>, GXValidateDBExists {
    /**
     * 验证前端用户的Token是否有效
     *
     * @param token 用户token
     * @return Dict
     */
    default Dict verifyUserToken(String token) {
        return Dict.create();
    }
}
