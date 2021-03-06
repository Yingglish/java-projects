package tree;

/**
 * 二叉链表实现二叉树
 * @param <E>
 */
public class TwoLinkBinTree<E> {
    public static class TreeNode {
        Object data;
        TreeNode left;
        TreeNode right;

        public TreeNode() {
        }

        public TreeNode(Object data) {
            this.data = data;
        }

        public TreeNode(Object data, TreeNode left, TreeNode right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }
    }

    private TreeNode root;

    // 以默认的构造器来创建二叉树
    public TwoLinkBinTree() {
        this.root = new TreeNode();
    }

    // 以指定根元素来创建二叉树
    public TwoLinkBinTree(E data) {
        this.root = new TreeNode(data);
    }

    /**
     * 为指定节点添加子节点
     * @param parent 需要添加子节点的父节点的索引
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
    public TreeNode parent(TreeNode node) {
        // 对于二叉链表存储法，如果要访问指定节点的父节点，必须遍历二叉树
        return null;
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
}
