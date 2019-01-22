package example.e3;

public class Test_wait_notify implements Runnable {
    private Dog dog;

    public Test_wait_notify(Dog dog) {
        this.dog = dog;
    }

    @Override
    public void run() {
        synchronized (dog) {
            for (int i = 0; i < 10; i++) {
                dog.notify();
                try {
                    dog.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " " + dog.getName());
            }

        }
    }
    //java中，wait和notify这两个方法是一对，wait方法阻塞当前线程，而notify是唤醒被wait方法阻塞的线程。
    //测试wait()和notify()  效果是2个线程交替运行，注意： notify()要在wait()之前
    public static void main(String[] args) {
        Dog dog = new Dog("haha");
        Test_wait_notify test_waitNotify = new Test_wait_notify(dog);
        new Thread(test_waitNotify, "上").start();
        new Thread(test_waitNotify, "左").start();
    }

}
