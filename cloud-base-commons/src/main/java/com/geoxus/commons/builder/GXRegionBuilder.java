package com.geoxus.commons.builder;

import cn.hutool.core.lang.Dict;
import cn.hutool.core.text.CharSequenceUtil;
import com.geoxus.core.common.builder.GXBaseBuilder;
import com.google.common.base.Joiner;
import org.apache.ibatis.jdbc.SQL;

import java.util.ArrayList;

public class GXRegionBuilder implements GXBaseBuilder {
    @Override
    public String listOrSearch(Dict param) {
        return null;
    }

    @Override
    public String detail(Dict param) {
        return null;
    }

    @Override
    public String getModelIdentificationValue() {
        return "region";
    }

    public String areaInfo(Dict param) {
        final ArrayList<Integer> list = new ArrayList<>();
        if (null != param.getInt("province_id")) {
            list.add(param.getInt("province_id"));
        }
        if (null != param.getInt("city_id")) {
            list.add(param.getInt("city_id"));
        }
        if (null != param.getInt("area_id")) {
            list.add(param.getInt("area_id"));
        }
        final SQL sql = new SQL().SELECT("name").FROM("region").WHERE(CharSequenceUtil.format("id in ({})", Joiner.on(",").join(list)));
        return sql.toString();
    }
}