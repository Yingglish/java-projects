package linklist;

public class DuLinkListTest {
    public static void main(String[] args) {
        DuLinkList<String> list = new DuLinkList<>();
        list.insert("aaaa", 0);
        list.add("bbbb");
        list.insert("cccc",0);
        list.insert("dddd", 1);

        System.out.println(list);

        String del = list.delete(2);
        System.out.println(list);
    }
}
