package com.example.demo.rni;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * @author Li
 * @version 1.0
 * @date 2020/8/2 13:14
 */
public class Server {
    public static void main(String args[]) throws Exception{

        //以1099作为LocateRegistry接收客户端请求的端口，并注册服务的映射关系
        Registry registry= LocateRegistry.createRegistry(1099);

        IOperation iOperation=new OperationImpl();
        Naming.rebind("rmi://127.0.0.1:1099/Operation",iOperation);

        System.out.println("service running...");
    }
}
