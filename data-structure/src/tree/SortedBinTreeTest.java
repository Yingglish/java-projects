package tree;

public class SortedBinTreeTest {
    public static void main(String[] args) {
        SortedBinTree<Integer> tree = new SortedBinTree<>();
        tree.add(5);
        tree.add(20);
        tree.add(10);
        tree.add(3);
        tree.add(8);
        tree.add(15);
        tree.add(30);
        System.out.println(tree.breadthFirst());
        // 删除节点
        tree.remove(20);
        System.out.println(tree.breadthFirst());
        System.out.println(tree.inIterator()); // 采用中序遍历得到有序序列
    }
}
