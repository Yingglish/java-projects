package sort.selectsort;

import sort.ref.DataWrap;

/**
 *  堆排序可以认为是对直接选择排序的一种优化，可以减少选择排序中的比较次数，进而减少排序时间
 *
 *  堆的概念
 *      堆树是一颗完全二叉树
 *      堆树中某个节点的值总是不大于或不小于其孩子节点的值
 *      堆树中每个节点的子树都是堆树
 *      当父节点的键值总是大于或等于任何一个子节点的键值时为最大堆，当父节点的键值总是小于或等于任何一个子节点的键值时为最小堆
 */
public class HeapSort {

    public static void main(String[] args) {
        DataWrap[] data = {
                new DataWrap(21, ""),
                new DataWrap(30, ""),
                new DataWrap(49, ""),
                new DataWrap(30, "*"),
                new DataWrap(21,"*"),
                new DataWrap(16, ""),
                new DataWrap(9, ""),
        };
        System.out.println( "排序前：\n"+ java.util.Arrays.toString(data));
        heapSort(data);
        System.out.println( "排序后：\n"+ java.util.Arrays.toString(data));
    }

    /**
     * 建堆
     * 拿堆的根结点和最后一个结点交换 就可以将最大值放入尾部
     */
    public static void heapSort(DataWrap[] data) {
        System.out.println("开始排序");
        int arrLen = data.length;
        // 循环建堆
        for (int i = 0; i < arrLen - 1; i++) {
            // 建堆
            buildMaxHeap(data, arrLen - 1 - i);
            // 交换堆顶和最后一个元素
            swap(data, 0 , arrLen - 1 - i);
            System.out.println(java.util.Arrays.toString(data));
        }
    }

    /**
     * 对data数组从 0 到 lastIndex建大顶堆
     *      大顶堆概念: k i > k 2i+1 && k i > k 2i+2
     */
    private static void buildMaxHeap(DataWrap[] data, int lastIndex) {
        // 从lastIndex处节点（最后一个节点）的父节点开始
        for (int i = (lastIndex - 1) / 2; i >= 0 ; i--) {
            // k 保存当前正在判断的节点
            int k = i;
            while (k * 2 + 1 <= lastIndex) {// 当前k节点的子节点存在
                // 记录k 节点的左子节点索引
                int biggerIndex = 2 * k + 1;

                // 如果biggerIndex小于lastIndex, 则biggerIndex + 1
                if (biggerIndex < lastIndex) {  // 代表 k 节点的右子节点存在
                    if (data[biggerIndex].compareTo(data[biggerIndex + 1]) < 0) { // 如果右子节点的值比较大
                        // 序号+1 指向右子树
                        // bigger总是记录较大子节点的索引
                        biggerIndex++;
                    }
                }

                // 如果k节点的值小于较大节点的值
                if (data[k].compareTo(data[biggerIndex]) < 0) {
                    // 交换他们
                    swap(data, k , biggerIndex);
                    // 将biggerIndex赋给k,开始while循环的下一次循环
                    // 重新保证k节点的值大于其左右子节点
                    k = biggerIndex;
                } else {
                    break;
                }
            }
        }
    }

    // 交换data数组i,j俩处的元素
    private static void swap(DataWrap[] data,int i,int j) {
        DataWrap tmp = data[i];
        data[i] = data[j];
        data[j] = tmp;
    }
}
