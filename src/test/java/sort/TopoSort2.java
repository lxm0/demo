package sort;

import lombok.Data;
import org.junit.Test;

import java.util.*;
@Data
public class TopoSort2<T> {

    private List<Node> result; // 用来存储结果集
    private Queue<Node> setOfZeroIndegree; // 用来存储入度为0的顶点
    private Graph graph;
    public TopoSort2() {
        this.graph = new Graph();
    }
    //构造函数，初始化
    public void TopoSort(Graph di) {
        this.graph = di;
        this.result = new ArrayList<>();
        this.setOfZeroIndegree = new LinkedList<>();
        // 对入度为0的集合进行初始化
        for(Node iterator : this.graph.vertexSet.values()){
            if(iterator.getPathIn() == 0){
                this.setOfZeroIndegree.add(iterator);
            }
        }
    }
    // 没有相邻节点
    public void addNode(T data) {
        if (!graph.vertexSet.containsKey(data)) {
            Node start = new Node(data);
            graph.vertexSet.put(data,start);
        }
    }
    // 将节点加入图中
    public boolean addNode(T data,T val) {
        Node start;
        Node end;
        if (!graph.vertexSet.containsKey(data)) {
            start = new Node(data);
            graph.vertexSet.put(start.getVal(),start);
        }else {
            start = graph.vertexSet.get(data);
        }
        if (!graph.vertexSet.containsKey(val)) {
            end = new Node(val);
            graph.vertexSet.put(val,end);
        }else {
            end = graph.vertexSet.get(val);
        }
        return this.addNode(start,end);
    }
    // 将节点加入图中
    public boolean addNode(Node start, Node end) {
        if (graph.adjaNode.containsKey(start)
                && graph.adjaNode.get(start).contains(end)) {
            return false;
        }
        if (graph.adjaNode.containsKey(start)) {
            graph.adjaNode.get(start).add(end);
        } else {
            Set<Node> temp = new HashSet<>();
            temp.add(end);
            graph.adjaNode.put(start, temp);
        }
        end.setPathInAddOne();
        return true;
    }

    //拓扑排序处理过程
    public void process() {
        TopoSort(graph);
        while (!setOfZeroIndegree.isEmpty()) {
            Node v = setOfZeroIndegree.poll();
            // 将当前顶点添加到结果集中
            result.add(v);
            //节点没有边
            if (!this.graph.adjaNode.containsKey(v)){
                this.graph.vertexSet.remove(v.getVal());
                continue;
            }
            // 遍历由v引出的所有边
            for (Node w : this.graph.adjaNode.get(v) ) {
                // 将该边从图中移除，通过减少边的数量来表示
                w.setPathInSubOne();
                if (0 == w.getPathIn()) // 如果入度为0，那么加入入度为0的集合
                {
                    setOfZeroIndegree.add(w);
                }
            }
            this.graph.vertexSet.remove(v.getVal());
            this.graph.adjaNode.remove(v);
        }
        // 如果此时图中还存在边，那么说明图中含有环路
        if (!this.graph.vertexSet.isEmpty()) {
            throw new IllegalArgumentException("Has Cycle !");
        }
    }
    public List getResult(){
        List data = new ArrayList<>();
        for (Node node: result) {
            data.add(node.getVal());
        }
        return data;
    }
    private  class Graph {
        // 图中节点的集合
        public Map<Object,Node> vertexSet = new HashMap<>();
        // 相邻的节点，纪录边
        public Map<Node, Set<Node>> adjaNode = new HashMap<>();
    }
    /**
     * 拓扑排序节点类
     */

    private  class Node {
        private T val;
        private int pathIn = 0; // 入度数量
        public Node(T val) {
            this.val = val;
        }

        public T getVal() {
            return val;
        }

        public void setVal(T val) {
            this.val = val;
        }

        public int getPathIn() {
            return pathIn;
        }

        public void setPathIn(int pathIn) {
            this.pathIn = pathIn;
        }

        // @Override
        // public int hashCode() {
        //     return val.hashCode();
        // }
        // @Override
        // public boolean equals(Object obj) {
        //     return val.equals(obj);
        // }
        public void setPathInSubOne() {
            this.pathIn--;
        }
        public void setPathInAddOne() {
            this.pathIn++;
        }
    }
    @Test
    public  void test() {
        TopoSort2.Node node = new TopoSort2.Node("A");
        System.out.println(node);
        System.out.println(node.hashCode());
        node.setPathInAddOne();
        System.out.println(node);
        System.out.print(node.hashCode());
    }
}
