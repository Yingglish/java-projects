package queue;

public class LinkQueue<T> {
    private class Node {
        private T data;
        private Node next; // 下一个节点的引用

        public Node() {
        }

        public Node(T data, Node next) {
            this.data = data;
            this.next = next;
        }
    }

    private Node front;
    private Node rear;
    private int size;

    // 创建空链队列
    public LinkQueue() {
        // 空链队列 front 与 rear都为null
        front = null;
        rear = null;
    }

    public LinkQueue(T element) {
        front = new Node(element, null);
        rear = front;
        size++;
    }

    // 返回链队列长度
    public int length() {
        return size;
    }

    // 添入
    public void add(T element) {
        if(front == null) { // 如果队列为空链队列
            front = new Node(element, null);
            rear = front;
        } else {
            Node newNode = new Node(element, null);
            // 让尾节点的next指向新节点
            rear.next = newNode;
            // 以新节点作为新的尾节点
            rear = newNode;
        }
        size++;
    }

    // 删除队列front端元素
    public T remove() {
        Node oldFront = front;
        front = front.next;
        oldFront.next = null;
        size--;
        return oldFront.data;
    }

    // 访问队列中最后一个元素
    public T element() {
        return rear.data;
    }

    public boolean empty() {
        return size == 0;
    }

    @Override
    public String toString() {
        if(empty()) {
            return "[]";
        } else {
            StringBuilder sb = new StringBuilder("[");
            for (Node current = front; current != null; current = current.next) {
                sb.append(current.data.toString()).append(" ,");
            }
            int len = sb.length();
            return sb.delete(len - 2, len).append("]").toString();
        }
    }
}

