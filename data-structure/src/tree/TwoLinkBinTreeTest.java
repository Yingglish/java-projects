package tree;

public class TwoLinkBinTreeTest {
    public static void main(String[] args) {
        TwoLinkBinTree<String> binTree = new TwoLinkBinTree<>("root");
        TwoLinkBinTree.TreeNode root = binTree.root();
        TwoLinkBinTree.TreeNode tn1 = binTree.addNode(root, "第二层左节点",true);
        TwoLinkBinTree.TreeNode tn2 = binTree.addNode(root, "第二层右节点",false);

        System.out.println(binTree.leftChild(root));
    }
}
