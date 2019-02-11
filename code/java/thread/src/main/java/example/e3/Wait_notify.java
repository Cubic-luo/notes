package example.e3;

public class Wait_notify implements Runnable {
    private Dog dog;

    public Wait_notify(Dog dog) {
        this.dog = dog;
    }

    @Override
    public void run() {
        synchronized (dog) {
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + "notify 之前");
                dog.notify();
                System.out.println(Thread.currentThread().getName() + "notify 之后");
                try {
                    System.out.println(Thread.currentThread().getName() + "wait 之前");
                    dog.wait();
                    System.out.println(Thread.currentThread().getName() + "wait 之后");
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
        Wait_notify test_waitNotify = new Wait_notify(dog);
        new Thread(test_waitNotify, "1 ").start();
        new Thread(test_waitNotify, "2 ").start();
    }

}
