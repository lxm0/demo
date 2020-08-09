package proxy;

/**
 * @author Li
 * @version 1.0
 * @date 2020/8/9 21:20
 */
public class JavaCoder implements ICoder ,Icoder2{
    private String name = "lll";



    public JavaCoder(){
    }



    @Override

    public void implDemands(String demandName) {

        System.out.println(name + " implemented demand:" + demandName + " in JAVA!");

    }

    @Override
    public void implDemands2(String demandName) {
        System.out.println("implDemands2 "+name + " implemented demand:" + demandName + " in JAVA!");
    }
}
