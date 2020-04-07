package com.yingglish.thread.synchronizedemo;

import java.util.concurrent.TimeUnit;

public class TestABC implements Runnable{
    private String name;
    private Object pre;
    private Object self;

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

                    // 释放锁
                    self.notify();
                }
                try {
                    // 给之前的数据加锁
                    pre.wait();
                }catch (Exception e){

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
