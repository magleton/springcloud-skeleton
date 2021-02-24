package com.geoxus.service;

import com.geoxus.entities.OrderEntity;

/**
 * @author britton
 * @since 2021-02-24
 */
public interface OrderService {
    void create(OrderEntity order);
}
