package com.geoxus.commons.controller;

import cn.hutool.core.lang.Dict;
import com.geoxus.commons.entities.RegionEntity;
import com.geoxus.commons.services.RegionService;
import com.geoxus.core.common.util.GXResultUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/generate/region")
public class RegionController {
    @Resource
    private RegionService regionService;

    /**
     * 获取所有区域树
     *
     * @return GXResultUtils
     */
    @GetMapping("/get-region-tree")
    public GXResultUtils<List<RegionEntity>> getRegionTree() {
        List<RegionEntity> list = regionService.getRegionTree();
        return GXResultUtils.ok(list);
    }

    /**
     * 通过条件获取区域
     *
     * @param param
     * @return GXResultUtils
     */
    @PostMapping("/get-region")
    public GXResultUtils<List<RegionEntity>> getRegion(@RequestBody Dict param) {
        List<RegionEntity> list = regionService.getRegion(param);
        return GXResultUtils.ok(list);
    }
}