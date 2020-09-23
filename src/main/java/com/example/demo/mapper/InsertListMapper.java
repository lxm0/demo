package com.example.demo.mapper;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Options;
import tk.mybatis.mapper.annotation.RegisterMapper;

import java.util.List;

@RegisterMapper
public interface InsertListMapper<T> {
    @Options(
            useGeneratedKeys = true,
            keyProperty = "id"
    )
    @InsertProvider(
            type = SpecialProvider.class,
            method = "dynamicSQL"
    )
    int insertList(List<T> var1);
}
