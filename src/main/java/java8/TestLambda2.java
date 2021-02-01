package java8;

import org.junit.Test;

import java.util.Comparator;
import java.util.function.Consumer;

/**
 * @Author: zhaoxin
 * @Date: 2020/9/8 14:52
 * Lambda表达式
 * ->将lambda表达式拆成2部分:
 * 左侧：表达式的参数列表
 * 右侧：表达式所需执行的功能
 * <p>
 * 语法格式一 无参数，无返回值
 * () -> System.out.println("Hello Lambda")
 * <p>
 * 语法格式二 一个参数，无返回值
 * (x) -> System.out.println(x)
 * <p>
 * 语法格式三 有2个以上参数，lambda体有多条语句，有返回值
 * Comparator<Integer> com = (x, y) -> {
 * System.out.println("函数式接口");
 * return Integer.compare(x, y);
 * };
 * <p>
 * 语法格式四 如果lambda体有只有一条语句，那大括号和return都可以不写
 * Comparator<Integer> com = (x, y) -> Integer.compare(x, y);
 * <p>
 * <p>
 * Lambda表达式需要函数式接口的支持
 * 接口中只有一个抽象方法
 * 可以使用注解@FunctionalInterface检查是否是函数式接口
 */
public class TestLambda2 {

    @Test
    public void test1() {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello Lambda");
            }
        };
        r.run();

        Runnable r1 = () -> System.out.println("Hello Lambda");
        r1.run();
    }

    @Test
    public void test2() {
        Consumer<String> con = (x) -> System.out.println(x);
        con.accept("我爱北京天安门");
    }

    @Test
    public void test3() {
        Comparator<Integer> com = (x, y) -> {
            System.out.println("函数式接口");
            return Integer.compare(x, y);
        };
    }

    @Test
    public void test4() {
        Comparator<Integer> com = (x, y) -> Integer.compare(x, y);
    }
}
