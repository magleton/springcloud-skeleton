package com.geoxus.springcloud.service;

import com.geoxus.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Param;

/**
 * @auther britton
 * @date 2020-02-18 10:40
 */
public interface PaymentService {
    public int create(Payment payment);

    public Payment getPaymentById(@Param("id") Long id);
}
