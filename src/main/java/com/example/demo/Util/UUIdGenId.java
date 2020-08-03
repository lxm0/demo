package com.example.demo.Util;

import tk.mybatis.mapper.genid.GenId;

import java.util.UUID;

/**
 * @author Li
 * @version 1.0
 * @date 2020/8/1 15:13
 */
public class UUIdGenId implements GenId<String> {
    @Override
    public String genId(String s, String s1) {
        return UUID.randomUUID().toString().replace("-","");
    }
}
