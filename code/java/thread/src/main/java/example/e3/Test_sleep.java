package example.e3;

public class Test_sleep implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + "start");
            try {
                Thread.sleep(2 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "end");
        }
    }

    public static void main(String[] args) {
        new Thread(new Test_sleep(), "TEST").start();
    }
}
