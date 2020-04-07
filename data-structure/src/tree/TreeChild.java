package tree;

import java.util.ArrayList;
import java.util.List;
	
/**
 * 子节点链表表示法的特点是，每个节点都可以快速找到它
 * 的所有子节点，但如果要找某个节点的父节点则比较麻烦，
 * 程序要遍历整个节点数组
 */
public class TreeChild<E> {
	// 子节点链节点 -- 记录当前节点的子节点的位置
    private static class SonNode {
        // 记录当前节点位置
        private int pos;
        private SonNode next;

        public SonNode(int pos, SonNode next) {
            this.pos = pos;
            this.next = next;
        }
    }
	// 节点类
    public static class Node<T> {
        T data;
        // 记录当前节点的第一个子节点
        SonNode first;
        public Node(T data) {
            this.data = data;
            this.first = null;
        }

        @Override
        public String toString() {
            if(first != null) {
                return "TreeChild$Node[data=" + data + ", first=" + first.pos + "]";
            }else {
                return "TreeChild$Node[data=" + data + ", first=-1]";
            }
        }
    }
    private final int DEFAULT_TREE_SIZE = 100;
    private int treeSize = 0;
    // 使用一个Node[] 数组来记录该树所有节点
    private Node<E>[] nodes;
    private int nodeNums;

    public TreeChild(E data) {
        treeSize = DEFAULT_TREE_SIZE;
        nodes = new Node[treeSize];
        nodes[0] = new Node<E>(data);
        nodeNums++;
    }

    public TreeChild(E data, int treeSize) {
        this.treeSize = treeSize;
        nodes = new Node[treeSize];
        nodes[0] = new Node<E>(data);
        nodeNums++;
    }

    // 为指定节点添加子节点 添加节点时只需找到指定节点的子节点链的最后节点，并让他指向新增的节点
    public void addNode(E data, Node parent) {
        for (int i = 0; i < treeSize; i++) {
            if(nodes[i] == null) {
                // 若该节点没有第一个子节点，那么就新建子节点链
                nodes[i] = new Node<E>(data);
                if(parent.first == null) {
                    parent.first = new SonNode(i, null);
                } else { // 若有，则依次取该节点的子节点，直到叶子节点
                    SonNode next = parent.first;
                    while (next.next != null) {
                        next = next.next;
                    }
                    next.next = new SonNode(i, null); // 在叶子节点处添加该子节点
                }
                nodeNums++;
                return;
            }
        }
        throw new RuntimeException("该树已满，无法添加新节点");
    }

    public boolean empty() {
        // 根节点是否为null
        return nodes[0] == null;
    }

    // 返回根节点
    public Node<E> root() {
        return nodes[0];
    }

    // 返回指定节点(非叶子节点) 的所有子节点
    public List<Node<E>> children(Node parent) {
        List<Node<E>> list = new ArrayList<>();
        // 获取parent节点的第一个子节点
        SonNode next= parent.first;
        // 沿着孩子链不断搜索下一个孩子节点
        while (next != null) {
            // 添加孩子链中的节点
            list.add(nodes[next.pos]);
            next = next.next;
        }
        return list;
    }
    // 返回指定节点(非叶子节点) 的第index个子节点
    public Node<E> children(Node parent, int index) {
        SonNode next = parent.first;
        // 沿着孩子链不断搜索下一个孩子节点
        for (int i = 0; next != null ; i++) {
            if (index == i) {
                return nodes[next.pos];
            }
            next = next.next;
        }
        return null;
    }
    // 返回该树深度
    public int deep() {
        return deep(root());
    }

    // 递归: 没棵子树的深度为其所有的子树最大深度+1
    private int deep(Node node) {
        if (node.first == null) {
            return 1;
        } else {
            // 记录其所有子树的最大深度
            int max = 0;
            SonNode next = node.first;
            // 沿着孩子链不断搜索下一个孩子节点
            while (next != null) {
                // 获取以其子节点为根的子树的深度
                int tmp = deep(nodes[next.pos]);
                if (tmp > max) {
                    max = tmp;
                }
                next = next.next;
            }
            // 返回其所有子树的最大深度+1
            return max + 1;
        }
    }

    // 返回包含指定值的节点
    public int pos(Node node) {
        for (int i = 0; i < treeSize; i++) {
            if (nodes[i] == node) {
                return i;
            }
        }
        return -1;
    }
}
