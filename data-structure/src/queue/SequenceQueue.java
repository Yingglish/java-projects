package queue;

import java.util.Arrays;

/**
 * 队列 -- 只允许在队尾rear进行插入，在队头front进行删除
 * 使用一个固定长度的数组来实现，队列元素在elementData中的位置不会改变，改变的只是front,rear俩个变量 -- 会出现" 假满 " 问题
 * 当rear等于底层数组容量capacity时，如果此时向队列里添加元素，会引起”队列已满“异常
 * @param <T>
 */
public class SequenceQueue<T> {
    private int DEFAULT_SIZE = 10;
    private int capacity;
    private Object[] elementData;

    private int front = 0;
    private int rear = 0;

    // 以默认数组长度创建空顺序队列
    public SequenceQueue() {
        capacity = DEFAULT_SIZE;
        elementData = new Object[capacity];
    }

    public SequenceQueue(T element) {
        this();
        elementData[0] = element;
        rear++;
    }

    public SequenceQueue(T element, int initSize) {
        this.capacity = initSize;
        elementData = new Object[capacity];
        elementData[0] = element;
        rear++;
    }

    public int length() {
        return rear - front;
    }

    // 插入
    public void add(T element) {
        if(rear > capacity - 1) {
            throw new IndexOutOfBoundsException("队列已满");
        }
        elementData[rear++] = element;
    }

    // 移出
    public T remove() {
        if(empty()) {
            throw new IndexOutOfBoundsException("队列为空");
        }

        T oldValue =  (T)elementData[front];
        // 释放队列的front端元素
        elementData[front++] = null;
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
        return rear == front;
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
            StringBuilder sb = new StringBuilder("[");
            for (int i = front; i < rear; i++) {
                sb.append(elementData[i].toString() + " ,");
            }
            int len = sb.length();
            return sb.delete(len - 2, len).append("]").toString();
        }
    }
}
