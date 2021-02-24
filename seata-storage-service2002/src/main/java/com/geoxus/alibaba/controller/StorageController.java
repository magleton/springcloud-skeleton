package com.geoxus.alibaba.controller;

import com.geoxus.alibaba.service.StorageService;
import com.geoxus.core.common.util.GXResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StorageController {
    @Autowired
    private StorageService storageService;

    /**
     * 扣减库存
     */
    @RequestMapping("/storage/decrease")
    public GXResultUtils decrease(Long productId, Integer count) {
        storageService.decrease(productId, count);
        return GXResultUtils.ok(200, "扣减库存成功！");
    }
}
