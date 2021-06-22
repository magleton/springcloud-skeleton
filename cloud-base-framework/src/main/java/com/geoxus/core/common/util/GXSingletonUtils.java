package com.geoxus.core.common.util;

import org.redisson.spring.cache.RedissonSpringCacheManager;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.core.io.ClassPathResource;

public class GXSingletonUtils {
    /**
     * 私有构造函数
     */
    private GXSingletonUtils() {

    }

    /**
     * 获取EhCacheCacheManager
     *
     * @return EhCacheCacheManager
     */
    public static EhCacheCacheManager getEhCacheCacheManager() {
        final String beanName = "a1cddb7233521d";
        EhCacheCacheManager ehCacheCacheManager = GXSpringContextUtils.getBean(EhCacheCacheManager.class);
        if (null != ehCacheCacheManager) {
            return ehCacheCacheManager;
        }
        ehCacheCacheManager = GXSpringContextUtils.getBean(beanName, EhCacheCacheManager.class);
        if (null != ehCacheCacheManager) {
            return ehCacheCacheManager;
        }
        EhCacheManagerFactoryBean ehCacheManagerFactoryBean = new EhCacheManagerFactoryBean();
        ehCacheManagerFactoryBean.setConfigLocation(new ClassPathResource("ehcache.xml"));
        ehCacheManagerFactoryBean.setShared(true);
        net.sf.ehcache.CacheManager cacheManager = ehCacheManagerFactoryBean.getObject();
        if (null == cacheManager) {
            cacheManager = new net.sf.ehcache.CacheManager();
        }
        EhCacheCacheManager manager = new EhCacheCacheManager(cacheManager);
        GXCommonUtils.registerSingleton(beanName, manager);
        return manager;
    }

    /**
     * 获取RedissonSpringCacheManager
     *
     * @return RedissonSpringCacheManager
     */
    public static RedissonSpringCacheManager getRedissonSpringCacheManager() {
        return GXSpringContextUtils.getBean(RedissonSpringCacheManager.class);
    }
}
