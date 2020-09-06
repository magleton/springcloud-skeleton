package com.geoxus.constant;

import com.geoxus.core.common.annotation.GXFieldCommentAnnotation;

public class OrdersConstant {
    @GXFieldCommentAnnotation(zh = "主键ID")
    public static final String PRIMARY_KEY = "order_id";

    @GXFieldCommentAnnotation(zh = "表名")
    public static final String TABLE_NAME = "orders";

    @GXFieldCommentAnnotation(zh = "数据表别名")
    public static final String TABLE_ALIAS_NAME = "orders";

    @GXFieldCommentAnnotation("模型在数据库中的表述[一般跟数据表的名字相同]")
    public static final String MODEL_IDENTIFICATION_VALUE = "orders";

    @GXFieldCommentAnnotation("数据源")
    public static final String DATASOURCE = "orders";

    private OrdersConstant() {

    }
}
