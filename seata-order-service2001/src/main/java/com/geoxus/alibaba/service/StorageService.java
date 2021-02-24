package com.geoxus.alibaba.service;

import com.geoxus.core.common.util.GXResultUtils;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author  britton
 * @since 2021-02-24
 */
@FeignClient(value = "seata-storage-service")
public interface StorageService {
    @PostMapping(value = "/storage/decrease")
    GXResultUtils decrease(@RequestParam("productId") Long productId, @RequestParam("count") Integer count);
}
