package com.example.demo.listeners;

import com.example.demo.util.ApplicationContextUtils;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

/**
 * @author Li
 * @version 1.0
 * @date 2020/8/1 17:40
 */
public class MainBusiListeners implements ApplicationListener<ContextRefreshedEvent> {
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        ApplicationContextUtils.setContext(event.getApplicationContext());
    }
}
