package com.geoxus.controller;

import com.geoxus.core.common.util.GXResultUtils;
import com.geoxus.entities.OrderEntity;
import com.geoxus.service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author britton
 * @since 2021-02-24
 */
@RestController
public class OrderController {
    @Resource
    private OrderService orderService;

    @GetMapping("/order/create")
    public GXResultUtils<String> create(OrderEntity orderEntity) {
        orderService.create(orderEntity);
        return GXResultUtils.ok("订单创建成功");
    }
}
