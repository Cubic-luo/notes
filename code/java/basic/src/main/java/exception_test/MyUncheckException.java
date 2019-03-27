package exception_test;

/**
 * Function Description: 自定义 unchecked exception<br>
 * Writter: p. <br>
 * Creating Time : 2019/3/27  16:59 <br>
 */
public class MyUncheckException extends RuntimeException {
    public MyUncheckException(String message) {
        super(message);
    }
}
