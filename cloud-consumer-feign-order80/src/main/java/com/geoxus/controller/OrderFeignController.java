package com.geoxus.controller;

import com.geoxus.core.common.util.GXResultUtils;
import com.geoxus.entities.CommonResult;
import com.geoxus.entities.Payment;
import com.geoxus.service.PaymentFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author britton
 * @date 2020-02-20 0:05
 */
@RestController
@Slf4j
public class OrderFeignController {
    @Resource
    private PaymentFeignService paymentFeignService;

    @GetMapping(value = "/consumer/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id) {
        return paymentFeignService.getPaymentById(id);
    }

    @GetMapping(value = "/consumer/payment/feign/timeout")
    public String paymentFeignTimeout() {
        // OpenFeign客户端一般默认等待1秒钟
        return paymentFeignService.paymentFeignTimeout();
    }

    @GetMapping(value = "/feign/hello/index")
    public GXResultUtils<String> helloIndex() {
        return paymentFeignService.helloIndex("britton");
    }
}
