package com.yingglish.thread.juc.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *  Synchronized与ReentrantLock
 *      相同点
 *          1. 都是加锁方式同步，而且都是阻塞式的同步(就是说当如果一个线程获得了对象锁，进入了同步块，其他访问该
 *          同步块的线程都必须阻塞在同步块外面等待)
 *      区别
 *          1. 功能上:
 *             a. Synchronized是java语言的关键字，是原生语法层面的互斥，需要jvm实现。
 *              而ReentrantLock它是JDK 1.5之后提供的API层面的互斥锁，需要lock()和unlock()方法配合try/finally语句块来完成
 *             b. Synchronized的使用比较方便简洁，并且由编译器去保证锁的加锁和释放，而ReenTrantLock需要手工声明来加锁和释放锁，
 *             为了避免忘记手工释放锁造成死锁，所以最好在finally中声明释放锁
 *          2. 性能上
 *              a. 在Synchronized优化(试图在用户态就把加锁问题解决，避免进入内核态的线程阻塞)以前
 *              synchronized的性能是比ReenTrantLock差很多的，但是自从Synchronized引入了偏向锁，轻量级锁（自旋锁）后，两者的性能就差不多了
 *
 *
 */
public class LockConditionDemo {
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    private void await() {
        try {
            lock.lock();
            System.out.println("开始等待await！ ThreadName：" + Thread.currentThread().getName());
            condition.await();
            System.out.println("等待await结束！ ThreadName：" + Thread.currentThread().getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    private void signal() {
        lock.lock();
        System.out.println("发送通知signal！ ThreadName：" + Thread.currentThread().getName());
        condition.signal();
        lock.unlock();
    }

    public static void main(String[] args) throws InterruptedException {
        LockConditionDemo demo = new LockConditionDemo();
        new Thread(demo::await, "thread1").start();
        TimeUnit.MILLISECONDS.sleep(100);
        new Thread(demo::signal, "thread2").start();
    }
}
