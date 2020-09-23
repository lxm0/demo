package com.example.demo.mybatis;

import com.example.demo.dmo.User;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BatchUpdate {
    @Autowired
    private UserMapper userMapper;
    public static void main(String[] args) {

    }
    @Test
    public void insertUser(){
        List<User> users = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            User user = new User();
            user.setName("lxm"+i);
            user.setAge(20);
            user.setAddress("lll");
            user.setPhone("112343");
            users.add(user);
        }
        userMapper.insertList(users);
    }
    @Test
    public void updateUser(){
        List<User> users = userMapper.selectAll();
        for (User user:users) {
            user.setAddress("xxxx");
        }
        userMapper.updateBatchByPrimaryKeySelective(users);
    }
}
