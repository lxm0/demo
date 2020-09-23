package sort;

import org.junit.Test;

import java.util.*;

import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Node;

/**
 * 拓扑排序，当前方案并没有在节点类中加入过多的内容
 * 但是在图类中加入了边的集合adjaNode
 */
public class TopoSort<T>{
        private List<Graph.Node> result; // 用来存储结果集
        private Queue<Graph.Node> setOfZeroIndegree; // 用来存储入度为0的顶点
        private Graph graph;

        //构造函数，初始化
        public TopoSort(Graph di) {
            this.graph = di;
            this.result = new ArrayList<>();
            this.setOfZeroIndegree = new LinkedList<>();
            // 对入度为0的集合进行初始化
            for(Object iterator : this.graph.vertexSet.values()){
                Graph<String>.Node node = (Graph<String>.Node)iterator;
                if(node.getPathIn() == 0){
                    this.setOfZeroIndegree.add(node);
                }
            }
        }

        //拓扑排序处理过程
        public void process() {
            while (!setOfZeroIndegree.isEmpty()) {
                Graph.Node v = setOfZeroIndegree.poll();
                // 将当前顶点添加到结果集中
                result.add(v);

                //节点没有边
                if (!this.graph.adjaNode.containsKey(v)){
                    this.graph.vertexSet.remove(v.getVal());
                    continue;
                }
                // 遍历由v引出的所有边
                for (Graph.Node w : (Set<Graph.Node>)this.graph.adjaNode.get(v) ) {
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
            for (Graph.Node node: result) {
                data.add(node.getVal());
            }
            return data;
        }

    public static void main(String[] args) {
        Graph graph = new Graph();
        Graph.Node node =  graph.new Node("A");
        System.out.println(node);
        System.out.println(node.hashCode());
        node.setPathInAddOne();
        System.out.println(node);
        System.out.print(node.hashCode());
    }
}

