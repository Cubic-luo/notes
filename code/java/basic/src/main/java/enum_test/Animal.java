package enum_test;

/**
 * Function Description:最基本的枚举 <br>
 * Creating Time : 2019/3/16  10:57 <br>
 */
public enum Animal {
    DOG("dog") {
        @Override
        public String getName() {
            return "旺财";
        }

    },
    CAT("cat");
    private final String name;//自定义属性

    Animal(String name) {
        this.name = name;
    }

    public static void of() {

    }

    public String getName() {
        //自定义方法
        return name;
    }
}
