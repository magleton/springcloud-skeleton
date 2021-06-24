package com.geoxus.controller.frontend;

import cn.hutool.core.lang.Dict;
import cn.hutool.core.util.IdUtil;
import com.geoxus.core.common.annotation.GXRequestBodyToTargetAnnotation;
import com.geoxus.core.common.controller.GXControllerDTO;
import com.geoxus.core.common.util.GXResultUtils;
import com.geoxus.dto.OrdersDTO;
import com.geoxus.entities.OrdersEntity;
import com.geoxus.mapstruct.OrdersMapStruct;
import com.geoxus.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author zj chen <britton@126.com>
 * @version 1.0
 * @since 2020-09-12
 */
@Slf4j
@RestController("orderFrontend")
@RequestMapping("/order/frontend")
public class OrderController implements GXControllerDTO<OrdersDTO> {
    @Autowired
    private OrderService orderService;

    @Autowired
    private OrdersMapStruct ordersMapStruct;

    @Override
    @PostMapping("/create")
    //@GXLoginAnnotation
    public GXResultUtils<Long> create(@Valid @GXRequestBodyToTargetAnnotation(mapstructClazz = OrdersMapStruct.class, jsonFields = {"ext", "other"}) OrdersDTO ordersDTO) {
        long orderNo = IdUtil.getSnowflake(1, 1).nextId();
        ordersDTO.setOrderNo(orderNo);
        OrdersEntity ordersEntity = ordersMapStruct.dtoToEntity(ordersDTO);
        orderService.create(ordersEntity, Dict.create().set("author", "枫叶思源"));
        return GXResultUtils.ok(orderNo);
    }

    @Override
    @GetMapping("/list-or-search")
    public GXResultUtils<Dict> listOrSearch(Dict param) {
        return GXResultUtils.ok(param);
    }
}
