package example.e3;

public class Test_setPriority implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            //循环次数设置为10的时候，不是很明显
            System.out.println(Thread.currentThread().getName());
        }
    }

    //setPriority 测试
    public static void main(String[] args) throws InterruptedException {
        Test_setPriority testsetPriority = new Test_setPriority();
        Thread thread1 = new Thread(testsetPriority, "线程1");
        Thread thread2 = new Thread(testsetPriority, "线程2");
        thread2.setPriority(Thread.MAX_PRIORITY);
        thread1.setPriority(Thread.NORM_PRIORITY);
        thread1.start();
        thread2.start();
    }
}
