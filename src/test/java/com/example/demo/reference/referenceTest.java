package com.example.demo.reference;

import org.junit.Test;

import java.lang.ref.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class referenceTest {
    public static void main(String[] args) {
    }
    // 强引用，内存不足不会被回收，只有在对象失去关联才会被JVM回收
    @Test
    public void reference(){
        Student student = new Student();
        student = null;
        System.gc();
    }
    // 软引用，内存不足会触发GC，如果GC过后还是不足就把软引用的对象回收。
    @Test
    public  void softReference(){
        // 创建软引用对象，包含10M字节数组，然后在创建10M字节数组，设定JVM堆内存为20M，-Xmx20M。
        SoftReference<byte[]> byteSoftReference = new SoftReference<byte[]>(new byte[1024*1024*10]);
        System.out.println(byteSoftReference.get());
        System.gc();
        System.out.println(byteSoftReference.get());

        byte[] bytesNew = new byte[1024 * 1024 * 10];
        System.out.println(byteSoftReference.get());
    }
    @Test
    public void weakReference(){
        // 弱引用，不管内存是否足够，对象都会被回收。
        WeakReference<byte[]> weakReference = new WeakReference<byte[]>(new byte[1]);
        System.out.println(weakReference.get());
        System.gc();
        System.out.println(weakReference.get());
    }
    @Test
    public void phantomReference(){
        ReferenceQueue queue = new ReferenceQueue();
        PhantomReference<byte[]> reference = new PhantomReference<byte[]>(new byte[1], queue);
        System.out.println(reference.get());
    }
    @Test
    public void phantomReference2(){
        ReferenceQueue queue = new ReferenceQueue();
        List<byte[]> bytes = new ArrayList<>();
        PhantomReference<Student> reference = new PhantomReference<Student>(new Student(),queue);

        new Thread(() -> {
            for (int i = 0; i < 1000;i++ ) {
                bytes.add(new byte[1024 * 1024]);
            }
        }).start();

        new Thread(() -> {
            while (true) {
                Reference poll = queue.poll();
                if (poll != null) {
                    System.out.println("虚引用被回收了：" + poll);
                }
            }
        }).start();

        Scanner scanner = new Scanner(System.in);
        scanner.hasNext();
    }
}
