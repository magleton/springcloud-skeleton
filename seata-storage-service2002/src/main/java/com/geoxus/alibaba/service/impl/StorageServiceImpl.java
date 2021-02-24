package com.geoxus.alibaba.service.impl;

import com.geoxus.alibaba.service.StorageService;
import com.geoxus.alibaba.mapper.StorageMapper;
import com.geoxus.core.common.util.GXCommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class StorageServiceImpl implements StorageService {
    private static final Logger LOGGER = GXCommonUtils.getLogger(StorageServiceImpl.class);

    @Resource
    private StorageMapper storageMapper;

    /**
     * 扣减库存
     */
    @Override
    public void decrease(Long productId, Integer count) {
        LOGGER.info("------->storage-service中扣减库存开始");
        storageMapper.decrease(productId, count);
        LOGGER.info("------->storage-service中扣减库存结束");
    }
}
