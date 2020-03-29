package stack;

import java.util.LinkedList;
import java.util.Queue;

class QueueEmptyException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public QueueEmptyException() {
        super();
    }

    public QueueEmptyException(String message) {
        super(message);
    }
}

/**
 * 使用两个队列来实现堆栈行为。编写进栈和出栈方法来演示 Stack 行为（后进先出）
 */
public class StackUsingTwoQueues {
    Queue<Integer> queue1;
    Queue<Integer> queue2;
    StackUsingTwoQueues()
    {
        queue1=new LinkedList<Integer>();
        queue2=new LinkedList<Integer>();
    }

    /**
     * 如果queue1为空，则将元素添加到queue1
     * 如果queue1不为空，则将queue1的所有元素添加到queue2，将当前元素添加到queue1，然后将queue2的所有元素复制到queue1
     */
    public void push(int i){
        if(queue1.size()==0)
            queue1.add(i);
        else{
            int sizeOfQueue1 = queue1.size();
            // Copy elements of Queue1 to Queue2
            for(int j=0 ; j<sizeOfQueue1 ; j++)
                queue2.add(queue1.remove());
            queue1.add(i);
            // Copy elements for Queue2 to Queue1
            for(int k=0 ; k<sizeOfQueue1 ; k++)
                queue1.add(queue2.remove());
        }
    }

    /**
     * 只需从queue1中删除元素
     */
    public int pop(){
        if(queue1.size()==0)
            throw new QueueEmptyException("Underflow Exception");
        return queue1.remove();
    }

    public static void main(String[] args) {
        StackUsingTwoQueues stack = new StackUsingTwoQueues();
        stack.push(20);
        stack.push(40);
        stack.push(70);
        stack.push(50);
        stack.push(90);
        stack.push(110);
        stack.push(30);
        System.out.println("Removed element : "+ stack.pop());
        stack.push(170);
        System.out.println("Removed element : "+ stack.pop());
    }
}
