package com.geoxus.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @auther britton
 * @date 2020-07-28 20:56
 */
@SpringBootApplication
@EnableZuulProxy
public class ZuulGatewayMain9528 {
    public static void main(String[] args) {
        SpringApplication.run(ZuulGatewayMain9528.class, args);
    }
}
