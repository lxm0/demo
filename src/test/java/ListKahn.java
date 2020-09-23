import java.util.*;

/**
 * 邻接表有向图实现Kahn算法
 * {@link ListKahn#ListKahn(E[], E[][])}  构建有向图
 * {@link ListKahn#DFS()}  深度优先遍历有向图
 * {@link ListKahn#BFS()}  广度优先遍历有向图
 * {@link ListKahn#toString()} 输出有向图
 * {@link ListKahn#kahn()} Kahn算法获取拓扑序列
 *
 * @param <E>
 * @author lx
 */
public class ListKahn<E> {
    /**
     * 顶点类
     *
     * @param <E>
     */
    private class Node<E> {
        /**
         * 该顶点的入度
         */
        int in;
        /**
         * 顶点信息
         */
        E data;
        /**
         * 指向第一条依附该顶点的边
         */
        LNode firstEdge;

        public Node(E data, LNode firstEdge) {
            this.data = data;
            this.firstEdge = firstEdge;
        }

        @Override
        public String toString() {
            return "" + data;
        }
    }

    /**
     * 边表节点类
     */
    private class LNode {
        /**
         * 该边所指向的顶点的索引位置
         */
        int vertex;
        /**
         * 指向下一条弧的指针
         */
        LNode nextEdge;
    }

    /**
     * 顶点数组
     */
    private Node<E>[] vertexs;

    /**
     * 创建图
     *
     * @param vexs  顶点数组
     * @param edges 边二维数组
     */
    public ListKahn(E[] vexs, E[][] edges) {
        /*初始化顶点数组,并添加顶点*/
        vertexs = new Node[vexs.length];
        for (int i = 0; i < vertexs.length; i++) {
            vertexs[i] = new Node<>(vexs[i], null);
        }
        /*初始化边表,并添加边节点到边表尾部,即采用尾插法*/
        for (E[] edge : edges) {
            // 读取一条边的起始顶点和结束顶点索引值
            int p1 = getPosition(edge[0]);
            int p2 = getPosition(edge[1]);
            // 初始化lnode1边节点 即表示p1指向p2的边
            LNode lnode1 = new LNode();
            lnode1.vertex = p2;

            // 将LNode链接到"p1所在链表的末尾"
            if (vertexs[p1].firstEdge == null) {
                vertexs[p1].firstEdge = lnode1;
            } else {
                linkLast(vertexs[p1].firstEdge, lnode1);
            }
            for (Node<E> vertex : vertexs) {
                if (vertex.data.equals(edge[1])) {
                    vertex.in += 1;
                }
            }
        }

    }

    /**
     * 获取某条边的某个顶点所在顶点数组的索引位置
     *
     * @param e 顶点的值
     * @return 所在顶点数组的索引位置, 或者-1 - 表示不存在
     */
    private int getPosition(E e) {
        for (int i = 0; i < vertexs.length; i++) {
            if (vertexs[i].data == e) {
                return i;
            }
        }
        return -1;
    }


    /**
     * 将lnode节点链接到边表的最后,采用尾插法
     *
     * @param first 边表头结点
     * @param node  将要添加的节点
     */
    private void linkLast(LNode first, LNode node) {
        while (true) {
            if (first.vertex == node.vertex) {
                return;
            }
            if (first.nextEdge == null) {
                break;
            }
            first = first.nextEdge;
        }
        first.nextEdge = node;
    }

    /**
     * 深度优先搜索遍历图的递归实现,类似于树的先序遍历
     * 因此模仿树的先序遍历,同样借用栈结构,这里使用的是方法的递归,隐式的借用栈
     *
     * @param i       顶点索引
     * @param visited 访问标志数组
     */
    private void DFS(int i, boolean[] visited) {
        //索引索引标记为true ,表示已经访问了
        visited[i] = true;
        System.out.print(vertexs[i].data + " ");
        //获取该顶点的边表头结点
        LNode node = vertexs[i].firstEdge;
        //循环遍历该顶点的邻接点,采用同样的方式递归搜索
        while (node != null) {
            if (!visited[node.vertex]) {
                DFS(node.vertex, visited);
            }
            node = node.nextEdge;
        }
    }

    /**
     * 深度优先搜索遍历图,类似于树的前序遍历,
     */
    public void DFS() {
        //新建顶点访问标记数组,对应每个索引对应相同索引的顶点数组中的顶点
        boolean[] visited = new boolean[vertexs.length];
        //初始化所有顶点都没有被访问
        for (int i = 0; i < vertexs.length; i++) {
            visited[i] = false;
        }
        System.out.println("DFS:");
        System.out.print("\t");
        /*循环搜索*/
        for (int i = 0; i < vertexs.length; i++) {
            //如果对应索引的顶点的访问标记为false,则搜索该顶点
            if (!visited[i]) {
                DFS(i, visited);
            }
        }
        /*走到这一步,说明顶点访问标记数组全部为true,说明全部都访问到了,深度搜索结束*/
        System.out.println();
    }


    /**
     * 广度优先搜索图,类似于树的层序遍历
     * 因此模仿树的层序遍历,同样借用队列结构
     */
    public void BFS() {
        // 辅组队列
        Queue<Integer> indexLinkedList = new LinkedList<>();
        //新建顶点访问标记数组,对应每个索引对应相同索引的顶点数组中的顶点
        boolean[] visited = new boolean[vertexs.length];
        //初始化所有顶点都没有被访问
        for (int i = 0; i < vertexs.length; i++) {
            visited[i] = false;
        }
        System.out.println("BFS:");
        System.out.print("\t");
        for (int i = 0; i < vertexs.length; i++) {
            //如果访问方剂为false,则设置为true,表示已经访问,然后开始访问
            if (!visited[i]) {
                visited[i] = true;
                System.out.print(vertexs[i].data + " ");
                indexLinkedList.add(i);
            }
            //判断队列是否有值,有就开始遍历
            if (!indexLinkedList.isEmpty()) {
                //出队列
                Integer j = indexLinkedList.poll();
                LNode node = vertexs[j].firstEdge;
                while (node != null) {
                    int k = node.vertex;
                    if (!visited[k]) {
                        visited[k] = true;
                        System.out.print(vertexs[k].data + " ");
                        //继续入队列
                        indexLinkedList.add(k);
                    }
                    node = node.nextEdge;
                }
            }
        }
        System.out.println();
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < vertexs.length; i++) {
            stringBuilder.append(i).append("(").append(vertexs[i].data).append("-").append(vertexs[i].in).append("): ");
            LNode node = vertexs[i].firstEdge;
            while (node != null) {
                stringBuilder.append(node.vertex).append("(").append(vertexs[node.vertex].data).append(")");
                node = node.nextEdge;
                if (node != null) {
                    stringBuilder.append("->");
                } else {
                    break;
                }
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    /**
     * kahn算法求拓扑排序
     */
    public void kahn() {
        //用于存储顶点的入度的数组
        int[] inArr = new int[vertexs.length];
        //辅助结构队列,用于存储0入度的顶点
        Queue<Node<E>> queueNode = new LinkedList<>();
        //辅助栈空间,用于存储0入度的顶点
        Stack<Node<E>> stackNode = new Stack<>();
        for (int i = 0; i < vertexs.length; i++) {
            inArr[i] = vertexs[i].in;
            if (vertexs[i].in == 0) {
                //添加0入度的顶点到队列尾部
                queueNode.add(vertexs[i]);
                //添加0入度的顶点到栈顶
                stackNode.add(vertexs[i]);
            }
        }
        List<Node<E>> result = new ArrayList<>();
        // 入度为0的节点从队列弹出并且把加入list，相当于从图中去掉，所以还要把其邻接节点的入度减1
        //循环判断队列是否为空
        while (!queueNode.isEmpty()) {
            //入度为0的节点从队列头部移除并且加入result
            Node<E> node = queueNode.poll();
            //实际上存储顺序就是拓扑排序的顺序
            result.add(node);
            //获取该顶点的邻接点,将邻接点的入度减去一,并且判断邻接点的入度是否变成了0,如果变成了0那么也加入到队列中
            LNode first = node.firstEdge;
            while (first != null) {
                inArr[first.vertex]--;
                if (inArr[first.vertex] == 0) {
                    queueNode.add(vertexs[first.vertex]);
                }
                first = first.nextEdge;
            }
        }
        /*使用栈辅助结构*/
        //循环判断栈是否为空
        /*while (!stackNode.isEmpty()) {
            //移除栈顶顶点元素
            Node<E> node = stackNode.pop();
            //实际上存储顺序就是拓扑排序的顺序
            result.add(node);
            //获取该顶点的领接点,将领接点的入度减去一,并且判断领接点的入度是否变成了0,如果变成了0那么也加入到队列中
            LNode first = node.firstEdge;
            while (first != null) {
                inArr[first.vertex]--;
                if (inArr[first.vertex] == 0) {
                    stackNode.add(vertexs[first.vertex]);
                }
                first = first.nextEdge;
            }
        }*/
        //输出集合,顺出顺序就是拓扑排序的顺序
        System.out.println("Kahn:");
        System.out.print("\t");
        System.out.println(result);
    }

    public static void main(String[] args) {
        //顶点数组 添加的先后顺序对于遍历结果有影响
        Character[] vexs = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        //边二维数组 {'a', 'b'}表示顶点a->b的边  添加的先后顺序对于遍历结果有影响
        Character[][] edges = {
                {'A', 'C'},
                {'A', 'D'},
                {'A', 'F'},
                {'C', 'B'},
                {'C', 'D'},
                //{'D', 'C'},
                {'D', 'B'},
                {'G', 'E'},
                {'B', 'E'},
                {'F', 'G'}};
        // 构建图有向图
        ListKahn<Character> listKahn = new ListKahn<>(vexs, edges);
        //输出图
        System.out.println(listKahn);
        //深度优先遍历
        listKahn.DFS();
        //广度优先遍历
        listKahn.BFS();
        //Kahn算法求拓扑序列
        listKahn.kahn();
    }
}
