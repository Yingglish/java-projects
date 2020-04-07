package tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
* 三叉树
*/
public class ThreeLinkBinTree<E> {
    public static class TreeNode {
        Object data;
        TreeNode left;
        TreeNode right;
        TreeNode parent;

        public TreeNode() {
        }

        public TreeNode(Object data) {
            this.data = data;
        }

        public TreeNode(Object data, TreeNode left, TreeNode right, TreeNode parent) {
            this.data = data;
            this.left = left;
            this.right = right;
            this.parent = parent;
        }
    }

    private TreeNode root;

    // 以默认的构造器来创建二叉树
    public ThreeLinkBinTree() {
        this.root = new TreeNode();
    }

    // 以指定根元素来创建二叉树
    public ThreeLinkBinTree(E data) {
        this.root = new TreeNode(data);
    }

    /**
     * 为指定节点添加子节点
     * @param parent
     * @param data 新子节点的数据
     * @param isLeft 是否为左子节点
     * @return 新增的节点
     */
    public TreeNode addNode(TreeNode parent, E data, boolean isLeft) {
        if (parent == null) {
            throw new RuntimeException(parent + "节点为null,无法添加子节点");
        }
        if (isLeft && parent.left != null) {
            throw new RuntimeException(parent + "节点已有左子节点，无法添加");
        }
        if (!isLeft && parent.right != null) {
            throw new RuntimeException(parent + "节点已有右子节点，无法添加");
        }
        TreeNode newNode = new TreeNode(data);
        if (isLeft) {
            // 让父节点的left引用指向新节点
            parent.left = newNode;
        } else {
            parent.right = newNode;
        }
        // 让新节点parent引用引用到parent节点
        newNode.parent = parent;
        return newNode;
    }

    public boolean empty() {
        // 根据根元素来判断二叉树是否为空
        return root.data == null;
    }

    public TreeNode root() {
        if (empty()) {
            throw new RuntimeException("树为空，无法访问其根节点");
        }
        return this.root;
    }

    // 返回指定节点(非根节点)的父节点
    public E parent(TreeNode node) {
        if (node == null) {
            throw new RuntimeException(node + "节点为null,无法访问其父节点");
        }
        return (E)node.parent.data;
    }

    public E leftChild(TreeNode parent) {
        if (parent == null) {
            throw new RuntimeException(parent + "节点为空，无法添加子节点");
        }
        return parent.left == null ? null : (E) parent.left.data;
    }
    @SuppressWarnings("unchecked")
    public E rightChild(TreeNode parent) {
        if (parent == null) {
            throw new RuntimeException(parent + "节点为空，无法添加子节点");
        }
        return parent.right == null ? null : (E) parent.right.data;
    }

    public int deep() {
        return deep(this.root);
    }

    private int deep(TreeNode node) {
        if (node == null) {
            return 0;
        }
        // 没有子树
        if (node.right == null && node.left == null) {
            return 1;
        } else {
            int leftDeep = deep(node.left);
            int rightDeep = deep(node.right);
            // 记录其所有左右子树较大者的深度
            int max = leftDeep > rightDeep ? leftDeep : rightDeep;
            return max + 1;
        }
    }

    // 先序遍历 根 -> 左 -> 右
    public List<TreeNode> preIterator() {
        return preIterator(root);
    }

    private List<TreeNode> preIterator(TreeNode node) {
        List<TreeNode> list = new ArrayList<>();
        // 处理根节点
        list.add(node);
        // 递归处理左子树
        if (node.left != null) {
            list.addAll(preIterator(node.left));
        }
        if (node.right != null) {
            list.addAll(preIterator(node.right));
        }
        return list;
    }
    /*
        中序 左-> 根 -> 右
        后续 左 -> 右 -> 根
     */

    // 广度优先遍历
    public List<TreeNode> breadthFirst() {
        Queue<TreeNode> queue = new ArrayDeque<TreeNode>();
        List<TreeNode> list = new ArrayList<>();
        if (this.root != null) {
            // 将根元素加入 队列
            queue.offer(root);
        }
        while (!queue.isEmpty()) {
            // 将该队列的 头部 元素加入list中
            list.add(queue.peek());
            // 将该队列的 头部 元素移出队列
            TreeNode p = queue.poll();
            // 如果左子节点不为null, 将其加入队列
            if (p.left != null) {
                queue.offer(p.left);
            }
            // 如果右子节点不为null, 将其加入队列
            if (p.right != null) {
                queue.offer(p.right);
            }
        }
        return list;
    }
}
