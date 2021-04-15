package com.geoxus.alibaba.service;

import com.geoxus.entities.CommonResult;
import com.geoxus.entities.Payment;
import org.springframework.stereotype.Component;

/**
 * @author britton
 * @date 2020-02-25 18:30
 */
@Component
public class PaymentFallbackService implements PaymentService {
    @Override
    public CommonResult<Payment> paymentSQL(Long id) {
        return new CommonResult<>(44444, "服务降级返回,---PaymentFallbackService", new Payment(id, "errorSerial"));
    }
}
