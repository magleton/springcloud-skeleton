package com.geoxus.mappers;

import com.geoxus.entities.OrderEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * @author britton
 * @since 2020-02-26 15:17
 */
@Mapper
public interface OrderMapper {
    //1 新建订单
    @Insert("insert into t_order (id, user_id, product_id, count, money, status)\n" +
            "        values (null, #{userId}, #{productId}, #{count}, #{money}, 0);")
    void create(OrderEntity order);

    //2 修改订单状态，从零改为1
    @Update("update t_order\n" +
            "        set status = 1\n" +
            "        where user_id = #{userId}\n" +
            "          and status = #{status};")
    void update(@Param("userId") Long userId, @Param("status") Integer status);
}