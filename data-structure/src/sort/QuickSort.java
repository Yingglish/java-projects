package sort;

public class QuickSort {
	private static void swap(DataWrap[] data, int i,int j) {
		DataWrap tmp = data[i];
		data[i] = data[j];
		data[j] = tmp;
	}

	// 对data数组中从start到end索引范围内的子序列进行处理
	// 使之满足所有小于分界值的放在左边，大于的放在右边
	private static void subSort(DataWrap[] data, int start, int end) {
		// 需要排序
		if (start < end) {
			// 以以一个元素作为分界值
			DataWrap base = data[start];
			// i 从左边开始搜索，搜索大于分界值的元素的索引
			int i = start;
			int j = end + 1;

			while (true) {
				// 找到大于分界值元素的索引，或i已经到达end处
				while (i < end && data[++i].compareTo(base) <= 0);
				while (j > start && data[--j].compareTo(base) >=0);
				if(i < j) {
					swap(data, i ,j);
				} else {
					break;
				}
			}
			swap(data, start, j);
			//递归左边子序列
			subSort(data, start, j - 1);
			subSort(data, j + 1, end);
		}
	}
	public static void quickSort(DataWrap[] data) {
		subSort(data, 0, data.length - 1);
	}

	public static void main(String[] args) {
		DataWrap[] data = {
			new DataWrap(9, ""),
			new DataWrap(-16, ""),
			new DataWrap(21, ""),
			new DataWrap(23, ""),
			new DataWrap(-30, ""),
			new DataWrap(-49, ""),
			new DataWrap(21, "*"),
			new DataWrap(30, ""),
			new DataWrap(13, "")
		};

        System.out.println( "排序前：\n"+ java.util.Arrays.toString(data));
        quickSort(data);
        System.out.println( "排序后：\n"+ java.util.Arrays.toString(data));
	}
}
