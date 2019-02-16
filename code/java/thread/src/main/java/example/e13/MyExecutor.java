package example.e13;

import java.util.concurrent.*;

public class MyExecutor implements Runnable {
    private int sleepTime;

    public MyExecutor() {
    }

    public MyExecutor(int sleepTime) {

        this.sleepTime = sleepTime;
    }

    public static void main(String[] args) throws InterruptedException {
        //test_newCachedThreadPool();
        //test_newSingleThreadExecutor();
        //test_newFixedThreadPool();
        //test_prestartCoreThread();
        test_keepAliveTime();


    }

    @Override
    public void run() {

        System.out.println("start 等待时间：" + sleepTime + Thread.currentThread().getName());
        try {
            //模拟处理事务消耗的时间
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("end " + Thread.currentThread().getName());
    }

    /**
     * 测试newCachedThreadPool()方法 无界队列
     * 1、无界线程池，可以进行自动线程回收
     * 2、创建一个可根据需要创建新线程的线程池，但是在以前构造的线程可用时将重用它们。
     * <p>
     * 提示：把sleep()注释，则会有3个线程分别执行任务；如果打开了sleep，则一直会是一个线程执行
     */

    public static void test_newCachedThreadPool() throws InterruptedException {
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(new MyExecutor());
        //Thread.sleep(1000);
        exec.execute(new MyExecutor());
        //Thread.sleep(1000);
        exec.execute(new MyExecutor());
        exec.shutdown();
    }

    /**
     * 测试newSingleThreadExecutor() 无界队列
     * 1.单个后台线程
     * 2.创建一个使用单个 worker 线程的 Executor，以无界队列方式来运行该线程。（注意，如果因为在关闭前的执行期间出现失败而终止了此单个线程，那么如果需要，一个新线程将代替它执行后续的任务）。
     * 可保证顺序地执行各个任务，并且在任意给定的时间不会有多个线程是活动的。
     *
     * @throws InterruptedException
     */
    public static void test_newSingleThreadExecutor() throws InterruptedException {
        ExecutorService exec = Executors.newSingleThreadExecutor();
        exec.execute(new MyExecutor());
        exec.execute(new MyExecutor());
        exec.execute(new MyExecutor());
        exec.shutdown();
    }

    /**
     * 测试newFixedThreadPool() 无界队列
     * 1.固定大小线程池
     * 2.创建一个可重用固定线程集合的线程池，以共享的无界队列方式来运行这些线程（只有要请求的过来，就会在一个队列里等待执行）。
     * 如果在关闭前的执行期间由于失败而导致任何线程终止，那么一个新线程将代替它执行后续的任务（如果需要）。
     *
     * @throws InterruptedException
     */
    public static void test_newFixedThreadPool() throws InterruptedException {
        ExecutorService exec = Executors.newFixedThreadPool(2);
        exec.execute(new MyExecutor());
        exec.execute(new MyExecutor());
        exec.execute(new MyExecutor());
        exec.shutdown();
    }

    /**
     * 定制 ThreadPoolExecutor
     * 测试项： prestartCoreThread()方法、prestartAllCoreThreads() 方法
     * prestartCoreThread():创一个空闲任务线程等待任务的到达
     * prestartAllCoreThreads()：创建核心线程池数量的空闲任务线程等待任务的到达
     * <p>
     * 注意：测试结果，当2个方法都执行时，以prestartCoreThread生效，即只有一个线程在等待
     */
    public static void test_prestartCoreThread() throws InterruptedException {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 2, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingDeque<>());
        System.out.println("未执行prestartCoreThread()，当前活动线程数：" + threadPoolExecutor.getActiveCount());
        //threadPoolExecutor.prestartCoreThread();
        System.out.println("已执行prestartCoreThread()，当前活动线程数：" + threadPoolExecutor.getActiveCount());
        threadPoolExecutor.prestartAllCoreThreads();
        System.out.println("已执行prestartAllCoreThreads()，当前活动线程数：" + threadPoolExecutor.getActiveCount());
        threadPoolExecutor.shutdown();

    }

    /**
     * 定制 ThreadPoolExecutor
     * 测试项：keepAliveTime 参数
     * 如果线程池当前拥有超过corePoolSize的线程，那么多余的线程在空闲时间超过keepAliveTime时会被终止
     */
    public static void test_keepAliveTime() throws InterruptedException {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 4, 1000L, TimeUnit.MILLISECONDS, new LinkedBlockingDeque<>(1));
        threadPoolExecutor.execute(new MyExecutor(10 * 1000));
        threadPoolExecutor.execute(new MyExecutor(10 * 1000));
        threadPoolExecutor.execute(new MyExecutor(2000));
        threadPoolExecutor.execute(new MyExecutor(2000));
        threadPoolExecutor.execute(new MyExecutor(2000));

        boolean flag = true;
        while (flag) {
            System.out.println("池中线程数：" + threadPoolExecutor.getPoolSize() + " 池中当前活动线程数：" + threadPoolExecutor.getActiveCount());
            Thread.sleep(500);

            System.out.println("开始通过threadGroup查看当前所有线程状态");
            ThreadGroup threadGroup = Thread.currentThread().getThreadGroup();
            int noThreads = threadGroup.activeCount();
            Thread[] lstThreads = new Thread[noThreads];
            threadGroup.enumerate(lstThreads);
            System.out.println("group 中线程数 " + noThreads);
            for (int i = 0; i < noThreads; i++) {
                System.out.println(lstThreads[i].getName() + " 当前转态" + lstThreads[i].getState());
            }
            System.out.println("结束通过threadGroup查看当前所有线程状态");

            if (threadPoolExecutor.getActiveCount() == 0) {
                threadPoolExecutor.shutdown();
                flag = false;
            }
        }


    }
}
