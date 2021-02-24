package com.example.demo.EnumTest;

import java.util.ArrayList;
import java.util.HashMap;

public enum DataTypeEnum  {
    Integer("整型数字"),
    Double("浮点数字"),
    String("字符串"),
    Datetime("时间"),
    Enum("枚举"),
    ListString("字符串列表"),
    ListDouble("数字列表"),
    Boolean("布尔值"),
    Map("keyValue"),
    MapMap("KeyKeyValue"),
    DataSet("数据集"),
    Object("复杂对象"),
    Any("任意");

    private String msg;

    private DataTypeEnum(String msg) {
        this.msg = msg;
    }

    public String getMessage() {
        return this.msg;
    }

    public static DataTypeEnum transDataType(String dataType) {
        DataTypeEnum dataTypeEnum = null;
        byte var3 = -1;
        switch(dataType.hashCode()) {
            case -1325958191:
                if (dataType.equals("double")) {
                    var3 = 3;
                }
                break;
            case -891985903:
                if (dataType.equals("string")) {
                    var3 = 4;
                }
                break;
            case 104431:
                if (dataType.equals("int")) {
                    var3 = 0;
                }
                break;
            case 3076014:
                if (dataType.equals("date")) {
                    var3 = 6;
                }
                break;
            case 3560141:
                if (dataType.equals("time")) {
                    var3 = 7;
                }
                break;
            case 64711720:
                if (dataType.equals("boolean")) {
                    var3 = 5;
                }
                break;
            case 97526364:
                if (dataType.equals("float")) {
                    var3 = 2;
                }
                break;
            case 1792749467:
                if (dataType.equals("dateTime")) {
                    var3 = 8;
                }
                break;
            case 1958052158:
                if (dataType.equals("integer")) {
                    var3 = 1;
                }
        }

        switch(var3) {
            case 0:
                dataTypeEnum = valueOf("Integer");
                break;
            case 1:
                dataTypeEnum = valueOf("Integer");
                break;
            case 2:
                dataTypeEnum = valueOf("Double");
                break;
            case 3:
                dataTypeEnum = valueOf("Double");
                break;
            case 4:
                dataTypeEnum = valueOf("String");
                break;
            case 5:
                dataTypeEnum = valueOf("Boolean");
                break;
            case 6:
                dataTypeEnum = valueOf("Datetime");
                break;
            case 7:
                dataTypeEnum = valueOf("Datetime");
                break;
            case 8:
                dataTypeEnum = valueOf("Datetime");
        }

        return dataTypeEnum;
    }

}
