package github.yix.binarytree;

import github.yix.binarytree.ref.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.LinkedList;

/**
 * 二叉树层序遍历
 */
public class BinaryTreeLevelOrder {

    // prints in level order
    public static void levelOrderTraversal(TreeNode startNode) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(startNode);
        while (!queue.isEmpty()) {
            TreeNode tempNode = queue.poll();
            System.out.printf("%d ", tempNode.val);
            if (tempNode.left != null)
                queue.add(tempNode.left);
            if (tempNode.right != null)
                queue.add(tempNode.right);
        }
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        if(root == null)
            return new ArrayList<>();
        List<List<Integer>> res = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);
        while(!queue.isEmpty()){
            int count = queue.size();
            List<Integer> list = new ArrayList<Integer>();
            while(count > 0){
                TreeNode node = queue.poll();
                assert node != null;
                list.add(node.val);
                if(node.left != null)
                    queue.add(node.left);
                if(node.right != null)
                    queue.add(node.right);
                count--;
            }
            res.add(list);
        }
        return res;
    }

    // 自底向上的层次遍历 即按从叶子节点所在层到根节点所在的层，逐层从左向右遍历
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        LinkedList<List<Integer>> result = new LinkedList<>();
        if (root == null)
            return result;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            List<Integer> oneLevel = new ArrayList<>();
            // 每次都取出一层的所有数据
            int count = queue.size();
            for (int i = 0; i < count; i++) {
                TreeNode node = queue.poll();
                oneLevel.add(node.val);
                if (node.left != null)
                    queue.add(node.left);
                if (node.right != null)
                    queue.add(node.right);
            }
            // 每次都往队头塞
            result.addFirst(oneLevel);
        }
        return result;
    }

    public static void main(String[] args) {
        // Creating a binary tree
        TreeNode rootNode = TreeNode.createBinaryTree();
        System.out.println("Level Order traversal of binary tree will be:");
        levelOrderTraversal(rootNode);
    }
}
