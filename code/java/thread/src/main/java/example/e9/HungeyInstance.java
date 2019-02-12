package example.e9;


public class HungeyInstance {
    //立即加载方式==饿汉模式
    private static HungeyInstance hungeyInstance = new HungeyInstance();

    private HungeyInstance() {
    }

    public static HungeyInstance getHungeyInstance() {
        return hungeyInstance;
    }

    private static class MyThread extends Thread {
        @Override
        public void run() {
            System.out.println(HungeyInstance.getHungeyInstance().hashCode());
        }
    }

    public static void main(String[] args) {
        MyThread t1 = new MyThread();
        MyThread t2 = new MyThread();
        MyThread t3 = new MyThread();
        t1.start();
        t2.start();
        t3.start();
    }

}
