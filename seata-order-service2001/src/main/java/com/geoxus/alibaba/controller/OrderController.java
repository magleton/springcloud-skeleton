package com.geoxus.alibaba.controller;

import com.geoxus.alibaba.entities.OrderEntity;
import com.geoxus.alibaba.service.OrderService;
import com.geoxus.core.common.util.GXResultUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author  britton
 * @since 2021-02-24
 */
@RestController
public class OrderController {
    @Resource
    private OrderService orderService;

    @GetMapping("/order/create")
    public GXResultUtils create(OrderEntity orderEntity) {
        orderService.create(orderEntity);
        return GXResultUtils.ok(200, "订单创建成功");
    }
}
