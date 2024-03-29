package com.geoxus.service.impl;

import cn.hutool.core.text.CharSequenceUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.geoxus.constant.OrdersConstant;
import com.geoxus.core.datasource.annotation.GXDataSourceAnnotation;
import com.geoxus.dto.TestDto;
import com.geoxus.entities.TestEntity;
import com.geoxus.mapper.TestMapper;
import com.geoxus.mapstruct.TestMapStruct;
import com.geoxus.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
@GXDataSourceAnnotation(OrdersConstant.DATASOURCE)
public class TestServiceImpl extends ServiceImpl<TestMapper, TestEntity> implements TestService {
    @Resource
    private TestMapStruct testMapStruct;

    @Override
    public Integer create(TestDto testDTO) {
        log.info("业务请求参数为 : {}", testDTO);
        TestEntity testEntity = testMapStruct.dtoToEntity(testDTO);
        save(testEntity);
        return testEntity.getId();
    }

    @Override
    public String callBack(String realName, String nickName) {
        final String format = CharSequenceUtil.format("你好呀~~~~ {} ~~~~ {}", realName, nickName);
        return format;
    }
}
