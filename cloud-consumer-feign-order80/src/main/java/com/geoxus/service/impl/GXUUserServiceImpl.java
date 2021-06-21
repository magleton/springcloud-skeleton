package com.geoxus.service.impl;

import cn.hutool.core.lang.Dict;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.geoxus.core.common.constant.GXTokenConstants;
import com.geoxus.entities.UserEntity;
import com.geoxus.mapper.UserMapper;
import com.geoxus.shiro.services.GXUUserService;
import org.springframework.stereotype.Service;

@Service
public class GXUUserServiceImpl extends ServiceImpl<UserMapper, UserEntity> implements GXUUserService<UserEntity> {
    @Override
    public Dict verifyUserToken(String token) {
        return Dict.create().set(GXTokenConstants.USER_ID, 1);
    }
}
