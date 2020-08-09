package proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * @author Li
 * @version 1.0
 * @date 2020/8/9 21:23
 */
public class DynamicClient {
    public static void main(String args[]){

        //要代理的真实对象

        ICoder coder =new JavaCoder();

        //创建中介类实例

        InvocationHandler handler = new CoderDynamicProxy(coder);

        //获取类加载器

        ClassLoader cl = coder.getClass().getClassLoader();

        //动态产生一个代理类

        ICoder proxy = (ICoder) Proxy.newProxyInstance(cl, new Class[]{Icoder2.class,ICoder.class}, handler);

        //通过代理类，执行doSomething方法；

        proxy.implDemands("Modify user management");

    }
}
