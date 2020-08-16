package com.geoxus.service;

import com.geoxus.entities.Payment;
import org.apache.ibatis.annotations.Param;

/**
 * @auther britton
 * @date 2020-02-18 10:40
 */
public interface PaymentService {
    int create(Payment payment);
    
    Payment getPaymentById(@Param("id") Long id);
}
