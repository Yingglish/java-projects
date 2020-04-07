package sort;

public class BubbleSort {
    public static void bubbleSort(DataWrap[] data) {
        System.out.println("开始排序");
        int arrLen = data.length;
        for (int i = 0; i < arrLen - 1; i++) {
            boolean flag = false;
            for (int j = 0; j < arrLen - 1 - i; j++) {
                // 如果 j 索引处元素大于 j+1索引处元素
                if (data[j].compareTo(data[j + 1]) > 0) {
                    DataWrap tmp = data[j + 1];
                    data[j + 1] = data[j];
                    data[j] = tmp;
                    flag = true;
                }
                System.out.println(java.util.Arrays.toString(data));
                if (!flag) { // 如果某趟未发生交换，则表明已处于有序状态
                    break;
                }
            }
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
        System.out.println( "排序前：\n"+ java.util.Arrays.toString(data));
        bubbleSort(data);
        System.out.println( "排序后：\n"+ java.util.Arrays.toString(data));
    }
}
