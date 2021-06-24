package com.geoxus.service.impl;

import cn.hutool.core.text.CharSequenceUtil;
import com.geoxus.core.common.service.GXSensitiveFieldDeEncryptService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class GXOrderSensitiveFieldDeEncryptServiceImpl implements GXSensitiveFieldDeEncryptService {
    /**
     * 加密数据
     *
     * @param dataStr 需要加密的数据
     * @param key     加密KEY
     * @param params  额外参数
     * @return 加密之后的数据(密文)
     */
    @Override
    public String encryptAlgorithm(String dataStr, String key, String... params) {
        return dataStr + ":AAAAAA";
    }

    /**
     * 解密数据
     *
     * @param dataStr 需要加密的数据
     * @param key     解密key
     * @param params  额外参数
     * @return 解密之后的数据(明文)
     */
    @Override
    public String decryAlgorithm(String dataStr, String key, String... params) {
        return CharSequenceUtil.replace(dataStr, ":AAAAAA", "", true);
    }
}
