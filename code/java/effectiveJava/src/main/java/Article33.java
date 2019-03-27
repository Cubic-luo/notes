import java.util.EnumMap;

/**
 * Function Description: 第33条 用EnumMap代替序数索引<br>
 * Writter: P. <br>
 * Creating Time : 2019/3/15  16:12 <br>
 */
public class Article33 {
    private enum APP {
        APP1
    }

    public static void main(String[] args) {
        EnumMap<APP, String> enumMap = new EnumMap(APP.class);
    }
}
