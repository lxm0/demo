/**
 * @author Li
 * @version 1.0
 * @date 2020/8/8 16:43
 */
public class Test {
    public static void main(String[] args) {
        float f = 1.3f;
        System.out.println(f/0);
    }
    @org.junit.Test
    public void StringTest(){
        String str1 = "sds";
        String str2 = str1.substring(0,1)+ "jj";
        String str3 = str1.substring(0,1)+ "jj";
        System.out.println(str3);
        System.out.println(str2);
        System.out.println(str2==str3);
        String str4 = "sds";
        System.out.println(str1==str4);
    }
}
