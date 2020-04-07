package queue;

public class SequenceQueueTest {
    public static void main(String[] args) {
        SequenceQueue<String> queue = new SequenceQueue<>();
        queue.add("aaa");
        queue.add("bbb");
        queue.add("ccc");
        System.out.println(queue);
        System.out.println("访问队列的front元素：" + queue.element());
        queue.remove();
        System.out.println("访问队列的front元素：" + queue.element());
    }
}
