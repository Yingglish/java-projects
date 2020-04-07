package tree;

public class ArrayBinTree<T> {
    // 使用数组来记录该树所有节点
    private Object[] data ;
    private int DEFAULT_DEEP = 8;
    // 保存该树的深度
    private int deep;
    private int arraySize;

    // 以默认深度来创建二叉树
    public ArrayBinTree() {
        this.deep = this.DEFAULT_DEEP;
        this.arraySize = (int)Math.pow(2, deep) - 1;
        data = new Object[arraySize];
    }

    // 指定深度
    public ArrayBinTree(int deep) {
        this.deep = deep;
        this.arraySize = (int)Math.pow(2, deep) - 1;
        data = new Object[arraySize];
    }

    // 指定深度和根节点

    public ArrayBinTree(int deep, T element) {
//        this.deep = deep;
//        this.arraySize = (int)Math.pow(2, deep) - 1;
//        data = new Object[arraySize];
        this(deep);
        data[0] = element;
    }

    /**
     * 为指定节点添加子节点
     * @param index 需要添加父节点的索引
     * @param element 新节点的数据
     * @param left 是否为左节点
     */
    public void add(int index, T element, boolean left){
        if (data[index] == null) {
            throw new RuntimeException(index + "处节点为空，无法添加子节点");
        }
        if (2 * index + 1 >= arraySize) {
            throw new RuntimeException("底层数组已满，树越界异常");
        }
        // 添加左子节点
        if (left) {
            data[2 * index + 1] = element;
        } else {
            data[2 * index + 2] = element;
        }
    }

    // 判断二叉树是否为空
    public boolean empty() {
        // 根据根元素来判断二叉树是否为空
        return data[0] == null;
    }

    // 返回根节点
    @SuppressWarnings("unchecked")
    public T root() {
        return (T)data[0];
    }

    // 返回指定节点(非根节点)的父节点
    public T parent(int index) {
        return (T)data[(index - 1) / 2];
    }

    // 返回指定节点(非叶子节点)的左子节点，不存在时返回null
    public T left(int index) {
        if(2 * index + 1 >= arraySize) {
            throw new  RuntimeException("该节点为叶子节点，无子节点");
        }
        return (T)data[index * 2 + 1];
    }

    public T right(int index) {
        if(2 * index + 1 >= arraySize) {
            throw new  RuntimeException("该节点为叶子节点，无子节点");
        }
        return (T)data[index * 2 + 2];
    }

    // 深度
    public int deep(int index) {
        return this.deep;
    }

    // 返回指定节点的位置
    public int pos(T element) {
        // 该循环实际上时按照广度遍历来搜索每个节点
        for (int i = 0; i < arraySize; i++) {
            if (data[i].equals(element)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public String toString() {
        return java.util.Arrays.toString(data);
    }
}
