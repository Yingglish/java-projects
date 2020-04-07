package stack;

public class LinkStack<T> {
    // 链栈节点
    class Node {
        private T data;
        private Node next;

        public Node() {
        }

        public Node(T data, Node next) {
            this.data = data;
            this.next = next;
        }
    }

    private Node top; // 用于保存栈顶元素
    private int size; // 栈中的节点数

    // 创建空链栈
    public LinkStack() {
        top = null;
    }

    public LinkStack(T element) {
        top = new Node(element, null);
        size++;
    }

    public int length() {
        return size;
    }

    // 进栈
    public void push(T element) {
        // 让top指向新创建的元素，新元素的next指向原栈顶元素 -- 类似头插法
        top = new Node(element, top);
        size++;
    }

    // 出栈
    public T pop() {
        Node oldTop = top;
        top = top.next;
        oldTop.next = null; // 释放原先top的next引用
        size--;
        return oldTop.data;
    }

    public T peek() {
        return top.data;
    }

    public boolean empty() {
        return size == 0;
    }

    public void clear() {
         top = null;
         size = 0;
    }

    @Override
    public String toString() {
        if(empty()) {
            return "[]";
        } else {
            StringBuilder sb = new StringBuilder("[");
            for (Node current = top ; current != null; current = current.next) {
                sb.append(current.data.toString() + " ,");
            }
            int len = sb.length();
            return sb.delete(len - 2, len).append("]").toString();
        }
    }
}
