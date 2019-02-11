package example.e3;

public class Join implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + i);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println(Thread.currentThread() + "主线程开始");
        Thread subThread = new Thread(new Join(), "线程A");
        subThread.start();
        subThread.join();
        System.out.println(Thread.currentThread() + "主线程结束");
    }
}
