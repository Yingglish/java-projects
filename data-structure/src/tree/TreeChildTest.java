package tree;

import java.util.List;

public class TreeChildTest {
    public static void main(String[] args) {
        TreeChild<String> tp = new TreeChild<>("root");
        TreeChild.Node root = tp.root();
        System.out.println("根节点：" + root);
        tp.addNode("节点1",root);
        tp.addNode("节点2",root);
        tp.addNode("节点3",root);
        System.out.println("添加子节点后根节点：" + root);


        List<TreeChild.Node<String>> nodes = tp.children(root);
        System.out.println("根节点的所有子节点" + nodes);

        tp.addNode("节点4", nodes.get(0));
        System.out.println("深度：" + tp.deep());
    }
}
