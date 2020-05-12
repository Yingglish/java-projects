package sort;

import sort.ref.DataWrap;

public class BubbleSort {
    public static void bubbleSort(DataWrap[] data) {
        System.out.println("开始排序");
        int arrLen = data.length;
        for (int i = 0; i < arrLen - 1; i++) {
            boolean flag = false; // 增加一个bool值，用于判断一次循环后是否有数据交换，如果没有，则退出排序

            // 从头开始比较每一对相邻元素，如果第一个比第二个大，就交换它们的位置 执行完一轮后，最末尾那个元素就是最大元素
            for (int j = 0; j < arrLen - 1 - i; j++) {
                // 如果 j 索引处元素大于 j+1索引处元素
                if (data[j].compareTo(data[j + 1]) > 0) {
                    DataWrap tmp = data[j + 1];
                    data[j + 1] = data[j];
                    data[j] = tmp;
                    flag = true;
                }
//                System.out.println(java.util.Arrays.toString(data));
            }
            if (!flag) { // 如果某趟未发生交换，则表明已处于有序状态
                break;
            }
        }
    }

    // 如果序列尾部已经局部有序，可以记录最后一次交换的位置，减少比较次数
    public static void bubbleSort_2(DataWrap[] data) {
        System.out.println("开始排序");
        int arrLen = data.length;
        for (int i = arrLen - 1; i > 0 ; i--) {
            // sortedIndex的初始值在数组完全有序的时候有用
            int sortedIndex = 1;
            for (int j = 0; j < i; j++) {
                if (data[j].compareTo(data[j + 1]) > 0) {
                    DataWrap tmp = data[j + 1];
                    data[j + 1] = data[j];
                    data[j] = tmp;
                    sortedIndex = j+1 ;
                }
//                System.out.println(java.util.Arrays.toString(data));
            }
            i = sortedIndex;
        }
    }

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
        System.out.println(data[2].compareTo(data[3]));
        System.out.println( "排序前：\n"+ java.util.Arrays.toString(data));
        bubbleSort_2(data);
        System.out.println( "排序后：\n"+ java.util.Arrays.toString(data));
    }
}
