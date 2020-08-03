package com.example.demo.rni;

import java.rmi.Naming;

/**
 * @author Li
 * @version 1.0
 * @date 2020/8/2 13:17
 */
public class Client {
    public static void main(String args[]) throws Exception{
        IOperation iOperation= (IOperation) Naming.lookup("rmi://127.0.0.1:1099/Operation");
        System.out.println(iOperation.add(1,1));
    }
}
