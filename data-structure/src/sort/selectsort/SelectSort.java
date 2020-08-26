package sort.selectsort;

import sort.ref.DataWrap;

/**
 * 平均时间复杂度：O(n^2)，空间复杂度：O(1) 属于不稳定排序
 */
public class SelectSort {
    public static void main(String[] args) {
        DataWrap[] data = {
                new DataWrap(21, ""),
                new DataWrap(30, ""),
                new DataWrap(49, ""),
                new DataWrap(30, "*"),
                new DataWrap(16, ""),
                new DataWrap(9, ""),
        };
        System.out.println( "排序前：\n"+ java.util.Arrays.toString(data));
        selectSort02(data); // [9, 16, 21, 30*, 30, 49]
        System.out.println( "排序后：\n"+ java.util.Arrays.toString(data));
    }
    /*
        每趟比较目的就是选择出本趟比较中最小的数据，并将该数据放在本趟比较的第一位
    */
    public static void selectSort02(DataWrap[] data) {
        System.out.println("开始排序");
        int arrLen = data.length;
        // 依次进行 n - 1次排序，第 i 躺比较将 比第 i 大的值选出放在 i 位置上
        for (int i = 0; i < arrLen - 1; i++) {
            // minIndex永远保留本趟比较中最小值的索引
            // 预设本趟排序的最小值索引为 第一个
            int minIndex = i;
            // 第 i 个数据只需和它后面的数据比较
            for (int j = i + 1; j < arrLen; j++) {
                // 如果第 minIndex 位置数据 > j位置数据
                if (data[minIndex].compareTo(data[j]) > 0) {
                    minIndex = j;
                }
            }
            if (minIndex != i) { // 如果最小值不是预设索引
                DataWrap tmp = data[i];
                data[i] = data[minIndex];
                data[minIndex] = tmp;
            }
            System.out.println(java.util.Arrays.toString(data));
        }
    }
}
