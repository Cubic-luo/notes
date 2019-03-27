package enum_test;

import java.util.HashMap;
import java.util.Map;

/**
 * Function Description:枚举模板 <br>
 * Writter: p. <br>
 * Creating Time : 2019/3/18  16:51 <br>
 */
public enum Template {
    A(1, "a"),
    B(2, "b");

    //注意  final修饰
    private final int code;
    private final String value;

    public int getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }

    private static final Map<Integer, Template> intMap = new HashMap<>();
    private static final Map<String, Template> stringMap = new HashMap<>();

    static {
        for (Template template : values()) {
            intMap.put(template.getCode(), template);
            stringMap.put(template.getValue(), template);
        }
    }

    //注意  构造函数：1、不能为public；2.不需要显示声明为private
    Template(int code, String value) {
        this.code = code;
        this.value = value;
    }

    //注意  static
    public static Template of(int code) {
        return intMap.get(code);
    }

    public static Template of(String value) {
        return stringMap.get(value);
    }

}
