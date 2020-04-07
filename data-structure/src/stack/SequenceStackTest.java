package stack;

public class SequenceStackTest {
    public static void main(String[] args) {
        SequenceStack<String> stack = new SequenceStack<>();
        stack.push("aaa");
        stack.push("bbb");
        stack.push("ccc");
        System.out.println(stack);
        System.out.println("栈顶：" + stack.peek());
        System.out.println("弹出：" + stack.pop() + "\t 弹出后" + stack );
    }
}
