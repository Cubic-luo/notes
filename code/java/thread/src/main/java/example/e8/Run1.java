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
 * 执行任务的时间晚于当前时间：在未来执行的效果(注意：当计划时间早于当前时间时，会立即运行)
 */
public class Run1 {
    private static Timer timer = new Timer();

    public static class MyTask extends TimerTask {

        @Override
        public void run() {
            System.out.println("任务已运行！当前时间为：" + new Date());
        }
    }

    public static void main(String[] args) throws ParseException {
        MyTask task = new MyTask();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = "2019-02-11 16:55:05";
        Date dateRef = sdf.parse(dateString);
        System.out.println("计划运行时间：" + dateRef.toLocaleString() + " 当前时间：" + new Date().toLocaleString());
        timer.schedule(task, dateRef);
    }
}
