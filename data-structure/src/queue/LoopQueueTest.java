package queue;

public class LoopQueueTest {
    public static void main(String[] args) {
        LoopQueue<String> queue = new LoopQueue<>("aaaa", 3);
        queue.add("bbbb");
        queue.add("cccc");

        // 此时队列已满
        System.out.println(queue);
        queue.add("dddd");
    }
}
