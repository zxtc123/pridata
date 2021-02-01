package java8;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class TestStreamAPI2 {

    List<Employee> emps = Arrays.asList(
            new Employee(101, "张三", 18, 5000.00),
            new Employee(102, "李四", 29, 666.66),
            new Employee(103, "王五", 37, 444.44),
            new Employee(104, "赵六", 45, 5567.00),
            new Employee(105, "钱七", 8, 15000.00),
            new Employee(105, "钱七", 8, 15000.00),
            new Employee(105, "钱七", 8, 15000.00)
    );

    //中间操作

    /**
     * 筛选与切片
     * filter--接受lambda,从流中排除某些元素
     * limit--截断流，使其数量不超过指定数
     * skip(n)--跳过元素，返回一个丢掉了前n个元素的流，不足n个返回空流
     * distinct--筛选，通过流所生成的元素的hashCode()和equals()去除重复元素
     */

    //内部迭代：迭代操作由Stream API完成
    @Test
    public void filter() {
        //中间操作不会执行任何操作
        //终止操作一次性执行全部操作
        emps.stream()
                .filter((e) -> e.getAge() > 35)
                .forEach(System.out::println);
    }

    @Test
    public void limit() {
        emps.stream()
                .filter((e) -> e.getSalary() >= 5000)
                .limit(2)
                .forEach(System.out::println);
    }

    @Test
    public void skip() {
        emps.stream()
                .filter((e) -> e.getSalary() >= 5000)
                .skip(2)
                .distinct()
                .forEach(System.out::println);
    }

    /**
     * 映射
     * map--接受lambda,将元素转换成其他形式或提取信息。接受一个函数作为参数，该函数会被应用到每个参数，并将其映射成一个新元素
     * flatMap--接受一个函数作为参数，将流中每个值换成另一个流，然后把所有流连接成一个流
     */
    @Test
    public void map() {
        List<String> list = Arrays.asList("aaa", "bbb", "ccc", "eee");
        list.stream()
                .map((str) -> str.toUpperCase())
                .forEach(System.out::println);

        System.out.println("-------------------------");

        emps.stream()
                .map(Employee::getName)
                .forEach(System.out::println);

        System.out.println("-------------------------");

        //返回的流中包含流
        Stream<Stream<Character>> stream = list.stream()
                .map(TestStreamAPI2::filterChar);
        stream.forEach((sm) -> {
            sm.forEach(System.out::println);
        });

        System.out.println("-------------------------");

        list.stream()
                .flatMap(TestStreamAPI2::filterChar)
                .forEach(System.out::println);
    }

    /**
     * map()类似add 整个元素添加到集合中
     * flatMap类似addAll集合元素中每个元素添加到集合中
     *
     * @param str
     * @return
     */
    public static Stream<Character> filterChar(String str) {
        List<Character> list = new ArrayList<>();
        for (Character ch : str.toCharArray()) {
            list.add(ch);
        }
        return list.stream();
    }

    /**
     * 排序
     * sorted()--自然排序(Comparable)
     * sorted(Comparator com)--定制排序（Comparator）
     */
    @Test
    public void sorted() {
        List<String> list = Arrays.asList("ddd", "aaa", "bbb", "ccc", "eee");
        list.stream()
                .sorted()
                .forEach(System.out::println);

        emps.stream()
                .sorted((e1, e2) -> {
                    if (e1.getAge().equals(e2.getAge())) {
                        return e1.getName().compareTo(e2.getName());
                    } else {
                        return e1.getAge().compareTo(e2.getAge());
                    }
                }).forEach(System.out::println);
    }
}
