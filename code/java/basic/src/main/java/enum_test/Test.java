package enum_test;

import java.util.EnumMap;
import java.util.EnumSet;
import java.util.Map;

/**
 * Function Description: <br>
 * Writter: p. <br>
 * Creating Time : 2019/3/16  11:00 <br>
 */
public class Test {
    public static void main(String[] args) {
        compareTo();
        getDeclaringClass();
        name();
        ordinal();
        toStringTest();
        valueOf();
        method();
        enumSet();
        enumMap();
        System.out.println("=============测试 标准模板=======");
        System.out.println(Template.valueOf("B"));
        System.out.println("=============测试 of=======");
        System.out.println(Template.of("b"));
    }

    public static void compareTo() {
        System.out.println("=========测试 compareTo()=================");
        System.out.println(Animal.DOG.compareTo(Animal.CAT));
    }

    public static void getDeclaringClass() {
        System.out.println("=========测试 getDeclaringClass()=================");
        System.out.println(Animal.DOG.getDeclaringClass());
    }

    public static void name() {
        System.out.println("=========测试 name()=================");
        System.out.println(Animal.DOG.name());
    }

    public static void ordinal() {
        System.out.println("=========测试 ordinal()=================");
        System.out.println(Animal.DOG.ordinal());
    }

    public static void toStringTest() {
        System.out.println("=========测试 toString()=================");
        System.out.println(Animal.DOG.toString());
    }

    public static void valueOf() {
        System.out.println("=========测试 valueOf()=================");
        System.out.println(Animal.DOG.valueOf("CAT"));
    }

    public static void method() {
        System.out.println("=========测试 自定义方法的重写()=================");
        System.out.println(Animal.CAT.getName());
        System.out.println(Animal.DOG.getName());
    }

    public static void enumSet() {
        System.out.println("=========测试 EnumSet()=================");
        EnumSet<Animal> enumSet = EnumSet.allOf(Animal.class);
        for (Animal animal : enumSet) {
            System.out.println(animal.name());
        }
    }

    public static void enumMap() {
        System.out.println("=========测试 enumMap()=================");
        EnumMap<Animal, String> enumMap = new EnumMap<Animal, String>(Animal.class);
        enumMap.put(Animal.CAT, "加菲");
        enumMap.put(Animal.DOG, "旺财");
        for (Map.Entry<Animal, String> entry : enumMap.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }
    }
}
