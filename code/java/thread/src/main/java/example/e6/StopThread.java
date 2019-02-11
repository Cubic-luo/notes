package example.e6;

public class StopThread implements Runnable {
    public volatile boolean exit = false;

    @Override
    public void run() {
        while (!exit) {
            System.out.println("do something");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        StopThread stopThread = new StopThread();
        new Thread(stopThread).start();
        Thread.sleep(3000);
        stopThread.exit = true;
    }
}
