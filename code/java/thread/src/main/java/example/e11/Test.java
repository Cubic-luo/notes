package example.e11;
//线程组不需要深究，稍微了解即可
public class Test {
    public static void main(String[] args) {
        ThreadGroup threadGroup = new ThreadGroup("xp thread group");
        MyRunnable runnable = new MyRunnable();
        Thread thread1 = new Thread(threadGroup, runnable);
        Thread thread2 = new Thread(threadGroup, runnable);
        thread1.start();
        thread2.start();
        System.out.println("Number of  active thread " + threadGroup.activeCount());
        System.out.println("Thread group name " + threadGroup.getName());


    }
}
