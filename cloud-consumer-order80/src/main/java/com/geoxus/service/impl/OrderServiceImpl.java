package com.geoxus.service.impl;

import cn.hutool.core.lang.Dict;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.geoxus.constant.OrdersConstant;
import com.geoxus.core.common.vo.response.GXPagination;
import com.geoxus.core.datasource.annotation.GXDataSourceAnnotation;
import com.geoxus.entities.OrdersEntity;
import com.geoxus.mapper.OrdersMapper;
import com.geoxus.service.OrderService;
import org.springframework.stereotype.Service;

/**
 * @author zj chen <britton@126.com>
 * @version 1.0
 * @since 2020-09-12
 */
@Service
@GXDataSourceAnnotation(OrdersConstant.DATASOURCE)
public class OrderServiceImpl extends ServiceImpl<OrdersMapper, OrdersEntity> implements OrderService {
    @Override
    public long create(OrdersEntity target, Dict param) {
        save(target);
        return target.getOrderId();
    }

    @Override
    public GXPagination<Dict> listOrSearchPage(Dict param) {
        return generatePage(param, Dict.create());
    }
}
