package com.yingglish.thread;

/**
 * 实现Runnable接口比继承Thread类所具有的优势 :
 *      1.适合多个相同的程序代码的线程去处理同一个资源
 *      2. 可以避免java中的单继承的限制
 *      3. 增加程序的健壮性，代码可以被多个线程共享，代码和数据独立
 *      4. 线程池只能放入实现Runable或callable类线程，不能直接放入继承Thread的
 */
public class MyRunnableImpl implements Runnable {
    private String name;

    public MyRunnableImpl(String name) {
        this.name = name;
    }

    @Override
    public void run() { // 线程主方法
        // Thread.currentThread() -- 获取当前线程对象
        System.out.println("当前线程对象\t" + Thread.currentThread());
        System.out.println( "线程名称\t" + Thread.currentThread().getName());
        // 线程操作方法
        for (int i = 0; i < 5; i++) {
            System.out.println(this.name + " --> " + i);
        }
    }

    /**
     * 当用户使用java命令执行一个类就表示启动一个 jvm 进程
     * 一个jvm 进程至少启动 main线程 和 gc线程(负责垃圾回收)
     */
    public static void main(String[] args) {
        // 要启动多线程要通过 thread 的start() 方法，但 Runnable 接口并未提供可继承的 start()方法
        // 只可通过 Thread类提供的 有参构造方法  public Thread(Runnable target)方法构造 Thread实例来启动多线程
        MyRunnableImpl myThread1 = new MyRunnableImpl("线程1");

        // public Thread(Runnable target, String name) 实例化线程对象，接受Runnable 接口子类对象，同时设置线程名称
        new Thread(myThread1,"线程A").start();
        new Thread(new MyRunnableImpl("线程2")).start(); // 使用匿名内部类

        /*
            Runnable 接口声明处使用了 @FunctionalInterface 注解
            说明 Runnable 是一个函数式接口
            使用Lambda表达式实现多线程
         */

/*        String name = "线程对象";
        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                System.out.println(name + " --> " + i);
            }
        }).start();*/
    }
}
