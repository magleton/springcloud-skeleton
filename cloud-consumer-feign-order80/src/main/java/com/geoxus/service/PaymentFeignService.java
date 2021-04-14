package com.geoxus.service;

import com.geoxus.core.common.annotation.GxFeignHeaderAnnotation;
import com.geoxus.core.common.util.GXResultUtils;
import com.geoxus.entities.CommonResult;
import com.geoxus.entities.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author britton
 * @since  2020-02-19 23:59
 */
@Component
@FeignClient(value = "CLOUD-ORDER-SERVICE")
public interface PaymentFeignService {
    @GetMapping(value = "/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id);

    @GetMapping(value = "/payment/feign/timeout")
    public String paymentFeignTimeout();

    @GetMapping(value = "/hello/frontend/index")
    @GxFeignHeaderAnnotation(names = {"test" , "test1" , "test2"})
    public GXResultUtils helloIndex(@RequestParam("name") String name);
}
