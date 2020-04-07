package com.yingglish.thread.synchronizedemo;

public class TestSynchronized {
    private final Object object = new Object();

    //修饰静态方法    
    public synchronized static void methodA(){
        System.out.println("methodA.....");
        try{
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //代码块synchronized(object)
    public void methodB(){
        synchronized (this) {
            System.out.println("methodB.....");
            try{
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    //代码块synchronized(class)
    public void methodC(){
        synchronized (TestSynchronized.class) {
            System.out.println("methodC.....");
            try{
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    //修饰普通法法
    public synchronized void methodD(){
        System.out.println("methodD.....");
        try{
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void methodE(){
        synchronized (object) {
            System.out.println("methodE.....");
            try{
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        TestSynchronized obj = new TestSynchronized();
        // 对比 synchronized (this)和 synchronized method(){}
        // this指的是调用这个方法的对象，那调用synchronized method()的又是被实例化出来的对象 即使用的是同一个锁
        new Thread(obj::methodB).start();
        new Thread(obj::methodD).start();
//        new Thread(obj::methodE).start(); // 不是同一个锁

        /*
            代码在运行时，只会生成一个Class对象。我们知道static修饰方法时，那方法就属于类方法，
            所以这里的话synchronized(Object.Class)和 static synchronized method()都是使用的Object.class作为锁
         */
        new Thread(obj::methodC).start();
        new Thread(TestSynchronized::methodA).start();
        new Thread(obj::methodE).start(); // 不是同一个锁
    }

}
