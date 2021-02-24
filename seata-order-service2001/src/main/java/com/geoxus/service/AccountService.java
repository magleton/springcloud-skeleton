package com.geoxus.service;

import com.geoxus.core.common.util.GXResultUtils;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

/**
 * @author  britton
 * @since 2021-02-24
 */
@FeignClient(value = "seata-account-service")
public interface AccountService {
    @PostMapping(value = "/account/decrease")
    GXResultUtils decrease(@RequestParam("userId") Long userId, @RequestParam("money") BigDecimal money);
}
