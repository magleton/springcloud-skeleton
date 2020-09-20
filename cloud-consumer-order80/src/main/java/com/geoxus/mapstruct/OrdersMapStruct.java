package com.geoxus.mapstruct;

import com.geoxus.dto.OrdersDTO;
import com.geoxus.entities.OrdersEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrdersMapStruct {
    OrdersEntity ordersDTOToOrdersEntity(OrdersDTO dto);
}
