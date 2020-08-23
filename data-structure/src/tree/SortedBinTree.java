package tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * 排序二叉树
 * 1. 若它的左子树不为空，则左子树上所有节点的值要均小于根节点的值；
 * 2. 若它的右子树不为空，则右子树上所有节点的值要均大于根节点的值；
 * 3. 左、右子树也分别是排序二叉树
 *
 * @param <T>
 */
public class SortedBinTree<T extends Comparable> {

    static class Node {
        Object data;
        Node parent;
        Node left;
        Node right;

        public Node(Object data, Node parent, Node left, Node right) {
            this.data = data;
            this.parent = parent;
            this.left = left;
            this.right = right;
        }

        public String toString() {
            return "[data=" + data + "]";
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }

            if (obj.getClass() == Node.class) {
                Node target = (Node) obj;
                return data.equals(target.data) && left == target.left
                        && right == target.right && parent == target.parent;
            }

            return false;
        }

    }

    private Node root;

    public SortedBinTree() {
        root = null;
    }

    /*
         创建排序二叉树的步骤就是不断像排序二叉树中添加新节点（p）的过程
        （1）以根节点（root）为当前节点（current）开始搜索；
        （2）用新节点p的值和current的值进行比较；
        （3）如果p.data>current.data，则current=current.right；若p.data < current.data，则current=current.left；
        （4）重复（2）（3），直到找到合适的叶子节点位置；
        （5）将p添加到上面找到的合适位置，若新节点更大，则添加为右子节点；否则，加为左子节点
     */
    public SortedBinTree(T o) {
        root = new Node(o, null, null, null);
    }

    /**
     * 添加节点
     *
     * @param ele 新节点的元素
     */
    public void add(T ele) {
        if (root == null) {
            root = new Node(ele, null, null, null);
        } else {
            Node current = root;
            Node parent = null;
            int cmp = 0;

            //搜索合适的叶子节点，以该叶子节点为父节点添加新节点
            do {
                parent = current;
                cmp = ele.compareTo(current.data);

                //如果新节点的值大于当前节点的值
                if (cmp > 0) {
                    //以当前节点的右子节点作为当前节点
                    current = current.right;
                } else {
                    current = current.left;
                }
            } while (current != null);

            //创建新节点
            Node newNode = new Node(ele, parent, null, null);

            //如果新节点的值大于父节点的值
            if (cmp > 0) {
                parent.right = newNode;
            } else {
                parent.left = newNode;
            }
        }
    }

    /**
     * 删除节点
     *
     * 设待删除节点p，p的父节点q，p的左子树pL，p的右子树pR
     *
     * （1）p是叶子节点，直接将它从其父节点中删除；
     *
     * （2）p只有左（右）子树，将pL（pR）添加成p的父节点q的左（右）子树即可；
     *
     * （3）p左右子树均非空，有两种处理方法：
     *      1. 将pL设为q的左或右子节点（取决于p是其父节点q的左、右子节点），将pR设为p的中序前驱结点s的右子节点（s是pL最右下的节点，也就是pL中最大的节点）
     *      2. 以p的中序前驱或后继替代p所指节点，然后再从原排序二叉树中删去中序前驱或后继节点（也就是用大于p的最小节点或小于p的最大节点代替p节点）
     * @param ele
     */
    public void remove(T ele) {
        // 获取要删除的节点
        Node target = getNode(ele);

        if (target == null) {
            return;
        }

        //左右子树都为空
        if (target.left == null && target.right == null) {
            if (target == root) {
                root = null;
            } else {
                //被删除节点是父节点的左子节点
                if (target == target.parent.left) {
                    //将target的父节点的left设为null
                    target.parent.left = null;
                } else {
                    target.parent.right = null;
                }

                target.parent = null;
            }
        } else if (target.left == null && target.right != null) {  // 要删除的节点只有右子树
            if (target == root) {
                root = target.right;
            } else {
                //被删除节点是父节点的左子节点
                if (target == target.parent.left) {
                    target.parent.left = target.right;
                } else {
                    target.parent.right = target.right;
                }

                //让target的右子树的parent指向target的parent
                target.right.parent = target.parent;
            }
        } else if (target.left != null && target.right == null) {  // 要删除的节点只有左子树
            if (target == root) {
                root = target.left;
            } else {
                //被删除节点是父节点的左子节点
                if (target == target.parent.left) {
                    target.parent.left = target.left;
                } else {
                    target.parent.right = target.left;
                }

                //让target的右子树的parent指向target的parent
                target.left.parent = target.parent;
            }
        } else { // 要删除的节点左右都不为空
            //leftMaxNode:target的左子树中值最大的节点
            Node leftMaxNode = target.left;

            //搜索target的左子树中值最大的节点
            while (leftMaxNode.right != null) {
                leftMaxNode = leftMaxNode.right;
            }

            //从原来的子树中删除leftMaxNode节点
            leftMaxNode.parent.right = null;

            leftMaxNode.parent = target.parent;

            if (target == target.parent.left) {
                target.parent.left = leftMaxNode;
            } else {
                target.parent.right = leftMaxNode;
            }

            leftMaxNode.left = target.left;
            leftMaxNode.right = target.right;
            target.parent = target.left = target.right = null;
        }
    }

    /**
     * 根据指定值搜索节点
     *
     * @param ele 指定值
     * @return 节点
     */
    public Node getNode(T ele) {
        //从根节点开始搜索
        Node p = root;
        while (p != null) {
            int cmp = ele.compareTo(p.data);

            if (cmp < 0) {
                p = p.left;
            } else if (cmp > 0) {
                p = p.right;
            } else {
                return p;
            }
        }

        return null;
    }

    /**
     * 广度优先遍历
     *
     * @return
     */
    public List<Node> breadthFirst() {
        Queue<Node> queue = new ArrayDeque<Node>();
        List<Node> list = new ArrayList<Node>();

        if (root != null) {
            queue.offer(root);
        }

        while (!queue.isEmpty()) {
            //将该队列的“队尾”元素添加到List中
            list.add(queue.peek());
            //弹出队尾节点
            Node p = queue.poll();

            //如果左子节点不为null，将它加入“队列”
            if (p.left != null) {
                queue.offer(p.left);
            }

            if (p.right != null) {
                queue.offer(p.right);
            }
        }

        return list;
    }

    /**
     * 中序遍历
     *
     * @return
     */
    public List<Node> inIterator() {
        return inIterator(root);
    }

    private List<Node> inIterator(Node node) {
        List<Node> list = new ArrayList<Node>();

        //递归处理左子树
        if (node.left != null) {
            list.addAll(inIterator(node.left));
        }

        //处理根节点
        list.add(node);

        //递归处理右子树
        if (node.right != null) {
            list.addAll(inIterator(node.right));
        }

        return list;
    }

}
