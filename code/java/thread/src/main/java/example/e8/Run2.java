package example.e8;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 方法schedule(TimerTask task,Date time)的测试
 * 该方法的作用是：在指定的时间执行一次指定任务
 *
 * TimerTask 是以队列的方式一个一个被顺序执行，所以执行的时间有可能和预期的时间不一致，因为前面的任务有可能消耗的时间较长，
 * 则后面的任务运行的时间也会被延迟
 */
public class Run2 {
    private static Timer timer = new Timer();

    public static class MyTask extends TimerTask {
        private String name;

        public MyTask(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + "任务已运行！当前时间为：" + new Date());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "完毕！当前时间为：" + new Date());
        }
    }

    public static void main(String[] args) throws ParseException {
        MyTask task1 = new MyTask("task1");
        MyTask task2 = new MyTask("task2");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString1 = "2019-02-11 16:53:05";
        Date dateRef1 = sdf.parse(dateString1);
        String dateString2 = "2019-02-11 16:53:06";
        Date dateRef2 = sdf.parse(dateString2);
        System.out.println("task1计划运行时间：" + dateRef1.toLocaleString() + " 当前时间：" + new Date().toLocaleString());
        System.out.println("task2计划运行时间：" + dateRef2.toLocaleString() + " 当前时间：" + new Date().toLocaleString());
        timer.schedule(task1, dateRef1);
        timer.schedule(task2, dateRef2);
    }
}
