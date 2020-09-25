package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;
import java.util.concurrent.Callable;

@RestController
@RequestMapping("/demo/")
public class DemoController {
    @GetMapping("hello")
    public String hello() throws InterruptedException {
        // 这个方法固定延时3秒，用于测试线程/连接数量控制
        Thread.sleep(3000);
        return "hello!!";
    }

    @GetMapping("test")
    public String benchmark() throws InterruptedException {
        System.out.println("访问test：" + Thread.currentThread().getName());
        // 这段代码，一直运算
        for (int i = 0; i < 200000; i++) {
            new Random().nextInt();
        }
        // 50毫秒的数据库等待，线程不干活
        Thread.sleep(50L);
        return "Success";
    }

    @GetMapping("testAsync")
    public Callable<String> benchmarkAsync() {
        return new Callable<String>() {
            public String call() throws Exception {
                System.out.println("访问testAsync：" + Thread.currentThread().getName());
                // 这段代码，一直运算
                for (int i = 0; i < 200000; i++) {
                    new Random().nextInt();
                }
                // 50毫秒的数据库等待，线程不干活
                Thread.sleep(50L);
                return "Success";
            }
        };
    }
}
