package com.geoxus.dao;

import com.geoxus.entities.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
 * @auther britton
 * @date 2020-02-18 10:27
 */
@Mapper
@Component
public interface PaymentDao {
    int create(Payment payment);

    Payment getPaymentById(@Param("id") Long id);
}
