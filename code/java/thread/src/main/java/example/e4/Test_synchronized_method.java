package example.e4;

/**
 * synchronized 同步方法
 */
public class Test_synchronized_method implements Runnable {
    private String name;

    public synchronized String getName() {
        return name;
    }

    public synchronized void setName(String name) {
        try {
            System.out.println(Thread.currentThread().getName() + "start");
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.name = name;
    }

    @Override
    public synchronized void run() {
        setName(Thread.currentThread().getName());
        System.out.println(getName());
    }

    public static void main(String[] args) {
        Test_synchronized_method test_synchronized1 = new Test_synchronized_method();
        new Thread(test_synchronized1, "线程1 ").start();
        new Thread(test_synchronized1, "线程2 ").start();
    }
}
