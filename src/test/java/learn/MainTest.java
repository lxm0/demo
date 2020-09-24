package learn;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

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
    public void test01(){
        String str = "str";
        Object o = str;
        String d = (String) o;
        System.out.println(d);
    }
}
