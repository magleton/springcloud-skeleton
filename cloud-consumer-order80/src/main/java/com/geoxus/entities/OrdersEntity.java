package com.geoxus.entities;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.geoxus.constant.OrdersConstant;
import com.geoxus.core.common.annotation.GXMergeSingleFieldToJSONFieldAnnotation;
import com.geoxus.core.common.entity.GXBaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author zj chen <britton@126.com>
 * @version 1.0
 * @since 1.0
 */
@TableName(OrdersConstant.TABLE_NAME)
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class OrdersEntity extends GXBaseEntity {
    @TableId(type = IdType.AUTO)
    private Integer orderId;

    private Long orderNo;

    private Integer userId;

    private String ext;

    @TableField(exist = false)
    @GXMergeSingleFieldToJSONFieldAnnotation(dbFieldName = "price", dbJSONFieldName = "ext")
    private float price;

    @TableField(exist = false)
    @GXMergeSingleFieldToJSONFieldAnnotation(dbFieldName = "total_price", dbJSONFieldName = "ext")
    private float totalPrice;
}
