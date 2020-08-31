package com.yingglish.thread.synchronizedemo;

import java.util.concurrent.TimeUnit;

/**
 * Synchronized经过编译后，会在同步块前后分别形成monitorenter和monitorexit两个字节码指令，
 * 在执行monitorenter指令时，首先要尝试获取对象锁，如果对象没有别锁定，或者当前已经拥有这个对象锁，把锁的计数器加1，
 * 相应的在执行monitorexit指令时，会将计数器减1，当计数器为0时，锁就被释放了。
 * 如果获取锁失败，那当前线程就要阻塞，直到对象锁被另一个线程释放为止。
 *
 */
public class TestABC implements Runnable{
    private String name;
    private final Object pre;
    private final Object self;

    public TestABC(String name, Object pre, Object self) {
        this.name = name;
        this.pre = pre;
        this.self = self;
    }

    @Override
    public void run() {
        int count = 10;
        while (count > 0) {
            synchronized (pre) {
                synchronized (self) {
                    System.out.println(name);
                    count --;

                    // 调用 notify() 方法会将等待队列中的线程移动到同步队列中，线程状态也会更新为 BLOCKED
                    // B 线程调用了 notify() 方法，这样 A 线程收到通知之后就可以从 wait() 方法中返回
                    self.notify(); // 唤醒等待队列中的线程
                }

                try {
                    // 调用 wait() 方法后线程会释放锁，进入 WAITING 状态，`该线程也会被移动到等待队列中`
                    // 从 wait() 方法返回的前提是调用 notify() 方法的线程释放锁，wait() 方法的线程获得锁
                    pre.wait();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Object a = new Object();
        Object b = new Object();
        Object c = new Object();

        new Thread(new TestABC("A",c,a)).start();
        TimeUnit.MILLISECONDS.sleep(100);
        new Thread(new TestABC("B",a,b)).start();
        TimeUnit.MILLISECONDS.sleep(100);
        new Thread(new TestABC("C",b,c)).start();
    }
}
