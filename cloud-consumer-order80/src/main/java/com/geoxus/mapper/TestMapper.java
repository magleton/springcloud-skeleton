package com.geoxus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.geoxus.entities.TestEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * @author zj chen <britton@126.com>
 * @version 1.0
 * @since 1.0
 */
@Mapper
@Component
public interface TestMapper extends BaseMapper<TestEntity> {
}
