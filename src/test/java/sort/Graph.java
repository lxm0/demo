package sort;

import lombok.Data;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Graph<T> {
    // 图中节点的集合
    public Map<T,Node> vertexSet = new HashMap<>();
    // 相邻的节点，纪录边
    public Map<Node, Set<Node>> adjaNode = new HashMap<>();

    public Graph(){

    }
    // 没有相邻节点
    public void addNode(T data) {
        if (!vertexSet.containsKey(data)) {
            Node start = new Node(data);
            vertexSet.put(data,start);
        }
    }
    // 将节点加入图中
    public boolean addNode(T data,T val) {
        Node start;
        Node end;
        if (!vertexSet.containsKey(data)) {
            start = new Node(data);
            vertexSet.put(start.getVal(),start);
        }else {
            start = vertexSet.get(data);
        }
        if (!vertexSet.containsKey(val)) {
            end = new Node(val);
            vertexSet.put(val,end);
        }else {
            end = vertexSet.get(val);
        }
        return this.addNode(start,end);
    }
    // 将节点加入图中
    public boolean addNode(Node start, Node end) {
        if (adjaNode.containsKey(start)
            && adjaNode.get(start).contains(end)) {
            return false;
        }
        if (adjaNode.containsKey(start)) {
            adjaNode.get(start).add(end);
        } else {
            Set<Node> temp = new HashSet<>();
            temp.add(end);
            adjaNode.put(start, temp);
        }
        end.setPathInAddOne();
        return true;
    }
    /**
     * 拓扑排序节点类
     */

     public  class Node {
        private T val;
        private int pathIn = 0; // 入度数量
        public Node() {
        }
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
        public void setPathInSubOne() {
            this.pathIn--;
        }
        public void setPathInAddOne() {
            this.pathIn++;
        }
    }
}
