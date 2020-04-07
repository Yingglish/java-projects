package tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class HuffmanTree {
    public static class Node<E> {
        E data;
        double weight;
        Node<E> leftChild;
        Node<E> rightChild;

        public Node(E data, double weight) {
            this.data = data;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "data=" + data +
                    ", weight=" + weight +
                    '}';
        }
    }
	public static void main(String[] args) {
		List<Node<String>> nodes = new ArrayList<>();

		nodes.add(new Node("A", 40.0));
		nodes.add(new Node("B", 8.0));
		nodes.add(new Node("C", 10.0));
		nodes.add(new Node("D", 30.0));
		nodes.add(new Node("E", 10.0));
		nodes.add(new Node("F", 2.0));
		
		Node root = HuffmanTree.createTree(nodes);
		System.out.println(root);
		System.out.println(breadthFirst(root));
    }

        /**
         * 构造哈夫曼树
		 * @param <E> 声明一个泛型E
         * @param nodes 节点集合
         * @return 构造出来的哈夫曼树的根节点
         */
        private static <E> Node<E> createTree(List<Node<E>> nodes) {
            // 只要nodes数组还有俩个以上的节点
            while (nodes.size() > 1) {
                quickSort(nodes);
				// 获取权值最小的俩个点
				Node<E> left = nodes.get(nodes.size() - 1);
				Node<E> right = nodes.get(nodes.size() - 2);

				// 生成新节点，让新节点的权值为俩个子节点的权值之和
				Node<E> parent = new Node<>(null, left.weight + right.weight);

				// 让新节点作为权值最小的俩节点的父节点
				parent.leftChild = left;
				parent.rightChild = right;
				// 删除权值最小的俩点
				nodes.remove(nodes.size() - 1);
				nodes.remove(nodes.size() - 1);
				nodes.add(parent);
            }
			return nodes.get(0); // 返回nodes集合中唯一节点，也就是根节点
        }

		// 将指定数组的i和j索引处的元素交换
		private static <E> void swap(List<Node<E>> nodes, int i, int j) {
			Node<E> tmp;
			tmp = nodes.get(i);
			nodes.set(i, nodes.get(j));
			nodes.set(j, tmp);
		}
        // 实现快速排序算法，用于对节点进行排序
        private static <E> void subSort(List<Node<E>> nodes, int start, int end) {
            // 需要排序
            if (start < end) {
                // 以第一个元素作为分界值
                Node base = nodes.get(start);
                // i从左边搜索，搜索大于分界值的元素索引
                int i = start;
                // j从右边搜索，搜索小于分界值的元素索引
                int j = end + 1;
                while (true) {
                    // 找到大于分界值的元素的索引，或 i 已经到达end处
                    while (i < end && nodes.get(++i).weight >= base.weight);
                    // 找到小于分界值的元素的索引，或 j 已经到达start处
                    while (j > start && nodes.get(--j).weight <= base.weight);
                    if (i < j) {
						swap(nodes, i, j);
                    } else {
						break;
					}
                }
				swap(nodes, start, j);
				// 递归左子序列
				subSort(nodes, start, j - 1);
				// 递归右子序列
				subSort(nodes, j + 1, end);
            }
        }
	public static <E> void quickSort(List<Node<E>> nodes) {
		subSort(nodes, 0 , nodes.size() - 1);
	}

	// 广度优先遍历
	public static List<Node> breadthFirst(Node root) {
		Queue<Node> queue = new ArrayDeque<>();
		List<Node> list = new ArrayList<>();
		if (root != null) { 
			// 将根元素加入队列
			queue.offer(root);
		}
		while(!queue.isEmpty()) {
			list.add(queue.peek());
			Node p = queue.poll();
			if (p.leftChild != null) {
				queue.offer(p.leftChild);
			}
			if (p.rightChild != null) {
				queue.offer(p.rightChild);
			}
		}
		return list;
	}
}
