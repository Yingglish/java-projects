package com.yingglish.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * jdk 1.5提供 实现多线程的新的接口  java.util.concurrent.Callable
 */
public class MyCallableImpl implements Callable<String> { // 多线程主题类
    private int count = 5;
    private String name;

    public MyCallableImpl(String name) {
        this.name = name;
    }
    @Override
    public String call() throws Exception {
        for (int i = 0; i < 6; i++) {
            if (!(this.count < 0)) {
                System.out.println(this.name +"剩余票 ：" + this.count--);
            }
        }
        return "票已卖光";
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        MyCallableImpl mc1 = new MyCallableImpl("线程1");
        MyCallableImpl mc2 = new MyCallableImpl("线程2");

        // 要接收call() 方法的返回值 使用FutureTask<V>类 FutureTask类是Runnable接口的子类(因此仍然可使用 Thread类来启动多线程)，可接受Callable接口实例
        FutureTask<String> task1 = new FutureTask<>(mc1);
        FutureTask<String> task2 = new FutureTask<>(mc2);

        new Thread(task1).start();
        new Thread(task2).start();

        System.out.println("线程1 返回值:  " + task1.get());
        System.out.println("线程2 返回值:  " + task2.get());
    }
}
