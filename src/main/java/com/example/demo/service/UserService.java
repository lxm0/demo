package com.example.demo.service;

import com.example.demo.dmo.User;
import com.example.demo.util.Result;

/**
 * @author Li
 * @version 1.0
 * @date 2020/8/1 14:31
 */
public interface UserService {

    public Result findById(int id);

    public Result findAll();

    public Result insert(User user);

    public Result update(User user);

    public Result delete(User user);

}

