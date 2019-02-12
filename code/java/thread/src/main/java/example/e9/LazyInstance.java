package example.e9;

/**
 * 使用双检查锁机制保证单例模式，同时兼顾线程效率
 * 1.问：用了synchronized，为何还要加volatile？
 * 答：synchronized没有禁止重排序的功能，volatile可以禁止重排序，步骤4的操作是非原子性的，实际上有3步，
 * (memory = allocate();　　// 1：分配对象的内存空间
 ctorInstance(memory);　// 2：初始化对象
 instance = memory;　　// 3：设置instance指向刚分配的内存地址)

 * 在实例化过程中，重排序可能导致某些线程访问到一个还未初始化的对象
 *
 * 参考文献：
 * https://www.cnblogs.com/vete-l/p/7107700.html
 * https://www.cnblogs.com/keeya/p/9260565.html
 * https://blog.csdn.net/ljheee/article/details/53152737
 */
public class LazyInstance {
    private static volatile LazyInstance lazyInstance;

    private LazyInstance() {

    }

    public static LazyInstance getLazyInstance() {
        //第一重检查  1
        if (lazyInstance == null) {
            try {
                synchronized (LazyInstance.class) { //加锁 2
                    //模拟处理其他事情消耗的时间
                    Thread.sleep(3000);
                    if (lazyInstance == null) {//第二重检查 3
                        lazyInstance = new LazyInstance(); //4.加volatile的原因 在这里
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return lazyInstance;
    }

    public static class MyThread extends Thread {
        @Override
        public void run() {
            System.out.println(LazyInstance.getLazyInstance().hashCode());
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
