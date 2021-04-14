package com.geoxus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author zj chen <britton@126.com>
 * @version 0.1
 * @since 2020-09-12
 */
@SpringBootApplication
@EnableEurekaClient
@EnableAspectJAutoProxy
//@RibbonClient(name = "CLOUD-PAYMENT-SERVICE",configuration=MySelfRule.class)
public class OrderMain80 {
    public static void main(String[] args) {
        SpringApplication.run(OrderMain80.class, args);
    }
}
