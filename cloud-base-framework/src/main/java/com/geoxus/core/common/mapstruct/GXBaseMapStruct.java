package com.geoxus.core.common.mapstruct;

import com.geoxus.core.common.dto.GXBaseDTO;
import com.geoxus.core.common.entity.GXBaseEntity;

/**
 * @author zj chen <britton@126.com>
 * @version 1.0
 * @since 2020-09-26
 */
public interface GXBaseMapStruct<T extends GXBaseDTO, E extends GXBaseEntity> {
    E dtoToEntity(T t);
}
