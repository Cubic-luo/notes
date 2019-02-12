package example.e8;

import java.text.ParseException;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * TimerTask类的cancel()方法
 * TimerTask类的cancel()方法作用是将自身从任务队列中清除
 */
public class Run4 {
    private static Timer timer = new Timer();

    public static class MyTaskA extends TimerTask {
        @Override
        public void run() {
            System.out.println("任务A已运行！当前时间为：" + new Date());
            this.cancel();
        }
    }

    public static class MyTaskB extends TimerTask {
        @Override
        public void run() {
            System.out.println("任务B已运行！当前时间为：" + new Date());
        }
    }

    public static void main(String[] args) throws ParseException {
        MyTaskA a = new MyTaskA();
        MyTaskB b = new MyTaskB();
        timer.schedule(a, 1000, 2000);
        timer.schedule(b, 1000, 2000);
    }
}
