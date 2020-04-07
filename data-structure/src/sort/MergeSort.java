package sort;


public class MergeSort {
    public static void mergeSort(DataWrap[] data) {

    }

    /**
     * 将索引left到right范围的数组进行归并排序
     * @param data 待排序的数组
     * @param left 待排序的数组的第一个元素的索引
     * @param right 待排序的数组的最后一个元素的索引
     */
    private static void sort(DataWrap[] data, int left, int right) {
        if (left < right) {
            // 找出中间索引
            int center = (left + right) / 2;
            // 对左边数组进行递归
            sort(data, left, center);
            // 对右边数组进行递归
            sort(data, center + 1, right);
            // 合并
        }
    }

    /**
     * 将俩个数组进行归并，归并前两个数组已经有序，归并后依然有效
     * @param data 数组对象
     * @param left 左数组的第一个元素索引
     * @param center 左数组的最高一个元素索引，则 center + 1 是右数组的第一个元素索引
     * @param right 右数组的最后一个元素索引
     */
    private static void merge(DataWrap[] data, int left, int center, int right) {
        // 定义一个与待排序序列长度相同的临时数组
        DataWrap[] tmpArr = new DataWrap[data.length];
        int mid = center + 1;
        // third记录中间数组的索引
        int third = left;
        int tmp = left;
        while (left < center && mid <= right) {
            // 从俩个数组取出小的放入中间数组
            if (data[left].compareTo(data[mid]) <= 0) {
                tmpArr[third++] = data[left++];
            } else {
                tmpArr[third++] = data[mid++];
            }
        }
        //剩余部分依次放入中间数组
        while (mid <= right) {
            tmpArr[third++] = data[mid++];
        }
        while (left <= center) {
            tmpArr[third++] = data[left++];
        }
    }
}
