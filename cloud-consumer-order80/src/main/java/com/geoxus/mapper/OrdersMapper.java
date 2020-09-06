package com.geoxus.mapper;

import cn.hutool.core.lang.Dict;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.geoxus.builder.OrdersBuilder;
import com.geoxus.core.common.mapper.GXBaseMapper;
import com.geoxus.entities.OrdersEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author zj chen <britton@126.com>
 * @version 1.0
 * @since 1.0
 */
@Mapper
@Component
public interface OrdersMapper extends GXBaseMapper<OrdersEntity> {
    @Override
    @SelectProvider(type = OrdersBuilder.class, method = "listOrSearchPage")
    List<Dict> listOrSearchPage(IPage<Dict> page, Dict param);
}
