package example.e8;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 在执行任务时，如果指定的计划执行时间，则task会首先按执行一次；然后按照执行时间、系统当前时间和period参数计算出过期该执行的次数，计算按照： (systemCurrentTime-scheduledExecutionTime)/period，再次执行计算出的次数；最后按period参数固定重复执行。
 * <p>
 * case1 scheduledExecutionTime<= systemCurrentTime(晚于计划时间执行)：
 * schedule方法：立即周期性按照指定间隔时间执行
 * scheduleAtFixedRate方法：按照(systemCurrentTime-scheduledExecutionTime)/period 计算出来的次数，立即不间断的执行，
 * 然后才按照指定间隔周期性的执行，也就是先把前面少执行的次数补上
 * <p>
 * case2 scheduledExecutionTime>= systemCurrentTime：
 * schedule方法和scheduleAtFixedRate方法：都是按照指定间隔时间周期性执行
 */
public class Run7 {
    private static Timer timer = new Timer();

    public static class MyTaskA extends TimerTask {
        @Override
        public void run() {
            System.out.println("schedule begin! the current time is：" + new Date());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("schedule end！the current time is：" + new Date());
        }
    }

    public static class MyTaskB extends TimerTask {
        @Override
        public void run() {
            System.out.println("scheduleAtFixedRate begin! the current time is：" + new Date());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("scheduleAtFixedRate end！the current time is：" + new Date());
        }
    }

    public static void main(String[] args) throws ParseException, InterruptedException {
        MyTaskA taskA = new MyTaskA();
        MyTaskB taskB = new MyTaskB();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = "2019-02-12 14:39:05";
        Date dateRef = sdf.parse(dateString);

        System.out.println("test start,the current time is:" + new Date());
        //晚于计划时间执行
        //timer.schedule(taskA, dateRef, 5000);
        //timer.scheduleAtFixedRate(taskB, dateRef, 5000);

        //按照计划时间执行
        //timer.schedule(taskA, new Date(), 5000);
        timer.scheduleAtFixedRate(taskB, new Date(), 5000);
    }
}
