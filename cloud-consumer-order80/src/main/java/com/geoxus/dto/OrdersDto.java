package com.geoxus.dto;

import com.geoxus.core.common.annotation.GXMergeSingleFieldToJSONFieldAnnotation;
import com.geoxus.core.common.dto.GXBaseDto;
import lombok.Data;

/**
 * 订单的DTO
 *
 * @author britton <britton@126.com>
 * @since 1.0
 */
@Data
public class OrdersDto extends GXBaseDto {
    private Integer orderId;

    private Long orderNo;

    private Integer userId;

    private String ext;

    private String other;

    @GXMergeSingleFieldToJSONFieldAnnotation(dbFieldName = "price", dbJSONFieldName = "ext")
    private Float price;

    @GXMergeSingleFieldToJSONFieldAnnotation(dbFieldName = "total_price", dbJSONFieldName = "ext")
    private Float totalPrice;

    @GXMergeSingleFieldToJSONFieldAnnotation(dbFieldName = "province", dbJSONFieldName = "other")
    private Integer province;

    @GXMergeSingleFieldToJSONFieldAnnotation(dbFieldName = "city", dbJSONFieldName = "other")
    private Integer city;
}
