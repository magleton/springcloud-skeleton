package com.geoxus.mappers;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.math.BigDecimal;

@Mapper
public interface AccountMapper {
    /**
     * 扣减账户余额
     */
    @Update("UPDATE t_account\n" +
            "        SET residue = residue - #{money},\n" +
            "            used    = used + #{money}\n" +
            "        WHERE user_id = #{userId};")
    void decrease(@Param("userId") Long userId, @Param("money") BigDecimal money);
}
