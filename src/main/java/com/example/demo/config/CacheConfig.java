package com.example.demo.config;

import net.sf.ehcache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.URL;

/**
 * @author Li
 * @version 1.0
 * @date 2020/8/2 9:49
 */
@Configuration
public class CacheConfig {
    /**
     * 给CacheHelper用的cacheManager
     *
     * @return
     */
    @Bean(name = "ehCacheManager")
    public CacheManager cacheManager() {
        URL url = getClass().getResource("/ehcache.xml");
        CacheManager manager =
                CacheManager.create(url);
        return manager;
    }
}

