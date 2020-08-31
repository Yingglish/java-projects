package com.yingglish.thread.juc.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockConditionDemo02 {
    private int number = 0;

    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void increment(){
        lock.lock();
        try {
            // 使用while 判断避免虚假唤醒
            // 假设俩个 +线程 当线程1 执行完毕后,可能唤醒 +线程2,但此时线程2 number = 1,只能进入await状态(此时if已经执行完),如果线程1抢到锁,但也不能进行操作
            // 和线程2一样进入等待状态，此时外面 -线程执行完毕后唤醒所有+线程,当+线程1执行完毕后可能执行+线程2 number
            while (number != 0){
                condition.await();
            }

            number++;
            System.out.println(Thread.currentThread().getName() + "\t" + number);
            condition.signalAll(); //
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void decrement(){
        lock.lock();
        try {
            while (number != 1){
                condition.await();
            }
            number--;
            System.out.println(Thread.currentThread().getName() + "\t" + number);
            condition.signalAll();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        LockConditionDemo02 demo = new LockConditionDemo02();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                demo.increment();
            }
        },"A").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                demo.decrement();
            }
        },"B").start();
    }
}
