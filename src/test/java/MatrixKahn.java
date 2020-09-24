import java.util.*;

/**
 * 邻接矩阵有向图实现Kahn算法
 * {@link MatrixKahn#MatrixKahn(E[], E[][])}  构建有向图
 * {@link MatrixKahn#DFS()}  深度优先遍历无向图
 * {@link MatrixKahn#BFS()}  广度优先遍历无向图
 * {@link MatrixKahn#toString()} 输出无向图
 * {@link MatrixKahn#kahn()} Kahn算法获取拓扑序列
 *
 * @param <E>
 * @author lx
 */
public class MatrixKahn<E> {

    /**
     * 顶点数组
     */
    private Object[] vertexs;
    /**
     * 邻接矩阵
     */
    private int[][] matrix;

    /**
     * 创建有向图
     *
     * @param vexs  顶点数组
     * @param edges 边二维数组
     */
    public MatrixKahn(E[] vexs, E[][] edges) {
        // 初始化顶点数组,并添加顶点
        vertexs = Arrays.copyOf(vexs, vexs.length);
        // 初始化边矩阵,并填充边信息
        matrix = new int[vexs.length][vexs.length];
        for (E[] edge : edges) {
            // 读取一条边的起始顶点和结束顶点索引值 p1,p2表示边方向p1->p2
            int p1 = getPosition(edge[0]);
            int p2 = getPosition(edge[1]);
            //p1 出度的位置 置为1
            matrix[p1][p2] = 1;
            //无向图和有向图的邻接矩阵实现的区别就在于下面这一行代码
            //matrix[p2][p1] = 1;
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
            if (vertexs[i] == e) {
                return i;
            }
        }
        return -1;
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
        for (int i = 0; i < vertexs.length; i++) {
            if (!visited[i]) {
                DFS(i, visited);
            }
        }
        System.out.println();
    }

    /**
     * 深度优先搜索遍历图的递归实现,类似于树的先序遍历
     * 因此模仿树的先序遍历,同样借用栈结构,这里使用的是方法的递归,隐式的借用栈
     *
     * @param i       顶点索引
     * @param visited 访问标志数组
     */
    private void DFS(int i, boolean[] visited) {
        visited[i] = true;
        System.out.print(vertexs[i] + " ");
        // 遍历该顶点的所有邻接点。若该邻接点是没有访问过，那么继续递归遍历领接点
        for (int w = firstVertex(i); w >= 0; w = nextVertex(i, w)) {
            if (!visited[w]) {
                DFS(w, visited);
            }
        }
    }

    /**
     * 返回顶点v的第一个邻接顶点的索引，失败则返回-1
     *
     * @param v 顶点v在数组中的索引
     * @return 返回顶点v的第一个邻接顶点的索引，失败则返回-1
     */
    private int firstVertex(int v) {
        //如果索引超出范围,则返回-1
        if (v < 0 || v > (vertexs.length - 1)) {
            return -1;
        }
        /*根据邻接矩阵的规律:顶点索引v对应着边二维矩阵的matrix[v][i]一行记录
         * 从i=0开始*/
        for (int i = 0; i < vertexs.length; i++) {
            if (matrix[v][i] == 1) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 返回顶点v相对于w的下一个邻接顶点的索引，失败则返回-1
     *
     * @param v 顶点索引
     * @param w 第一个邻接点索引
     * @return 返回顶点v相对于w的下一个邻接顶点的索引，失败则返回-1
     */
    private int nextVertex(int v, int w) {
        //如果索引超出范围,则返回-1
        if (v < 0 || v > (vertexs.length - 1) || w < 0 || w > (vertexs.length - 1)) {
            return -1;
        }
        /*根据邻接矩阵的规律:顶点索引v对应着边二维矩阵的matrix[v][i]一行记录
         * 由于邻接点w的索引已经获取了,所以从i=w+1开始寻找*/
        for (int i = w + 1; i < vertexs.length; i++) {
            if (matrix[v][i] == 1) {
                return i;
            }
        }
        return -1;
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
        for (int i = 0; i < vertexs.length; i++) {
            visited[i] = false;
        }
        System.out.println("BFS:");
        System.out.print("\t");
        for (int i = 0; i < vertexs.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                System.out.print(vertexs[i] + " ");
                indexLinkedList.add(i);
            }
            if (!indexLinkedList.isEmpty()) {
                //j索引出队列
                Integer j = indexLinkedList.poll();
                //继续访问j的邻接点
                for (int k = firstVertex(j); k >= 0; k = nextVertex(j, k)) {
                    if (!visited[k]) {
                        visited[k] = true;
                        System.out.print(vertexs[k] + " ");
                        //继续入队列
                        indexLinkedList.add(k);
                    }
                }
            }
        }
        System.out.println();
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < vertexs.length; i++) {
            for (int j = 0; j < vertexs.length; j++) {
                stringBuilder.append(matrix[i][j]).append("\t");
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
        //遍历矩阵,计算每个顶点的入度
        for (int i = 0; i < vertexs.length; i++) {
            for (int j = 0; j < vertexs.length; j++) {
                if (matrix[i][j] != 0) {
                    inArr[j]++;
                }
            }
        }
        //辅助结构队列,用于存储0入度的顶点
        Queue<Integer> queueNode = new LinkedList<>();
        //辅助栈空间,用于存储0入度的顶点
        //Stack<Integer> stackNode = new Stack<>();
        for (int i = 0; i < inArr.length; i++) {
            if (inArr[i] == 0) {
                //添加0入度的顶点索引到队列尾部
                queueNode.add(i);
                //添加0入度的顶点索引到栈顶
                //stackNode.add(i);
            }
        }
        List<Integer> result = new ArrayList<>();
        //入度为0的节点从队列弹出并且把加入list，相当于从图中去掉，所以还要把其邻接节点的入度减1
        //循环判断队列是否为空
        while (!queueNode.isEmpty()) {
            //入度为0的节点索引从队列头部移除并且加入result
            Integer nodeIndex = queueNode.poll();
            //实际上存储顺序就是拓扑排序的顺序
            result.add(nodeIndex);
            //遍历矩阵,获取该顶点的邻接点,将邻接点的入度减去一,并且判断邻接点的入度是否变成了0,如果变成了0那么也加入到队列中
            for (int i = 0; i < vertexs.length; i++) {
                //入度在顶点所表示的"列"中
                if (matrix[nodeIndex][i] != 0) {
                    if (--inArr[i] == 0) {
                        queueNode.add(i);
                    }
                }
            }
        }
        /*使用栈辅助结构*/
        //循环判断栈是否为空
        /*while (!stackNode.isEmpty()) {
            //移除栈顶顶点元素索引
            Integer nodeIndex = stackNode.pop();
            //实际上存储顺序就是拓扑排序的顺序
            result.add(nodeIndex);
            //获取该顶点的邻接点,将邻接点的入度减去一,并且判断邻接点的入度是否变成了0,如果变成了0那么也加入到队列中
            for (int i = 0; i < vertexs.length; i++) {
                if (matrix[nodeIndex][i] != 0) {
                    if (--inArr[i] == 0) {
                        stackNode.add(i);
                    }
                }
            }
        }*/

        //输出集合,顺出顺序就是拓扑排序的顺序
        System.out.println("Kahn:");
        System.out.print("\t");
        for (Integer nodeIndex : result) {
            System.out.print(vertexs[nodeIndex] + " ");
        }
    }


    public static void main(String[] args) {
        Character[] vexs = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
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
        //构建图
        MatrixKahn<Character> matrixKahn = new MatrixKahn<>(vexs, edges);
        //输出图
        System.out.println(matrixKahn);
        //深度优先遍历
        matrixKahn.DFS();
        //广度优先遍历
        matrixKahn.BFS();
        //Kahn算法求拓扑序列
        matrixKahn.kahn();
    }
}
