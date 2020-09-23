package com.example.demo.util;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ClassUtils;

/**
 * @author Li
 * @version 1.0
 * @date 2020/8/2 10:03
 */
public class EhCacheUtils {

    private static final Logger log = LoggerFactory.getLogger(EhCacheUtils.class);
    private static final String PATH = ClassUtils.getDefaultClassLoader().getResource("").getPath()+"ehcache.xml";
    private static CacheManager manager;
    private static EhCacheUtils ehCache;

    public static final String TAXI_CACHE = "taxiCache";
    public static final String GROUP_TREE_KEY = "groupTreeList";
    public static final String GROUP_VEHICLE_TREE_KEY = "groupVehicleTreeList";

    /**
     * 获得缓存配置管理
     * @param path
     */
    private EhCacheUtils(String path) {
        try {
            manager = CacheManager.create(path);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("获取配置文件错误：{}",e.getMessage());
        }
    }

    /**
     * 初始化缓存管理类
     * @return
     */
    public static EhCacheUtils getInstance() {
        try {
            if (ehCache== null) {
                ehCache= new EhCacheUtils(PATH);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("初始化错误：{}",e.getMessage());
        }
        return ehCache;
    }

    /**
     * 获取Cache类
     * @param cacheName
     * @return
     */
    public Cache getCache(String cacheName) {
        return manager.getCache(cacheName);
    }

    /**
     * 添加缓存数据
     * @param cacheName
     * @param key
     * @param value
     */
    public void put(String cacheName, String key, Object value) {
        try {
            Cache cache = manager.getCache(cacheName);
            Element element = new Element(key, value);
            cache.put(element);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("添加缓存失败：{}",e.getMessage());
        }
    }

    /**
     * 获取缓存数据
     * @param cacheName
     * @param key
     * @return
     */
    public Object get(String cacheName, String key) {
        try {
            Cache cache = manager.getCache(cacheName);
            Element element = cache.get(key);
            return element == null ? null : element.getObjectValue();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("获取缓存数据失败：{}",e.getMessage());
            return null;
        }
    }

    /**
     * 删除缓存数据
     * @param cacheName
     * @param key
     */
    public void delete(String cacheName, String key) {
        try {
            Cache cache = manager.getCache(cacheName);
            cache.remove(key);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("删除缓存数据失败：{}",e.getMessage());
        }
    }
    /**
     * 初始化缓存管理类
     * @return
     */
    public static CacheManager getCacheInstance() {
        try {
            if (manager== null) {
                manager= (CacheManager)ApplicationContextUtils.getBean("ehCacheManager");
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("初始化错误：{}",e.getMessage());
        }
        return manager;
    }

}

