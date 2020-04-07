package tree;

import java.util.List;

public class TreeParentTest {
    public static void main(String[] args) {
        TreeParent<String> tp = new TreeParent<>("root");
        TreeParent.Node root = tp.root();
        System.out.println(root);
        tp.addNode("节点1",root);
        System.out.println("此树深度：" + tp.deep());
        tp.addNode("节点2",root);

        List<TreeParent.Node<String>> nodes = tp.children(root);
        System.out.println("子节点：" + nodes);
        tp.addNode("节点3", nodes.get(0));
        System.out.println("此树深度：" + tp.deep());
    }
}
