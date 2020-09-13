package java8;

import org.junit.Test;

import java.util.*;

public class TestLambda {

    List<Employee> emps = Arrays.asList(
            new Employee(101, "张三", 18, 5000.00),
            new Employee(102, "李四", 29, 666.66),
            new Employee(103, "王五", 37, 444.44),
            new Employee(104, "赵六", 45, 5567.00),
            new Employee(105, "钱七", 8, 15000.00)
    );

    //传入不同的策略对员工进行过滤
    public List<Employee> filterEmployee(List<Employee> list, MyPredicate<Employee> mp){
        List<Employee> emps = new ArrayList<>();

        for(Employee e : list){
            if(mp.test(e))emps.add(e);
        }

        return emps;
    }

    @Test
    public void testFilter(){
        //使用匿名内部类
        //按照年龄过滤
        List<Employee> employees = filterEmployee(emps, new MyPredicate<Employee>() {
            @Override
            public boolean test(Employee employee) {
                return employee.getAge() > 25;
            }
        });

        for(Employee e : employees){
            System.out.println(e);
        }
    }

    @Test
    public void testFilter2(){
        //使用Lambda表达式
        List<Employee> employees = filterEmployee(emps, (e) -> e.getAge() > 25);
        employees.forEach(System.out::println);
    }

    @Test
    public void testFilter3(){
        emps.stream()
                .filter((e) -> e.getAge() > 25)
                .limit(2)
                .forEach(System.out::println);
    }

    //先对员工年龄进行比较，在对姓名进行比较
    @Test
    public void test1(){
//        Collections.sort(emps, new Comparator<Employee>() {
//            @Override
//            public int compare(Employee e1, Employee e2) {
//                if(e1.getAge() == e2.getAge()){
//                    return e1.getName().compareTo(e2.getName());
//                }else{
//                    return e1.getAge().compareTo(e2.getAge());
//                }
//            }
//        });

        Collections.sort(emps, (e1, e2) -> {
            if(e1.getAge() == e2.getAge()){
                return e1.getName().compareTo(e2.getName());
            }else{
                return e1.getAge().compareTo(e2.getAge());
            }
        });

        for(Employee e : emps){
            System.out.println(e);
        }
    }

    @Test
    public void test2(){
        String handlerString = handlerString("\t\t\t\t waizjx   ", (str) -> str.trim());
        System.out.println(handlerString);
    }

    //用于处理字符串
    public String handlerString(String str, MyFunction mf){
        return mf.getValue(str);
    }

    @Test
    public void test3(){
        op(100L, 200L, (x, y) -> x + y);

        op(100L, 200L, (x, y) -> x * y);
    }

    //对2个LONG型数据进行运算
    public void op(Long l1, Long l2, MyFunction2<Long, Long> mf){
        System.out.println(mf.getValue(l1, l2));
    }
}
