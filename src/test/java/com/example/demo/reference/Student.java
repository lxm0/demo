package com.example.demo.reference;

public class Student {
    @Override
    public void finalize() throws Throwable{
        System.out.println("Strudent 被回收");
    }
}
