package com.geoxus.shiro.dto.req;

import com.geoxus.core.common.dto.GXBaseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@EqualsAndHashCode(callSuper = true)
@Data
public class AdminLoginReqDto extends GXBaseDto {
    /**
     * 用户名
     */
    @NotNull
    @NotEmpty
    private String username;

    /**
     * 密码
     */
    @NotNull
    @NotEmpty
    private String password;
}
