package stack;


import java.util.Arrays;

/**
 * 使用数组实现顺序栈
 * @param <T>
 */
public class  SequenceStack<T> {

    private int DEFAULT_SIZE = 10;
    private int capacity;
    private int capacityIncrement = 0; // 底层数组容量不够时，程序每次增加的数组长度

    private Object[] elementData; // 底层数组
    private int size;

    //以默认数组长度创建空顺序栈
    public SequenceStack() {
        capacity = DEFAULT_SIZE;
        elementData = new Object[capacity];
    }

    // 以一个初始化元素来创建顺序栈
    public SequenceStack(T element) {
        this();
        elementData[0] = element;
        size++;
    }

    /**
     * 以指定长度的数组来创建数据栈
     * @param element 指定数据栈中的第一个元素
     * @param initSize 底层数组长度
     */
    public SequenceStack(T element, int initSize) {
        this.capacity = initSize;
        elementData = new Object[capacity];
        elementData[0] = element;
        size++;
    }

    /**
     * 以指定长度的数组来创建顺序栈
     * @param element
     * @param initSize
     * @param capacityIncrement
     */
    public SequenceStack(T element, int initSize, int capacityIncrement) {
        this.capacity = initSize;
        this.capacityIncrement = capacityIncrement;
        elementData = new Object[capacity];
        elementData[0] = element;
        size++;
    }

    public int length() {
        return this.size;
    }

    // 入栈
    public void push(T element) {
        ensureCapacity(size + 1);
        elementData[size++] = element;
    }

    private void ensureCapacity(int minCapacity) {
        // 如果数组的原有长度小于目前所需长度 minCapacity
        if(minCapacity > capacity) {
            if(capacityIncrement > 0) {
                while (minCapacity > capacity) {
                    capacity += capacityIncrement;
                }
            } else {
                while (minCapacity > capacity) {
                    capacity <<= 1;
                }
            }
        }
        elementData = Arrays.copyOf(elementData, capacity);
    }

    // 出栈
    public T pop() {
        T oldValue = (T)elementData[size - 1];
        elementData[--size] = null; //释放栈顶元素
        return oldValue;
    }

    // 返回栈顶元素
    public T peek() {
        return (T)elementData[size - 1];
    }

    // 清空
    public void clear() {
        Arrays.fill(elementData,null); // 将底层所有元素赋为 null
        size = 0;
    }

    @Override
    public String toString() {
        if(size == 0) {
            return "[]";
        } else {
            StringBuilder sb = new StringBuilder("[");
            for (int i = size - 1; i > -1 ; i--) {
                sb.append(elementData[i].toString() + " ,");
            }
            int len = sb.length();
            return sb.delete(len - 2, len).append("]").toString();
        }
    }
}
