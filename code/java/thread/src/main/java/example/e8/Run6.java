package example.e8;

import java.text.ParseException;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 方法schedule(TimerTask task,long delay,long period)的测试
 * <p>
 * 该方法的作用是以执行schedule(TimerTask task,long delay,long period)方法当前的时间为参考时间，
 * 在此时间基础上延迟指定的毫秒数后执行一次TimerTask任务
 */
public class Run6 {
    private static Timer timer = new Timer();

    public static class MyTask extends TimerTask {

        @Override
        public void run() {
            System.out.println("任务已运行！当前时间为：" + new Date());
        }
    }

    public static void main(String[] args) throws ParseException {
        MyTask task = new MyTask();
        System.out.println("当前时间为：" + new Date().toLocaleString());
        timer.schedule(task, 2000);
    }
}
