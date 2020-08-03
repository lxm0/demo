package com.example.demo.Service.Impl;

import com.example.demo.DO.User;
import com.example.demo.Mapper.UserMapper;
import com.example.demo.Service.UserService;
import com.example.demo.Util.Result;
import org.springframework.beans.factory.Aware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Li
 * @version 1.0
 * @date 2020/8/1 14:33
 */
@Service
@CacheConfig(cacheNames="users")
public class UserServiceImpl implements UserService ,Aware{

    @Autowired
    private UserMapper userMapper;

    @Override
    @Cacheable(cacheNames="users",key = "#id")
    public Result findById(int id) {
        User user = userMapper.selectByPrimaryKey(id);
        return Result.success(user);
    }

    @Override
    public Result findAll() {
        List<User>users = userMapper.selectAll();
        return Result.success(users);
    }

    @Override
    @CachePut(key="#user.id")
    public Result insert(User user) {
        int res = userMapper.insertSelective(user);
        User user1 = userMapper.selectByPrimaryKey(user.getId());
        if (res >0){
            return Result.success(user1);
        }else {
            return Result.fail();
        }
    }

    @Override
    @CachePut(key="#user.id")
    public Result update(User user) {
        int res = userMapper.updateByPrimaryKey(user);
        User user1 = userMapper.selectByPrimaryKey(user.getId());
        if (res >0){
            return Result.success(user1);
        }else {
            return Result.fail();
        }
    }

    @Override
    @CacheEvict(key = "#user.id")
    public Result delete(User user) {
        int res = userMapper.deleteByPrimaryKey(user);
        if (res >0){
            return Result.success();
        }else {
            return Result.fail();
        }
    }
}

