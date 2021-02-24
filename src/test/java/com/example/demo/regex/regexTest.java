package com.example.demo.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class regexTest {
    public static void main(String[] args) {
        String str = "test.*-\\{2020-11-02}.*.xlsx";
        Pattern p = Pattern.compile(str);
        Matcher matcher = p.matcher("");
        if (matcher.matches()) {
            System.out.println();
        }
    }
}
