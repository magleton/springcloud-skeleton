package com.geoxus.core.common.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class GXBaseSearchReqDto extends GXBaseDto {
    /**
     * 分页信息
     */
    protected GXBasePagingInfoReqDto pagingInfo;

    /**
     * 搜索条件
     */
    protected GXBaseSearchConditionReqDto searchCondition;

    /**
     * 需要移除的字段
     * ext::username,ext::password
     */
    protected String removeField;
}
