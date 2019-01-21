package example.e2;

public class Test {
    public static void main(String[] args) {
        //每个线程都独立，不共享资源，每个线程都卖出了10张票，总共卖出了30张。如果真卖票，就有问题了。
        MyThread t1 = new MyThread();
        MyThread t2 = new MyThread();
        MyThread t3 = new MyThread();
        t1.start();
        t2.start();
        t3.start();

        // 每个线程共享了对象myRunnable的资源
        MyRunnable myRunnable = new MyRunnable();
        new Thread(myRunnable, "窗口1").start();
        new Thread(myRunnable, "窗口2").start();
        new Thread(myRunnable, "窗口3").start();
    }
}
