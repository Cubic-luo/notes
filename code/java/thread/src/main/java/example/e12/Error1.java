package example.e12;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * SimpleDateFormat 主要负责日期的转换与格式化，但是在多线程环境中，使用此类容易造成数据转换及处理的不准确，
 * 因为SimpleDateFormat类并非线程安全的
 * 解决方法1：创建多个impleDateFormat实例
 * 解决方法2: 用ThreadLocal放SimpleDateFormat
 */
public class Error1 {
    public static class MyThread extends Thread {
        private SimpleDateFormat sdf;
        private String dateString;

        public MyThread(SimpleDateFormat sdf, String dateString) {
            this.dateString = dateString;
            this.sdf = sdf;
        }

        @Override
        public void run() {
            try {
                Date dateRef = sdf.parse(dateString);
                String newDateString = sdf.format(dateRef).toString();
                if (!newDateString.equals(dateString)) {
                    System.out.println("Thread name=" + this.getName() + " 报错了，预期：" + dateString + " 结果为; " + newDateString);
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String[] dateStringArray = new String[]{
                "2019-02-01", "2019-02-02", "2019-02-03", "2019-02-04", "2019-02-05", "2019-02-06", "2019-02-07", "2019-02-08", "2019-02-09", "2019-02-10"
        };
        MyThread[] threads = new MyThread[10];
        for (int i = 0; i < 10; i++) {
            threads[i] = new MyThread(sdf, dateStringArray[i]);
        }
        for (int i = 0; i < 10; i++) {
            threads[i].start();
        }
    }
}
