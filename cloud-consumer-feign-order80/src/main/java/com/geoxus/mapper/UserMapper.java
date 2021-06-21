package com.geoxus.mapper;

import com.geoxus.core.common.mapper.GXBaseMapper;
import com.geoxus.entities.UserEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends GXBaseMapper<UserEntity> {
}
