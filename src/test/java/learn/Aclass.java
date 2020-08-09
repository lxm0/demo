package learn;

import lombok.Data;

/**
 * @author Li
 * @version 1.0
 * @date 2020/8/8 20:25
 */
@Data
public class Aclass {
    private Bclass aclass;
    private String name;
    public void hasB(Aclass bclass){

        System.out.println(bclass.name);
    }

}
