package com.geoxus.alibaba.mapper;

import com.geoxus.alibaba.entities.OrderEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @auther britton
 * @date 2020-02-26 15:17
 */
@Mapper
public interface OrderMapper {
    //1 新建订单
    void create(OrderEntity order);

    //2 修改订单状态，从零改为1
    void update(@Param("userId") Long userId, @Param("status") Integer status);
}