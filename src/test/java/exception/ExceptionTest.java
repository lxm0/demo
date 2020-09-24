package exception;

import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

public class ExceptionTest {
    public static void main(String[] args) {
        //test1();
    }
    @Test
    @Transactional
    public  void test1(){
        try{
            test2();
        }catch (RuntimeException e){
            // throw new RuntimeException();
        }


        System.out.println("ll");
    }
    @Transactional(rollbackFor = Exception.class)
    public  void test2() {
        try {
            int i =1/0;
        }catch (Exception e){
            throw new RuntimeException();
        }
    }
}
