package com.geoxus.builder;

import cn.hutool.core.lang.Dict;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.geoxus.constant.OrdersConstant;
import com.geoxus.core.common.builder.GXBaseBuilder;
import org.apache.ibatis.jdbc.SQL;

public class OrdersBuilder implements GXBaseBuilder {
    @Override
    public String listOrSearchPage(IPage<Dict> page, Dict param) {
        SQL sql = new SQL().FROM("orders").SELECT("*");
        mergeSearchConditionToSQL(sql, param);
        return sql.toString();
    }

    @Override
    public String listOrSearch(Dict param) {
        SQL sql = new SQL().FROM("orders").SELECT("*");
        mergeSearchConditionToSQL(sql, param);
        return sql.toString();
    }

    @Override
    public String detail(Dict param) {
        return null;
    }

    @Override
    public Dict getDefaultSearchField() {
        return Dict.create();
    }

    @Override
    public String getModelIdentificationValue() {
        return OrdersConstant.MODEL_IDENTIFICATION_VALUE;
    }
}
