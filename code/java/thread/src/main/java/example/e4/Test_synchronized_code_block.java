package example.e4;

public class Test_synchronized_code_block implements Runnable {
    private String name;

    public String getName() {
        synchronized (this) {
            try {
                System.out.println(Thread.currentThread().getName() + " start");
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return name;
        }
    }

    public void setName() {
        this.name = Thread.currentThread().getName();
    }

    @Override
    public void run() {
        getName();
    }


    public static void main(String[] args) {
        Test_synchronized_code_block test_synchronized_code_block = new Test_synchronized_code_block();
        new Thread(test_synchronized_code_block, "线程1").start();
        new Thread(test_synchronized_code_block, "线程2").start();

    }
}



