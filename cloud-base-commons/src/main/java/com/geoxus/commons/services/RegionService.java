package com.geoxus.commons.services;

import cn.hutool.core.lang.Dict;
import com.geoxus.commons.entities.RegionEntity;
import com.geoxus.core.framework.service.GXBaseService;

import java.util.List;

public interface RegionService extends GXBaseService<RegionEntity> {
    /**
     * 获取所有区域树
     *
     * @return
     */
    List<RegionEntity> getRegionTree();

    /**
     * 通过条件获取区域
     *
     * @param param
     * @return
     */
    List<RegionEntity> getRegion(Dict param);

    /**
     * 转换名字到拼音
     *
     * @return
     */
    boolean convertNameToPinYin();
}