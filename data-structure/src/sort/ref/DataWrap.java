package sort.ref;

// 定义一个数据包装类
public class DataWrap implements Comparable<DataWrap> {
    int data;
    String flag;

    public DataWrap(int data, String flag) {
        this.data = data;
        this.flag = flag;
    }

    @Override
    public String toString() {
        return data + flag;
    }

    // 根据data实例变量开决定俩个DataWrap的大小
    @Override
    public int compareTo(DataWrap o) {
        return Integer.compare(this.data, o.data);
    }
}

