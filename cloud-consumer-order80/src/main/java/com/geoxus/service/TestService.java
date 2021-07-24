package com.geoxus.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.geoxus.constant.OrdersConstant;
import com.geoxus.core.datasource.annotation.GXDataSourceAnnotation;
import com.geoxus.dto.TestDto;
import com.geoxus.entities.TestEntity;

@GXDataSourceAnnotation(OrdersConstant.DATASOURCE)
public interface TestService extends IService<TestEntity> {
    Integer create(TestDto testDTO);

    String callBack(String realName, String nickName);
}
