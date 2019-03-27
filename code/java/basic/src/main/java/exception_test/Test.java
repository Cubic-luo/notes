package exception_test;

/**
 * Function Description: <br>
 * Writter: p. <br>
 * Creating Time : 2019/3/27  16:51 <br>
 */
public class Test {
    public static void main(String[] args) throws MyCheckException {
        uncheckException();
        checkException();
    }

    /*
        不受检查的异常，不强制捕获或者抛出
     */
    public static void uncheckException() {
        throw new MyUncheckException("uncheck exception");
    }

    /*
        受检查的异常，必须捕获或者抛出，调用者也是如此
     */
    public static void checkException() throws MyCheckException {
        throw new MyCheckException("check exception");
    }
}
