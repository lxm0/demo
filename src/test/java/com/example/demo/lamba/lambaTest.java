package com.example.demo.lamba;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class lambaTest {
    public static void main(String[] args) {
        Map<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("target","abcd");
        paramsMap.put("index",1);
        doExecuteMethod(paramsMap);
    }
    @Test
    public void testStatmentPattern(){
        Runnable runnable = ()->System.out.println("hello world!");
    }
    public static Map doExecuteMethod(Map<String, Object> paramsMap) {
        String target = (String)paramsMap.get("target");
        Integer index= (Integer)paramsMap.get("index");
        Map map = new HashMap();
        String str = target.substring(index-1,index);
        map.put("length",str);
        return map;
    }
}
