package linklist;

/**
 * 实现单链表
 * @param <T>
 */
public class LinkList<T> {
    // 定义一个内部类Node, Node实例代表链表的节点
    private class Node {
        // 保存节点的数据
        private T data;
        // 指向下一个节点的引用
        private Node next;

        public Node() {
        }

        public Node(T data, Node next) {
            this.data = data;
            this.next = next;
        }

        @Override
        public String toString() {
            return this.data.toString();
        }
    }

    private Node header; // 头节点
    private Node tail; // 尾节点
    private int size;

    public Node getHeader() {
        return header;
    }

    public void setHeader(Node header) {
        this.header = header;
    }

    //创建空链表
    public LinkList() {
        // 空链表，header和tail都是null
        header = null;
        tail = null;
    }

    // 以指定元素来创建链表，该链表只有一个元素
    public LinkList(T element) {
        header = new Node(element, null);
        // 只有一个节点，header和tail都指向该节点
        tail = header;
        size++;
    }

    // 返回列表长度
    public int length() {
        return size;
    }

    // 获取链表中索引为index处的元素
    public T get(int index) {
        return getNodeByIndex(index).data;
    }

    // 根据索引index获取指定位置的节点
    private Node getNodeByIndex(int index) {
        if(index < 0 || index > size - 1) {
            throw new IndexOutOfBoundsException("线性表索引越界");
        }

        // 从header节点开始
        Node current = header;
        for (int i = 0; i < size && current != null; i++,current = current.next) {
            if(i == index) {
                return current;
            }
        }

        return null;
    }

    // 查找链式线性表指定元素索引
    public int locate(T element) {
        // 从头节点开始搜索
        Node current = header;
        for (int i = 0; i < size && current != null; i++,current = current.next) {
            if(current.data.equals(element)) {
                return i;
            }
        }
        return -1;
    }

    // 向链表指定位置插入元素
    public void insert(T element, int index) {
        if(index < 0 || index > size) {
            throw new IndexOutOfBoundsException("线性表索引越界");
        }
        // 如果为空链表
        if(header == null) {
            add(element);
        } else {
            // 当index 为0时， 也就是在链表头处churu
            if(index == 0) {
                addAtHeader(element);
            } else {
                // 获取插入点的前一个节点
                Node prev = getNodeByIndex(index - 1);
                // 让prev的next指向新节点
                // 让新节点的next指向原来prev的下一个节点
                prev.next = new Node(element, prev.next);
                size++;
            }
        }
    }

    // 采用尾插法尾链表添加新节点
    public void add(T element) { //TODO
        // 如果为空链表
        if(header == null) {
            header = new Node(element, null);
            tail = header;
        } else {
            // 创建新节点
            Node newNode = new Node(element, null);
            //让尾节点的next指向新增的节点
            tail.next = newNode;
            // 以新节点作为新的尾节点
            tail = newNode;
        }
        size++;
    }

    // 采用头插法
    public void addAtHeader(T element) {
        // 创建新节点，让新节点的next指向原来的header
        // 并以新节点作为新的header
        header = new Node(element, header); //TODO
        // 如果插入之前为空表
        if(tail == null) { //
            tail = header;
        }
        size++;
    }

    // 删除指定位置节点
    public T delete(int index) {
        if(index < 0 || index > size - 1) {
            throw new IndexOutOfBoundsException("线性表索引越界");
        }

        Node del = null;
        // 如果删除的时header节点
        if(index == 0) {
            del = header;
            header = header.next;
        } else {
            // 获取删除点的前一个节点
            Node prev = getNodeByIndex(index - 1);
            // 获取要被删除的节点
            del = prev.next;
            // 让被删除节点的next指向被删除节点的下一个节点
            prev.next = del.next;
            // 将被删除节点的next引用赋为null
            del.next = null; // ***
        }
        size--;
        return del.data;
    }

    // 删除链表的最后一个元素
    public T remove() {
        return delete(size - 1);
    }

    // 判断链表是否为空表
    public boolean empty() {
        return size == 0;
    }

    //  清空
    public void clear() {
        // header tail赋空
        header = null;
        tail = null;
        size = 0;
    }

    // 倒数第n个元素 滑动窗口
    public Node nthFromLastNode(Node head,int n) {
        Node firstPtr = head;
        Node secondPtr = head;

        for (int i = 0; i < n; i++) {
            firstPtr=firstPtr.next;
        }

        while(firstPtr!=null)
        {
            firstPtr=firstPtr.next;
            secondPtr=secondPtr.next;
        }

        return secondPtr;
    }
    @Override
    public String toString() {
        if(empty()) {
            return "[]";
        } else {
            StringBuilder sb = new StringBuilder("[");
            for (Node current = header; current != null; current = current.next) {
                sb.append(current.data.toString() + ", ");
            }
            return sb.delete(sb.length() - 2, sb.length()).append("]").toString();
        }
    }

    /**
     * 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表
     * @param head
     * @return
     */
    public Node swapPairs(Node head) {
        // 定义一个头节点的虚拟前节点
        Node pre = new Node();
        pre.next = head;
        Node tmp = pre;
        // 设置需要交换的俩个节点分别为 start、end
        while(tmp.next != null && tmp.next.next != null) {
            Node start = tmp.next;
//            Node end = start.next;
            Node end = tmp.next.next;
            // 进行交换
            tmp.next = end;
            start.next = end.next;

            end.next = start;
            tmp = start;
        }
        header = pre.next;
        return header; // 返回新的头节点
    }

    /**
     * 设需要交换的两个点为 head 和 next，head 连接后面交换完成的子链表，next 连接 head，完成交换
     * @param head
     * @return
     */
	public Node swapPairs2(Node head) {
        if(head == null || head.next == null) { // 终止条件：head 为空指针或者 next 为空指针，也就是当前无节点或者只有一个节点，无法进行交换
            return head;
        } else {
            Node next = head.next;
            head.next = swapPairs(next.next); // 接受上一级返回的子链表
            next.next = head;
            return next; // 返回此次交换俩个节点中的头节点
        }
    }
}
