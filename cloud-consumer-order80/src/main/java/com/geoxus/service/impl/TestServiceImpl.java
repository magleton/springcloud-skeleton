package com.geoxus.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.geoxus.constant.OrdersConstant;
import com.geoxus.core.datasource.annotation.GXDataSourceAnnotation;
import com.geoxus.entities.TestEntity;
import com.geoxus.mapper.TestMapper;
import com.geoxus.service.TestService;
import org.springframework.stereotype.Service;

@Service
@GXDataSourceAnnotation(OrdersConstant.DATASOURCE)
public class TestServiceImpl extends ServiceImpl<TestMapper, TestEntity> implements TestService {
}
