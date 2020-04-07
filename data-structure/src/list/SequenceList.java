package list;

import java.util.Arrays;

/**
 * @author 10859
 * 利用数组实现顺序结构线性表
 */
public class SequenceList<T> {
    private int DEFAULT_SIZE = 16;
    // 保存数组的长度
    private int capacity;
    // 定义一个数组保存顺序线性表的元素
    private Object[] elementData;
    // 保存顺序线性表中元素的当前个数
    private int size = 0;

    // 以默认数组长度创建空顺序线性表
    public SequenceList() {
        this.capacity = this.DEFAULT_SIZE;
        elementData = new Object[this.capacity];
    }

    // 以一个初始化元素来创建顺序线性表
    public SequenceList(T element) {
        this(); // 先进行初始化
        elementData[0] = element;
        size++;
    }
    /**
     * 以指定长度的数组来创建顺序线性表
     * @param element 指定顺序线性表中的第一个参数
     * @param  initSize 指定顺序线性表底层数组的长度
     */
    public SequenceList(T element, int initSize) {
        capacity = 1;
        // 把capacity 设为大于initSize的最小的2的n次方
        while (capacity < initSize) {
            capacity <<= 1;
        }
        elementData = new Object[capacity];
        elementData[0] = element;
        size++;
    }
    /**
     * 返回顺序线性表的大小
     */
    public int size() {
        return size;
    }

    // 获取顺序线性表中索引为i处的元素
    @SuppressWarnings("unchecked")
    public T get(int i) {
        if(i < 0 || i > size - 1) {
            throw new IndexOutOfBoundsException("线性表索引越界");
        }
        return (T)elementData[i];
    }

    // 查找顺序线性表指定元素的索引
    public int locate(T element) {
        for (int i = 0; i < size; i++) {
            if (elementData[i].equals(element)) {
                return i;
            }
        }
        return -1;
    }

    // 向线性表指定位置插入一个元素
    public void insert(T element, int index) {
        if(index < 0 || index > size) {
            throw new IndexOutOfBoundsException("线性表索引越界");
        }
        ensureCapacity(size + 1);

        /*
            public static native void arraycopy(Object src,int srcPos, Object dest, int destPos,int length);

            该方法用了native关键字，说明调用的是其他语言写的底层函数

            src - 源数组。
            srcPos - 源数组中的起始位置。
            dest - 目标数组。
            destPos - 目标数据中的起始位置。
            length - 要复制的数组元素的数量
         */
        // 将index 处所有元素往后移动
        System.arraycopy(elementData, index, elementData, index+1, size -index); //TODO 弄清用法 System.arraycopy()
        elementData[index] = element;
        size++;

    }

    // 在线性表的开始处添加一个元素
    public void add(T element) {
        insert(element, size);
    }

    // 扩充底层数组长度，很麻烦，性能差
    private void ensureCapacity(int minCapacity) {
        // 如果数组的原有长度小于目前所需的长度
        if(minCapacity > capacity) {
            // 不断地将 capacity * 2，直到 capacity > minCapacity
            while (capacity < minCapacity){
                capacity <<= 1;
            }
            /*
                新旧数组都是指向同一个引用
             */
            elementData = Arrays.copyOf(elementData,capacity); //TODO 弄清用法 Arrays.copyOf
        }
    }

    // 删除顺序线性表指定索引处的元素
    public T delete(int index) {
        if(index < 0 || index > size - 1) {
            throw new IndexOutOfBoundsException("线性表索引越界");
        }

        T oldValue = (T)elementData[index];
        int numMoved = size - index - 1;
        if(numMoved > 0) {
            System.arraycopy(elementData, index + 1, elementData, index, numMoved);
        }
        // 清空最后一个元素 同时表长 - 1
        elementData[--size] = null;
        return  oldValue;
    }

    // 删除顺序线性表中的最后一个元素
    public T remove() {
        return delete(size - 1);
    }

    // 判断是否为空表
    public boolean isEmpty() {
        return size == 0;
    }

    // 清空线性表
    public void clear() {
        // 将底层数组中的所有元素赋为null
        Arrays.fill(elementData, null);
        size = 0;
    }


    public String toString() {
        if(size == 0 ) {
            return "[]";
        } else {
            StringBuilder sb = new StringBuilder("[");
            for (int i = 0; i < size; i++) {
                sb.append(elementData[i].toString() + ", ");
            }

            int len = sb.length();
            return sb.delete(len-2,len).append("]").toString();
        }
    }

}
