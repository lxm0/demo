package learn;

import lombok.Synchronized;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.SynchronousQueue;

/**
 * @author Li
 * @version 1.0
 * @date 2020/8/8 20:27
 */
public class MainTest {
    public static void main(String[] args) {
        Aclass aclass = new Aclass();
        Bclass bclass = new Bclass();
        Map <String,Object> dd = new HashMap<>();
        Map cc = new HashMap();
        cc.put("1","2");
        cc.put("3","2");
        dd.put("target",cc);
        System.out.println(doExecuteMethod(dd));
    }
    public static Object doExecuteMethod(Map<String, Object> paramsMap) {
        Map target =(Map) paramsMap.get("target");
        if(null == target){
            return 0;
        } else {
            return target.keySet().size();
        }
    }
    @Test
    public void  test01() throws InterruptedException {
        String str = "str";
        Object o = str;
        for (int i = 0; i < 100; i++) {
            Integer integer = new Integer(i);
            new Thread(new Runnable() {
                @Override
                public void run() {
                        lock(o,integer);
                        //System.out.println(o);

                }
            }).start();
        }

    }
    public void lock(Object o,Integer i){
        System.out.print(i);
        synchronized (o) {
            if (i/2==0){
                System.out.println(i+"o wait");
                try {
                    o.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }else {
                System.out.println(i+"o notify");
                o.notify();
            }

        }
    }
}
