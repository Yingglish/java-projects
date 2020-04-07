package queue;

import java.util.Arrays;

public class LoopQueue<T> {
    private int DEFAULT_SIZE = 10;
    private int capacity;
    private Object[] elementData;

    private int front = 0;
    private int rear = 0;

    // 以默认数组长度创建空顺序队列
    public LoopQueue() {
        capacity = DEFAULT_SIZE;
        elementData = new Object[capacity];
    }

    public LoopQueue(T element) {
        this();
        elementData[0] = element;
        rear++;
    }

    public LoopQueue(T element, int initSize) {
        this.capacity = initSize;
        elementData = new Object[capacity];
        elementData[0] = element;
        rear++;
    }

    public int length() {
        if(empty()) {
            return 0;
        }
        return rear > front ? rear - front : capacity - (front - rear);
    }

    // 插入
    public void add(T element) {
        if(rear == front && elementData[front] != null) {
            throw new IndexOutOfBoundsException("队列已满");
        }
        elementData[rear++] = element;
        // 如果rear 已经到头，那就转头
        rear = rear == capacity ? 0 : rear;
    }

    // 移出
    public T remove() {
        if(empty()) {
            throw new IndexOutOfBoundsException("队列为空");
        }

        T oldValue =  (T)elementData[front];
        // 释放队列的front端元素
        elementData[front++] = null;

        // 如果front已经到头，那就转头
        front = front == capacity ? 0 : front;
        return oldValue;
    }

    // 返回队列顶元素但不删除
    public T element() {
        if(empty()) {
            throw new IndexOutOfBoundsException("队列为空");
        }
        return (T)elementData[front];
    }

    public boolean empty() {
        return rear == front && elementData[rear] == null;
    }

    public void clear() {
        Arrays.fill(elementData, null);
        front = 0;
        rear = 0;
    }

    @Override
    public String toString() {
        if(empty()) {
            return "[]";
        } else {
            if(front < rear) { // 如果front < rear 有效元素为 front -> rear
                StringBuilder sb = new StringBuilder("[");
                for (int i = front; i < rear; i++) {
                    sb.append(elementData[i].toString() + " ,");
                }
                return sb.delete(sb.length() - 2, sb.length()).append("]").toString();
            } else { // 如果front >= rear , 那么有效元素为 front -> capacity 之间 和 0 -> front之间的元素
                StringBuilder sb = new StringBuilder("[");
                for (int i = front; i < capacity; i++) {
                    sb.append(elementData[i].toString() + " ,");
                }
                for (int i = 0; i < front; i++) {
                    sb.append(elementData[i].toString() + " ,");
                }
                int len = sb.length();
                return sb.delete(len - 2, len).append("]").toString();
            }
        }
    }
}
