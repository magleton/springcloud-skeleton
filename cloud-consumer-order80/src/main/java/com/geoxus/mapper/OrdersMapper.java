package com.geoxus.mapper;

import com.geoxus.core.common.mapper.GXBaseMapper;
import com.geoxus.entities.OrdersEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * @author zj chen <britton@126.com>
 * @version 1.0
 * @since 1.0
 */
@Mapper
@Component
public interface OrdersMapper extends GXBaseMapper<OrdersEntity> {
}
