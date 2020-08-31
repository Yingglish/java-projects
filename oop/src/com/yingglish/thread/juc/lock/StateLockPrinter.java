package com.yingglish.thread.juc.lock;

/**
 * ReetrabtLock 实现原理:
 *      ReenTrantLock的实现是一种自旋锁，通过循环调用CAS操作来实现加锁。它的性能比较好也是因为避免了使线程进入内核态的阻塞状态。
 *      想尽办法避免线程进入内核的阻塞状态是我们去分析和理解锁设计的关键钥匙
 *
 * 相比Synchronized，ReentrantLock类提供了一些高级功能
 *      1. 等待可中断，持有锁的线程长期不释放的时候，正在等待的线程可以选择放弃等待，
 *          这相当于Synchronized来说可以避免出现死锁的情况。通过lock.lockInterruptibly()来实现这个机制
 *      2. 公平锁，多个线程等待同一个锁时，必须按照申请锁的时间顺序获得锁，Synchronized的锁是非公平锁，
 *      ReentrantLock默认的构造函数是创建的非公平锁，可以通过参数true设为公平锁，但公平锁表现的性能不是很好
 *      3. 锁绑定多个条件，一个ReentrantLock对象可以同时绑定对个对象。ReenTrantLock提供了一个Condition（条件）类，
 *      用来实现分组唤醒需要唤醒的线程们，而不是像synchronized要么随机唤醒一个线程要么唤醒全部线程
 */

public class StateLockPrinter {
    //状态变量
    private volatile int state=0;

    // 打印线程
    private class Printer implements Runnable {
        //打印次数
        private static final int PRINT_COUNT=10;
        //打印锁
        private final Object printLock;
        //打印标志位 和state变量相关
        private final int printFlag;
        //后继线程的线程的打印标志位，state变量相关
        private final int nextPrintFlag;
        //该线程的打印字符
        private final char printChar;

        // 每个打印线程一个自身的condition和下一个线程的condition，每次打印字符后
        // 调用下一个线程的condition.signal来唤醒下一个线程，然后自身再通过自己的condition.await来释放锁并等待唤醒
        public Printer(Object printLock, int printFlag,int nextPrintFlag, char printChar) {
            super();
            this.printLock = printLock;
            this.printFlag=printFlag;
            this.nextPrintFlag=nextPrintFlag;
            this.printChar = printChar;
        }

        @Override
        public void run() {
            //获取打印锁 进入临界区
            synchronized (printLock) {
                //连续打印PRINT_COUNT次
                for(int i=0;i<PRINT_COUNT;i++){
                    //循环检验标志位 每次都阻塞然后等待唤醒
                    while (state!=printFlag) {
                        try {
                            printLock.wait();
                        } catch (InterruptedException e) {
                            return;
                        }
                    }
                    //打印字符
                    System.out.print(printChar);
                    //设置状态变量为下一个线程的标志位
                    state=nextPrintFlag;
                    //注意要notifyall，不然会死锁，因为notify只通知一个，
                    //但是同时等待的是两个,如果唤醒的不是正确那个就会没人唤醒，死锁了
                    printLock.notifyAll();
                }
            }
        }

    }

    public void test() throws InterruptedException{
        // 锁
        Object lock=new Object();
        //打印A的线程
        Thread threadA=new Thread(new Printer(lock, 0,1, 'A'));
        //打印B的线程
        Thread threadB=new Thread(new Printer(lock, 1,2, 'B'));
        //打印C的线程
        Thread threadC=new Thread(new Printer(lock, 2,0, 'C'));
        //一次启动A B C线程
        threadA.start();
        Thread.sleep(1000);
        threadB.start();
        Thread.sleep(1000);
        threadC.start();
    }

    public static void main(String[] args) throws InterruptedException {

        StateLockPrinter print = new StateLockPrinter();
        print.test();
    }

}