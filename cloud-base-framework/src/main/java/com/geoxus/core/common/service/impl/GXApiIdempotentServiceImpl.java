package com.geoxus.core.common.service.impl;

import cn.hutool.core.lang.Dict;
import cn.hutool.core.text.CharSequenceUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.RandomUtil;
import com.geoxus.core.common.annotation.GXFieldCommentAnnotation;
import com.geoxus.core.common.exception.GXException;
import com.geoxus.core.common.service.GXApiIdempotentService;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
public class GXApiIdempotentServiceImpl implements GXApiIdempotentService {
    @GXFieldCommentAnnotation(zh = "Guava缓存组件")
    private static final Cache<String, String> API_IDEMPOTENT_CACHE;

    static {
        API_IDEMPOTENT_CACHE = CacheBuilder.newBuilder().maximumSize(10000).expireAfterWrite(Duration.ofSeconds(300L)).build();
    }

    @Override
    public String createApiIdempotentToken(Dict param) {
        final String token = getTokenValue(RandomUtil.randomString(32));
        API_IDEMPOTENT_CACHE.put(token, "1");
        return token;
    }

    @Override
    public boolean checkApiIdempotentToken(String token) {
        final String s = API_IDEMPOTENT_CACHE.getIfPresent(token);
        if (CharSequenceUtil.isBlank(s)) {
            throw new GXException("API TOKEN不能为空");
        }
        API_IDEMPOTENT_CACHE.invalidate(token);
        return true;
    }

    private String getTokenValue(String initStr) {
        final String s = IdUtil.randomUUID() + initStr;
        return RandomUtil.randomString(s, 32);
    }
}
