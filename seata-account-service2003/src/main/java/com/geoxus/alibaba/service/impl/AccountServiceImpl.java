package com.geoxus.alibaba.service.impl;


import com.geoxus.alibaba.mapper.AccountMapper;
import com.geoxus.alibaba.service.AccountService;
import com.geoxus.core.common.util.GXCommonUtils;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

/**
 * 账户业务实现类
 *
 * @author britton
 */
@Service
public class AccountServiceImpl implements AccountService {
    private static final Logger LOGGER = GXCommonUtils.getLogger(AccountServiceImpl.class);

    @Resource
    AccountMapper accountMapper;

    /**
     * 扣减账户余额
     */
    @Override
    public void decrease(Long userId, BigDecimal money) {
        LOGGER.info("------->account-service中扣减账户余额开始");
        //模拟超时异常，全局事务回滚
        //暂停几秒钟线程
        try {
            TimeUnit.SECONDS.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        accountMapper.decrease(userId, money);
        LOGGER.info("------->account-service中扣减账户余额结束");
    }
}
