package com.geoxus.controller.frontend;

import cn.hutool.core.lang.Dict;
import cn.hutool.core.util.IdUtil;
import cn.hutool.http.HttpStatus;
import com.geoxus.core.common.annotation.GXRequestBodyToEntityAnnotation;
import com.geoxus.core.common.controller.GXController;
import com.geoxus.core.common.util.GXResultUtils;
import com.geoxus.entities.OrdersEntity;
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
public class OrderController implements GXController<OrdersEntity> {
    @Autowired
    private OrderService orderService;

    @Override
    @PostMapping("/create")
    //@GXLoginAnnotation
    public GXResultUtils create(@Valid @GXRequestBodyToEntityAnnotation(jsonFields = {"ext", "other"}) OrdersEntity order) {
        long orderNo = IdUtil.getSnowflake(1, 1).nextId();
        order.setOrderNo(orderNo);
        orderService.create(order, Dict.create().set("author", "枫叶思源"));
        return GXResultUtils.ok(HttpStatus.HTTP_OK).addKeyValue("order_no", orderNo);
    }

    @Override
    @GetMapping("/list-or-search")
    public GXResultUtils listOrSearch(Dict param) {
        return GXResultUtils.ok().putData(orderService.listOrSearchPage(param));
    }
}
