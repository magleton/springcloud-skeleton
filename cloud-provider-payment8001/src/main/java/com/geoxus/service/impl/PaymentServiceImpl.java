package com.geoxus.service.impl;

import com.geoxus.core.datasource.annotation.GXDataSourceAnnotation;
import com.geoxus.dao.PaymentDao;
import com.geoxus.entities.Payment;
import com.geoxus.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @auther britton
 * @date 2020-02-18 10:40
 */
@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    private PaymentDao paymentDao;

    public int create(Payment payment) {
        return paymentDao.create(payment);
    }

    @GXDataSourceAnnotation(value = "db2019")
    public Payment getPaymentById(Long id) {
        return paymentDao.getPaymentById(id);
    }
}
