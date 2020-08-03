package com.example.demo.rni;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * @author Li
 * @version 1.0
 * @date 2020/8/2 13:16
 */
public class OperationImpl extends UnicastRemoteObject implements IOperation {

    public OperationImpl() throws RemoteException {
        super();
    }
    @Override
    public int add(int a, int b) throws RemoteException {
        return a+b;
    }
}
