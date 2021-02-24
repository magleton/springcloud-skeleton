package com.geoxus.alibaba.service;

import com.geoxus.alibaba.entities.OrderEntity;

/**
 * @author britton
 * @since 2021-02-24
 */
public interface OrderService {
    void create(OrderEntity order);
}
