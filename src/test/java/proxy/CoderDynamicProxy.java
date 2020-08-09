package proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author Li
 * @version 1.0
 * @date 2020/8/9 21:23
 */
public class CoderDynamicProxy implements InvocationHandler {
    //被代理的实例

    private ICoder coder;



    public CoderDynamicProxy(ICoder _coder){

        this.coder = _coder;

    }



    //调用被代理的方法


    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        System.out.println(System.currentTimeMillis());

        Object result = method.invoke(coder, args);

        System.out.println(System.currentTimeMillis());

        return result;

    }
}
