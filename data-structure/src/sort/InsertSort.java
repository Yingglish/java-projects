package sort;

public class InsertSort {
	public static void insertSort(DataWrap[] data) {
		System.out.println("开始排序\n");
		int arrLen = data.length;
		for (int i = 1; i < arrLen - 1; i++) {
			// 当整体后移时，保证data[i]的值不会丢失
			DataWrap tmp = data[i];

			// i 处索引处的值比前面的所有值都大，表明有序，无须插入
			// i - 1 索引之前的数据已经有序， i - 1索引处的元素值就是最大值
			if (data[i].compareTo(data[i - 1]) < 0) {
				int j = i -1;
				// 整体后移一格
				for ( ; j >= 0 && data[j].compareTo(tmp) > 0; j--) {
					data[j + 1] = data[j];
				}
				// 最后将tmp的值插入适合位置
				data[j + 1] = tmp;
			}
			System.out.println(java.util.Arrays.toString(data));
		}
	}
	public static void main(String[] args) {
		DataWrap[] data = {
				new DataWrap(21, ""),
				new DataWrap(23, ""),
				new DataWrap(-30, ""),
				new DataWrap(-49, ""),
				new DataWrap(21,"*"),
				new DataWrap(30, ""),
				new DataWrap(30, "*"),
		};
		System.out.println( "排序前\n"+ java.util.Arrays.toString(data));
		insertSort(data);
		System.out.println( "排序后\n"+ java.util.Arrays.toString(data));
	}
}
