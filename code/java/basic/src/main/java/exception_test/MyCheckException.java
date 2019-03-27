package exception_test;

/**
 * Function Description:自定义 check exception <br>
 * Writter: p. <br>
 * Creating Time : 2019/3/27  17:05 <br>
 */
public class MyCheckException extends Exception {
    public MyCheckException(String message) {
        super(message);
    }
}
