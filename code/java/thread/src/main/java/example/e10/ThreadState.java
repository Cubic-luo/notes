package example.e10;

public class ThreadState {
    private static class MyThread extends Thread {
        public MyThread() {
            System.out.println("执行构造函数: " + Thread.currentThread().getState());
        }

        @Override
        public void run() {
            synchronized ("block") {
                System.out.println("执行run方法: " + Thread.currentThread().getState());
                try {
                    System.out.println("开始sleep");
                    Thread.sleep(2000);
                    System.out.println("结束sleep");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static class Thread2 extends Thread {
        @Override
        public void run() {
            synchronized ("block") {
                System.out.println("线程2已执行");
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        MyThread t = new MyThread();
        Thread2 thread2 = new Thread2();
        System.out.println("已初始化,未start：" + t.getState());
        Thread.sleep(1000);
        t.start();
        thread2.start();
        Thread.sleep(500);
        System.out.println("等待线程锁：" + thread2.getState());
        thread2.join();
        System.out.println("sleep: " + t.getState());
        t.join();
        System.out.println("执行完毕：" + t.getState());
    }
}
