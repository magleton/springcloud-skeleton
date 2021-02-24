package com.geoxus.mappers;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface StorageMapper {
    //扣减库存
    @Update(" UPDATE\n" +
            "            t_storage\n" +
            "        SET\n" +
            "            used = used + #{count},residue = residue - #{count}\n" +
            "        WHERE\n" +
            "            product_id = #{productId}")
    void decrease(@Param("productId") Long productId, @Param("count") Integer count);
}
