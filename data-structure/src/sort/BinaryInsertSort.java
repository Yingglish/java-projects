package sort;

import java.util.Arrays;

public class BinaryInsertSort {
    public static void binaryInsertSort(DataWrap[] data) {
        System.out.println("开始排序：\n");
        int arrLen = data.length;
        for (int i = 1; i < arrLen; i++) {
            // 当整体后移后保证data[i]的值不会丢失
            DataWrap tmp = data[i];
            int low = 0;
            int high = i - 1;
            while (low <= high) {
                int mid = (low + high) / 2;
                if (tmp.compareTo(data[mid]) > 0) {
                    // 限制在索引大于mid的那一半中搜索
                    low = mid + 1;
                } else {
                    // 限制在索引小于mid的那一半中搜索
                    low = mid - 1;
                }
            }
            // 将low 到i处的所有元素整体后移y一位
            for (int j = i; j > low; j--) {
                data[j] = data[j - 1];
            }

            // 最后将tmp的值插入合适位置
            data[low] = tmp;
            System.out.println(Arrays.toString(data));
        }
    }
}
