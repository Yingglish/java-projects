package queue;

public class LinkQueueTest {
    public static void main(String[] args) {
        LinkQueue<String> queue = new LinkQueue<>("aaaa");
        queue.add("bbbb");
        queue.add("cccc");
        System.out.println(queue);
        queue.remove();
        System.out.println(queue);
    }
}
