package com.geoxus.service.impl;

import com.geoxus.entities.TestEntity;
import com.geoxus.service.HelloService;
import org.springframework.stereotype.Service;

@Service
public class HelloServiceImpl implements HelloService {
    @Override
    public String hello(String name, int age, TestEntity testEntity) {
        return "我的名字叫hello >>> " + name + " , 年龄 >>>> " + age;
    }
}
