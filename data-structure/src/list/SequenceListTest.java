package list;

public class SequenceListTest {
    public static void main(String[] args) {
        SequenceList<String> list = new SequenceList<>();
        list.add("aaaa");
        list.add("bbbb");
        list.add("cccc");

        // 在索引为1处插入一个新元素
        list.insert("dddd",1);

        System.out.println(list);

        // 删除索引为2处的元素
        list.delete(2);
        System.out.println(list);

        System.out.println("cccc在顺序表中的位置" + list.locate("cccc"));
    }
}
