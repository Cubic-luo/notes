package example.e12;

/**
 * 线程中出现异常的处理
 * 1.在本线程内可以捕获本线程的异常
 * 2.本线程外无法捕获本线程异常
 * <p>
 * 需要使用Thread.UncaughtExceptionHandler处理
 * 可以给单个线程加，或者通过
 */
public class Error2 {
    public static class MyRunnable implements Runnable {

        @Override
        public void run() {

            try {
                throw new RuntimeException("测试线程异常");
            } catch (Exception e) {
                System.out.println("线程内捕获线程异常");
                throw e;
            }
        }
    }

    public static void main(String[] args) {
        try {
            Thread thread = new Thread(new MyRunnable());
            thread.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
                @Override
                public void uncaughtException(Thread t, Throwable e) {
                    System.out.println("通过UncaughtExceptionHandler捕获异常");
                    try {
                        throw e;
                    } catch (Throwable throwable) {
                        throwable.printStackTrace();
                    }
                }
            });
            thread.start();
        } catch (Exception e) {
            System.out.println("线程外捕获异常");
        }

    }
}
