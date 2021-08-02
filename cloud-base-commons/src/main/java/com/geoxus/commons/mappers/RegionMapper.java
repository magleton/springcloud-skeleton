package com.geoxus.commons.mappers;

import cn.hutool.core.lang.Dict;
import com.geoxus.commons.builder.RegionBuilder;
import com.geoxus.commons.entities.RegionEntity;
import com.geoxus.core.common.mapper.GXBaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;

@Mapper
public interface RegionMapper extends GXBaseMapper<RegionEntity> {
    @SelectProvider(type = RegionBuilder.class, method = "areaInfo")
    Dict areaInfo(Dict param);
}