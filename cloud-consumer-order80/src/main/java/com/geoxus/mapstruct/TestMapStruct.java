package com.geoxus.mapstruct;

import com.geoxus.core.common.mapstruct.GXBaseMapStruct;
import com.geoxus.dto.TestDTO;
import com.geoxus.entities.TestEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TestMapStruct extends GXBaseMapStruct<TestDTO, TestEntity> {
}
