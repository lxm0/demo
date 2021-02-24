package com.example.demo;

import com.example.demo.listeners.MainBusiListeners;
import com.example.demo.service.DemoService;
import com.example.demo.service.UserService;
import com.example.demo.util.ApplicationContextUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;


public class DemoApplicationTests {
	public static void main(String[] args) {
		SpringApplication sa = new SpringApplication(DemoApplication.class);
		sa.addListeners(new MainBusiListeners());
		sa.run(args);
		// 测试
		UserService bean = ApplicationContextUtils.getBean(UserService.class);
		System.out.println(bean.findAll());
	}
	@Test
	public void contextLoads() {
		String str = "w";
	}
	@Test
	public void applicationContextTest(){

	}
	public Object doExecuteMethod(Map<String, Object> paramsMap) {
		Map target = (Map)paramsMap.get("target");
		int i =0;
		for (;;){
			if (i>0){
				break;
			}
		}
		if(null == target){
			return 0;
		} else {
			return target.size();
		}
	}

}
