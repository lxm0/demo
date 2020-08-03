package com.example.demo.rni;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @author Li
 * @version 1.0
 * @date 2020/8/2 13:15
 */
public interface IOperation extends Remote {
    /**
     * 远程接口上的方法必须抛出RemoteException，因为网络通信是不稳定的，不能吃掉异常
     * @param a
     * @param b
     * @return
     */
    int add(int a, int b) throws RemoteException;

}
