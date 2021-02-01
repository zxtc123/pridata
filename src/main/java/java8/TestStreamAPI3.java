package java8;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 终止操作
 */
public class TestStreamAPI3 {

    List<Employee> emps = Arrays.asList(
            new Employee(101, "张三", 18, 5000.00, Employee.Status.FREE),
            new Employee(102, "李四", 29, 666.66, Employee.Status.BUSY),
            new Employee(103, "王五", 37, 444.44, Employee.Status.VACATION),
            new Employee(104, "赵六", 45, 5567.00, Employee.Status.FREE),
            new Employee(105, "钱七", 8, 15000.00, Employee.Status.BUSY),
            new Employee(105, "钱七", 8, 15000.00, Employee.Status.BUSY)
    );

    /**
     * 查找与匹配
     * allMatch--检查是否匹配所有元素
     * anyMatch--检查是否至少匹配一个元素
     * noneMatch--检查是否没有匹配所有元素
     * findFirst--返回第一个元素
     * findAny--返回当前流中任意元素
     * count--返回当前流中元素总数
     * max--返回流中最大元素
     * min--返回流中最小元素
     */

    @Test
    public void match() {
        boolean b1 = emps.stream()
                .allMatch((e) -> e.getStatus().equals(Employee.Status.BUSY));
        System.out.println(b1);

        boolean b2 = emps.stream()
                .anyMatch((e) -> e.getStatus().equals(Employee.Status.BUSY));
        System.out.println(b2);

        boolean b3 = emps.stream()
                .noneMatch((e) -> e.getStatus().equals(Employee.Status.BUSY));
        System.out.println(b3);

        //获取工资最少的员工
        Optional<Employee> employee = emps.stream()
                .sorted((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary()))
                .findFirst();
        System.out.println(employee.get());

        Optional<Employee> employee2 = emps.stream()
                .filter((e) -> e.getStatus().equals(Employee.Status.FREE))
                .findAny();
        System.out.println(employee2.get());
    }

    @Test
    public void count() {
        long count = emps.stream()
                .count();
        System.out.println(count);

        //获取工资最高的员工
        Optional<Employee> op = emps.stream()
                .max((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary()));
        System.out.println(op.get());

        //获取最低的工资数值(先映射出工资，在取最小值)
        Optional<Double> min = emps.stream()
                .map(Employee::getSalary)
                .min(Double::compare);
        System.out.println(min.get());
    }

    /**
     * 归约
     * T reduce(T identity, BinaryOperator<T> accumulator)/Optional<T> reduce(BinaryOperator<T> accumulator)--可以将流中元素反复结合起来，得到一个值
     */
    @Test
    public void reduce() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        //起始值identity 2元运算BinaryOperator
        Integer reduce = list.stream()
                .reduce(0, (x, y) -> x + y);
        System.out.println(reduce);

        //计算员工工资总和
        //没有起始值，可能为空
        Optional<Double> reduce1 = emps.stream()
                .map(Employee::getSalary)
                .reduce(Double::sum);
        System.out.println(reduce1.get());
    }

    /**
     * 收集
     * collect--将流转换为其他形式，接受一个collector接口的实现，用于给stream元素做汇总方法
     */
    @Test
    public void collect() {
        //Collector收集器
        //Collectors工具类
        //收集到list
        emps.stream()
                .map(Employee::getName)
                .collect(Collectors.toList())
                .forEach(System.out::println);

        System.out.println("-------------------------------");

        //收集到set
        emps.stream()
                .map(Employee::getName)
                .collect(Collectors.toSet())
                .forEach(System.out::println);

        System.out.println("-------------------------------");

        //特殊的集合
        emps.stream()
                .map(Employee::getName)
                .collect(Collectors.toCollection(HashSet::new))
                .forEach(System.out::println);
    }

    @Test
    public void collect2() {
        //总数
        Long count = emps.stream()
                .collect(Collectors.counting());
        System.out.println(count);

        System.out.println("-------------------------------");

        //平均值
        Double avg = emps.stream()
                .collect(Collectors.averagingDouble(Employee::getSalary));
        System.out.println(avg);

        System.out.println("-------------------------------");

        //总和
        Double sum = emps.stream()
                .collect(Collectors.summingDouble(Employee::getSalary));
        System.out.println(sum);

        System.out.println("-------------------------------");

        //最大值
        Optional<Double> max = emps.stream()
                .map(Employee::getSalary)
                .collect(Collectors.maxBy(Double::compareTo));
        System.out.println(max.get());

        System.out.println("-------------------------------");

        //工资最小值
        Optional<Double> min = emps.stream()
                .map(Employee::getSalary)
                .collect(Collectors.minBy(Double::compareTo));
        System.out.println(min.get());
    }

    //分组
    @Test
    public void group() {
        Map<Employee.Status, List<Employee>> collect = emps.stream()
                .collect(Collectors.groupingBy(Employee::getStatus));
        System.out.println(collect);
    }

    //多级分组
    @Test
    public void dGroup() {
        Map<Employee.Status, Map<String, List<Employee>>> collect = emps.stream()
                .collect(Collectors.groupingBy(Employee::getStatus, Collectors.groupingBy(e -> {
                    if (e.getAge() <= 35) {
                        return "青年";
                    } else if (e.getAge() <= 50) {
                        return "中年";
                    } else {
                        return "老年";
                    }
                })));
        System.out.println(collect);
    }

    //分区
    //满足条件true和不满足条件false
    @Test
    public void dvision() {
        Map<Boolean, List<Employee>> collect = emps.stream()
                .collect(Collectors.partitioningBy(e -> e.getSalary() > 8000));
        System.out.println(collect);
    }

    @Test
    public void join() {
        String str = emps.stream()
                .map(Employee::getName)
                .collect(Collectors.joining(","));
        System.out.println(str);
    }


}
