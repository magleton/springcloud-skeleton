package com.geoxus.springcloud.service.impl;

import com.geoxus.springcloud.dao.PaymentDao;
import com.geoxus.springcloud.entities.Payment;
import com.geoxus.springcloud.service.PaymentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @auther britton
 * @date 2020-02-18 10:40
 */
@Service
public class PaymentServiceImpl implements PaymentService
{
    @Resource
    private PaymentDao paymentDao;

    public int create(Payment payment)
    {
        return paymentDao.create(payment);
    }

    public Payment getPaymentById(Long id)
    {
        return paymentDao.getPaymentById(id);
    }
}
