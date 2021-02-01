package java8;

import org.junit.Test;

import java.io.PrintStream;
import java.util.Comparator;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 一.方法引用：若lambda体中内容有方法已经实现了，我们可以使用“方法引用”
 * 可以理解方法引用是lambda表达式的另一种表现形式
 * <p>
 * 三种语法格式：
 * <p>
 * 对象:: 实例方法名
 * <p>
 * 类 :: 静态方法名
 * <p>
 * 类 :: 实例方法名
 * <p>
 * 注意：1.lambda体中调用方法的参数列表与返回值，要与函数式接口的参数列表与返回值保持一致
 * 2.若lambda表达式参数列表中第一个参数是实例方法的调用者，而第二个参数是实例方法的参数时，可以使用 className::method
 * <p>
 * 二.构造器引用
 * <p>
 * 注意：需要调用的构造器参数列表与函数式接口中抽象方法的参数列表保持一致
 * <p>
 * 三.数组引用
 * Type[]::new
 */
public class TestMethodRef {

    //对象:: 实例方法名
    @Test
    public void test1() {
        PrintStream ps = System.out;
        Consumer con = (x) -> ps.println(x);

        Consumer con1 = ps::println;
        con1.accept("abcde");
    }

    public void test() {
        Employee emp = new Employee();
        Supplier<String> sup = () -> emp.getName();
        String name = sup.get();

        Supplier<Integer> sup1 = emp::getAge;
        Integer age = sup1.get();
    }

    //类 :: 静态方法名
    @Test
    public void test2() {
        Comparator<Integer> com = (x, y) -> Integer.compare(x, y);

        Comparator<Integer> com1 = Integer::compare;
    }

    //类 :: 实例方法名
    @Test
    public void test3() {
        BiPredicate<String, String> bp = (x, y) -> x.equals(y);

        BiPredicate<String, String> bp1 = String::equals;
    }

    //构造器引用
    @Test
    public void test4() {
        Supplier<Employee> sup = () -> new Employee();

        Supplier<Employee> sup1 = Employee::new;
    }

    //数组引用
    @Test
    public void test5() {
        Function<Integer, String[]> fun = (x) -> new String[x];

        Function<Integer, String[]> fun2 = String[]::new;
        String[] apply = fun2.apply(10);
        System.out.println(apply.length);
    }
}
