package com.geoxus.controller;

import com.geoxus.core.common.util.GXResultUtils;
import com.geoxus.service.StorageService;
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
    public GXResultUtils<String> decrease(Long productId, Integer count) {
        storageService.decrease(productId, count);
        return GXResultUtils.ok("扣减库存成功！");
    }
}
