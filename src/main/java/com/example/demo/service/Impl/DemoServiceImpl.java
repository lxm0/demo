package com.example.demo.service.impl;

import com.example.demo.service.DemoService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;

@Service
@DependsOn(value = "springUtil")
public class DemoServiceImpl implements DemoService, InitializingBean {
    @Override
    public ApplicationContext applicationContextTest() {
        return null;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        ApplicationContext applicationContext = SpringUtil.getApplicationContext();
        System.out.println(applicationContext);
    }
}
