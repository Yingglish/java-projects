package tree;

import java.util.ArrayList;
import java.util.List;

/**
 * 父节点表示法
 * @param <E>
 */
public class TreeParent<E> {
	/*
		非静态内部类会自动继承外部类的泛型
		静态内部类不会自动继承外围类泛型参数
		此处泛型符号若使用E，但TreeParent<E>与Node<E>中的 E 无任何联系,只是符号相同
	*/

	/*
		静态内部类补充:
			1.静态内部类不能使用外部类的非静态属性，并且在创建时可以不使用外部类对象
			2.静态内部类中只能调用外部类的静态变量和静态方法
	*/
    public static class Node<T> {
        T data;
        // 记录该节点的父节点在数组中的位置索引
        int parent;

        public Node() {
        }

        public Node(T data) {
            this.data = data;
        }

        public Node(T data, int parent) {
            this.data = data;
            this.parent = parent;
        }

        @Override
        public String toString() {
            return "TreeParent$Node{" +
                    "data=" + data +
                    ", parent=" + parent +
                    '}';
        }
    }

    private final int DEFAULT_TREE_SIZE = 100;
    private int treeSize = 0;
    private Node<E>[] nodes; // 树中所有节点
    private int nodeNums; // 节点数

    // 以指定根节点创建树
    public TreeParent(E data) {
        treeSize = DEFAULT_TREE_SIZE;
        nodes = new Node[treeSize];
        nodes[0] = new Node<E>(data, -1);
        nodeNums++;
    }

    // 以指定根节点, 指定treeSize创建树
    public TreeParent(E data, int treeSize) {
        this.treeSize = treeSize;
        nodes = new Node[treeSize];
        nodes[0] = new Node<E>(data, -1);
        nodeNums++;
    }

    // 为指定根节点添加子节点
    public void addNode(E data, Node parent) {
        for (int i = 0; i < treeSize; i++) {
           // 找到数组中第一个为null的元素，该元素保存新节点
           if(nodes[i] == null) {
               // 创建新节点，并用指定的数组元素保存它
               nodes[i] = new Node<E>(data,pos(parent));
               nodeNums++;
               return;
           }
        }
        throw new RuntimeException("该树已满,无法添加新节点");
    }

    // 判断是否为空
    public boolean empty() {
        // 根节点是否为null
        return nodes[0] == null;
    }

    // 返回根节点
    public Node<E> root() {
        return nodes[0];
    }

    // 返回指定节点(非根节点) 的父节点
    public Node<E> parent(Node node) {
        return nodes[node.parent];
    }

    // 返回指定节点(非叶子节点)的所有子节点
    public List<Node<E>> children(Node parent) {
        List<Node<E>> list = new ArrayList<>();
        for (int i = 0; i < treeSize; i++) {
            // 如果当前节点的父节点的位置等于parent节点的位置
            if(nodes[i] != null && nodes[i].parent == pos(parent)) {
                list.add(nodes[i]);
            }
        }
        return list;
    }

    // 返回该树的深度
    public int deep() {
        // 用于记录节点最大深度
        int max = 0;
        for (int i = 0; i < treeSize && nodes[i] != null; i++) {
            // 初始化本节点深度
            int def = 1;
            // 记录当前节点父节点位置
            int m = nodes[i].parent;
            // 如果其父节点存在
            while (m != -1 && nodes[m] != null) {
                // 向上继续搜索父节点
                m = nodes[m].parent;
                def++;
            }
            if(max < def) {
                max = def;
            }
        }
        return max;
    }

    // 返回包含指定值的节点
    public int pos(Node node) {
        for (int i = 0; i < treeSize; i++) {
            if(nodes[i] == node) {
                return i;
            }
        }
        return -1;
    }
}
