package com.geoxus.shiro.services.impl;

import cn.hutool.core.lang.Dict;
import com.geoxus.core.common.util.GXCommonUtils;
import com.geoxus.core.common.util.GXSpringContextUtils;
import com.geoxus.shiro.services.GXSAdminHasRolesService;
import com.geoxus.shiro.services.GXSAdminService;
import com.geoxus.shiro.services.GXSPermissionsService;
import com.geoxus.shiro.services.GXShiroService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Set;

@Service
@Slf4j
public class GXShiroServiceImpl implements GXShiroService {
    /**
     * 获取用户权限列表
     *
     * @param adminId 管理员ID
     */
    @SuppressWarnings(value = {"unchecked"})
    public Set<String> getAdminAllPermissions(Long adminId) {
        return Objects.requireNonNull(GXSpringContextUtils.getBean(GXSPermissionsService.class)).getAdminAllPermissions(adminId);
    }

    /**
     * 获取用户角色列表
     *
     * @return
     */
    public Dict getAdminRoles(long adminId) {
        return Objects.requireNonNull(GXSpringContextUtils.getBean(GXSAdminHasRolesService.class)).getAdminRoles(adminId);
    }

    @Override
    public Dict getAdminById(Long adminId) {
        final Dict dict = Objects.requireNonNull(GXSpringContextUtils.getBean(GXSAdminService.class)).getStatus(adminId);
        if (null == dict) {
            return Dict.create();
        }
        return dict;
    }

    @Override
    public boolean isSuperAdmin(Dict adminData) {
        final String primaryKey = Objects.requireNonNull(GXSpringContextUtils.getBean(GXSAdminService.class)).getPrimaryKey();
        if (null != adminData.getLong(primaryKey)) {
            return adminData.getLong(primaryKey).equals(GXCommonUtils.getEnvironmentValue("super.admin.id", Long.class));
        }
        return adminData.getLong("is_super_admin") == 1;
    }

    @Override
    public Long currentSessionUserId() {
        return 0L;
    }

    @Override
    public Dict adminRoles(Long currentAdminId) {
        return Dict.create();
    }
}
