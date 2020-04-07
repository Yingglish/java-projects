package linklist;

public class LinkListTest {
    public static void main(String[] args) {
        LinkList<String> list = new LinkList<>();
        list.insert("aaaa", 0);
        list.add("bbbb");
        list.add("cccc");

        list.insert("dddd", 1);
        System.out.println(list);
        System.out.println(list.getHeader());
        System.out.println(list.swapPairs(list.getHeader()));
        System.out.println(list);

        String del = list.delete(2);

        System.out.println("删除元素后链表" + list);
        System.out.println("cccc在链表中的位置：" + list.locate("cccc"));
        System.out.println("索引为2的链表元素：" + list.get(2));
    }
}
