import java.util.Date;

/**
 * Function Description:必要时进行保护性拷贝 <br>
 * Writter: p. <br>
 * Creating Time : 2019/3/19  20:05 <br>
 */
public final class Article39 {
    private final Date time;

    public Article39(Date time) {
        Date timeCopy = new Date(time.getTime());//保护性拷贝
        this.time = timeCopy;
    }

    public Date getTime() {
        return new Date(time.getTime());
    }

    public static void main(String[] args) {
        System.out.println("======通过构造函数渗透=====");
        Date time = new Date();
        Article39 article39 = new Article39(time);
        time.setYear(66);
        System.out.println(article39.getTime());
        System.out.println("======通过get方法渗透=====");
        article39.getTime().setYear(66);
        System.out.println(article39.getTime());

    }
}
