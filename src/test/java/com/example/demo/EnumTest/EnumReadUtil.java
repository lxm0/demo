package com.example.demo.EnumTest;

import java.util.HashSet;
import java.util.Set;

public class EnumReadUtil {
    public static void main(String[] args) {
        // getAllDataTypeEnum(DataTypeEnum.class);
    }

    private static Set<String> getAllDataTypeEnum(Class clazz) {
        //获取所有枚举实例
        Object[] enumConstants = clazz.getEnumConstants();
        //根据方法名获取方法
        Set<String> set = new HashSet<>();
        for (Object enum1 : enumConstants) {
            //执行枚举方法获得枚举实例对应的值
            set.add(enum1.toString());
            System.out.println(enum1);
        }
        return set;
        // Set<String> set = new HashSet<>();
        // for (io.transwarp.dicommon.metadata.constant.DataTypeEnum dataEnum :
        //     io.transwarp.dicommon.metadata.constant.DataTypeEnum
        //     .values()) {
        //     set.add(dataEnum.name());
        // }
        // return set;
    }
}
