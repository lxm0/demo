import lombok.Data;
import sort.Graph;
import sort.TopoSort;
import sort.TopoSort2;

import java.util.*;


/**
 * @author Li
 * @version 1.0
 * @date 2020/8/8 16:43
 */
public class Test {
    public static void main(String[] args) {
        Graph graph = new Graph();
        graph.addNode("A","B");
        graph.addNode("B","C");

        graph.addNode("B","D");

        graph.addNode("D","C");
        graph.addNode("E","C");
        graph.addNode("C","F");
        graph.addNode("G");
        TopoSort topoSort = new TopoSort(graph);
        topoSort.process();
        List list = topoSort.getResult();
        for(Object temp : list){
            System.out.print(temp + "-->");
        }
    }
    @org.junit.Test
    public void testTopoSort2(){
        TopoSort2<Integer> topoSort2 = new TopoSort2();
        // topoSort2.addNode("A","B");
        // topoSort2.addNode("B","C");
        //
        // topoSort2.addNode("B","D");
        //
        // topoSort2.addNode("D","C");
        // topoSort2.addNode("E","C");
        // topoSort2.addNode("C","F");
        // topoSort2.addNode("G");
        topoSort2.addNode(1,2);
        topoSort2.addNode(2,3);
        topoSort2.addNode(2,4);
        topoSort2.addNode(4,3);
        topoSort2.addNode(5,3);
        topoSort2.addNode(3,6);
        topoSort2.addNode(7);

        topoSort2.process();
        List list = topoSort2.getResult();
        for(Object temp : list){
            System.out.print(temp + "-->");
        }
    }
    @org.junit.Test
    public void testHashCode(){
        Node node = new Node("A");
        System.out.println(node);
        System.out.println(node.hashCode());
        node.setPathInAddOne();
        System.out.println(node);
        System.out.print(node.hashCode());
    }

}
@Data
class Node {
    private Object val;
    private int pathIn = 0; // 入度数量
    public Node(Object val) {
        this.val = val;
    }

    public Object getVal() {
        return val;
    }

    public void setVal(Object val) {
        this.val = val;
    }

    public int getPathIn() {
        return pathIn;
    }

    public void setPathIn(int pathIn) {
        this.pathIn = pathIn;
    }
    public void setPathInSubOne() {
        this.pathIn--;
    }
    public void setPathInAddOne() {
        this.pathIn++;
    }
}
