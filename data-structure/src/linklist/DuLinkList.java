package linklist;

/**
 * 实现双向链表
 * @param <T>
 */
public class DuLinkList<T> {
    private class Node {
        private T data;
        private Node prev; //指向上一个节点的引用
        private Node next;

        public Node() {
        }

        public Node(T data, Node prev, Node next) {
            this.data = data;
            this.prev = prev;
            this.next = next;
        }
    }

    private Node header;
    private Node tail;
    private int size;

    // 创建空链表

    public DuLinkList() {
        header = null;
        tail = null;
    }

    // 以指定数据元素来创建链表，该链表只有一个元素
    public DuLinkList(T element) {
        header = new Node(element, null, null);
        // 只有一个节点，header都指向该节点
        tail = header;
        size++;
    }

    // 链表长度
    public int length() {
        return size;
    }

    // 获取链表索引为index处的元素
    public T get(int index) {
        return getNodeByIndex(index).data;
    }

    /**
     * 根据索引获取指定位置节点
     * @param index
     * @return
     */
    private Node getNodeByIndex(int index) {
        if(index < 0 || index > size - 1) {
            throw new IndexOutOfBoundsException("线性表索引越界");
        }
        if(index < size / 2) {
            // 从header节点开始
            Node current = header;
            for (int i = 0; i < size /2 && current != null; i++, current = current.next) {
                if(i == index) {
                    return current;
                }
            }
        } else {
            // 从tail节点开始搜索
            Node current = tail;
            for (int i = size - 1; i > size/2 && current != null ; i--, current = current.prev) {
                if(i == index) {
                    return current;
                }
            }
        }
        return null;
    }

    // 查找指定元素索引
    public int locate(T element) {
        // 从头节点开始搜索
//        Node current = this.header;
//        for (int i = 0; i < size && current != null; i++, current = current.next) {
//            if(current.data.equals(element)) {
//                return i;
//            }
//        }
        Node current = this.tail;
        for (int i = size - 1; i >= 0 && current != null; i--, current = current.prev) {
            if (current.data.equals(element)) {
                return i;
            }
        }
        return -1;
    }

    // 向指定位置插入元素
    public void insert(T element, int index) {
        if(index < 0 || index > size) {
            throw new IndexOutOfBoundsException("线性表索引越界");
        }
        // 如果还是空链表
        if(header == null) {
            add(element);
        } else {
            // 当index为0时
            if(index == 0) {
                addAtHeader(element);
            } else {
                //获取插入点的前一个节点
                Node prev = getNodeByIndex(index - 1);
                // 获取插入点的节点
                Node next = prev.next;
                // 让新节点的next引用指向next节点，pre引用指向pre节点
                Node newNode = new Node(element, prev , next);
                // 让prev的next指向新节点
                prev.next = newNode;
                // 让prev的下一个节点的prev指向新节点
                next.prev = newNode;
                size++;
            }
        }
    }

    // 采用尾插法为链表添加新节点
    public void add(T element) {
        // 如果链表为空列表
        if(header == null) {
            header = new Node(element, null, null);
            tail = header;
        } else {
            // 创建新节点，新节点pre引用指向原tail节点
            Node newNode = new Node(element, tail, null);
            // 让尾节点的next指向新增的节点
            tail.next = newNode;
            // 以新节点作为新的尾节点
            tail = newNode;
        }
        size++;
    }

    public void addAtHeader(T element) {
        // 创建新节点，让新节点的next指向与原来的header
        // 并以新节点作为新的header
        header = new Node(element, null, header);
        // 如果插入之前为空链表
        if(tail == null) {
            tail = header;
        }
        size++;
    }

    // 删除链表中指定索引处元素
    public T delete(int index) {
        if(index < 0 || index >size - 1) {
            throw new IndexOutOfBoundsException("线性表索引越界");
        }

        Node del = null;

        // 如果被删除的是header节点
        if(index == 0) {
            del = header;
            header = header.next;
            header.prev = null; // 释放header节点的prev引用
        } else {
            // 获取删除点的前一个节点
            Node prev = getNodeByIndex(index - 1);
            // 获取要被删除的节点
            del = prev.next;
            // 让被删除节点的next指向被删除节点的下一个节点
            prev.next = del.next;
            // 让被删除节点的下一个节点的prev指向prev节点
            if(del.next != null) {
                del.next.prev = prev;
            }
            // 将被删除节点的prev, next引用赋为null
            del.prev = null;
            del.next = null;
        }
        size--;
        return del.data;
    }

    // 删除链表最后一个元素
    public T remove() {
        return delete(size - 1);
    }

    // 判断链表是否为空
    public boolean empty() {
        return size == 0;
    }

    // 清空
    public void clear() {
        header = null;
        tail = null;
        size = 0;
    }

    @Override
    public String toString() {
        // 链表为空
        if(empty()) {
            return "[]";
        } else {
            StringBuilder sb = new StringBuilder("[");
            for (Node current = tail; current != null; current = current.prev) {
                sb.append(current.data.toString() + ", ");
            }
            int len = sb.length();
            return sb.delete(len - 2, len).append("]").toString();
        }
    }
}
