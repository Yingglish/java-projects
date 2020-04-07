package stack;

public class LinkStackTest {
    public static void main(String[] args) {
        LinkStack<String> stack = new LinkStack<>();
        stack.push("aaaa");
        stack.push("bbbb");
        stack.push("cccc");
        System.out.println(stack);
        System.out.println("栈顶：" + stack.peek());
        System.out.println("弹出：" + stack.pop() + "\t 弹出后" + stack );
    }
}
