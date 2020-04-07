package sort;

import java.util.Arrays;

public class ShellSort {
    public static void shellSort(DataWrap[] data) {
        System.out.println("开始排序");
        int arrLen = data.length;

        // h用于保存可变增量
        int h = 1;
        // 按 h*3 + 1得到增量序列的最大值
        while (h <= arrLen / 3) {
            h = h * 3 + 1;
        }
        while (h > 0) {
            System.out.println("===h的值：" + h + "===");
            for (int i = h; i < arrLen; i++) {
                // 整体后移时，保证data[i] 的值不会丢失
                DataWrap tmp = data[i];
                // i 处索引处的值比前面的所有值都大，表明有序，无须插入
                // i - 1 索引之前的数据已经有序， i - 1索引处的元素值就是最大值
                if (data[i].compareTo(data[i - h]) < 0) {
                    int j = i -h;
                    // 整体后移h格
                    for (; j >= 0 && data[j].compareTo(tmp) > 0; j -= h) {
                        data[j + h] = data[j];
                    }
                    // 将tmp插入合适位置
                    data[j + h] = tmp;
                }
                System.out.println(Arrays.toString(data));
            }
            h = (h - 1) / 3; // 反向计算h序列
        }
    }

    public static void main(String[] args) {
        DataWrap[] data = {
                new DataWrap(9, ""),
                new DataWrap(-16, ""),
                new DataWrap(21, ""),
                new DataWrap(23, ""),
                new DataWrap(-30, ""),
                new DataWrap(-49, ""),
                new DataWrap(21,"*"),
                new DataWrap(30, ""),
                new DataWrap(30, "*"),
        };
        System.out.println( "排序前\n"+ java.util.Arrays.toString(data));
        shellSort(data);
        System.out.println( "排序后\n"+ java.util.Arrays.toString(data));
    }
}
