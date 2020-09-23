package com.example.demo.controller;

import com.example.demo.dmo.User;
import com.example.demo.service.UserService;
import com.example.demo.util.EhCacheUtils;
import com.example.demo.util.Result;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Li
 * @version 1.0
 * @date 2020/8/1 14:28
 */
@RestController
@RequestMapping("user")
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @RequestMapping("/findById/{id}")
    public Result findById(@PathVariable int id) {
        Result result = userService.findById(id);
        return result;
    }

    @RequestMapping("/findAll")
    public Result findAll() {
        Result userList = userService.findAll();
        return userList;
    }

    @PostMapping("/insert")
    public Result insert(@RequestBody User user) {
        Result result = userService.insert(user);
        return result;
    }

    @RequestMapping("/delete")
    public Result delete(@RequestBody User user) {
        Result result = userService.delete(user);
        return result;
    }

    @PostMapping("/update")
    public Result update(@RequestBody User user) {
        Result result = userService.update(user);
        printCache();
        return result;
    }
    public void printCache(){
        CacheManager cacheManager = EhCacheUtils.getCacheInstance();
        Cache cache = cacheManager.getCache("users");
        for (Object key:cache.getKeys()) {
            Element element = cache.get(key);
            System.out.println(element.getObjectValue());
        }
    }
}
