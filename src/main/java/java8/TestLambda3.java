package java8;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * 四大内置函数式接口
 *
 * Consumer<T> 消费型接口
 *      void accept(T t);
 *
 * Supplier<T> 供给型接口
 *      T get();
 *
 * Function<T, R> 函数型接口
 *      R apply(T t);
 *
 * Predicate<T> 断言型接口
 *      boolean test(T t);
 */
public class TestLambda3 {

    /**
     * Consumer<T> 消费型接口
     */
    @Test
    public void consumer(){
        happy(1000, (m) ->  System.out.println("大宝剑消费"+m));
    }

    private void happy(double money, Consumer<Double> con){
        con.accept(money);
    }

    /**
     * Supplier<T> 供给型接口
     * 产生对象
     */
    @Test
    public void supplier(){
        List<Integer> numList = getNumList(10, () -> (int) (Math.random()*100));
        for(Integer i : numList){
            System.out.println(i);
        }
    }

    //产生指定个数的整数，并放入集合
    private List<Integer> getNumList(int sum, Supplier<Integer> sup){
        List list = new ArrayList();

        for(int i=0;i<sum;i++){
            Integer integer = sup.get();
            list.add(integer);
        }

        return list;
    }

    /**
     * Function<T, R> 函数型接口
     */
    @Test
    public void function(){
        String s = handlerString("  我爱赵佳欣  ", (str) -> str.trim());
        System.out.println(s);
        String s1 =handlerString("  我爱赵佳欣  ", (str) -> str.trim().substring(2,5));
        System.out.println(s1);
    }

    //处理字符串
    private String handlerString(String str, Function<String, String>fun){
        return fun.apply(str);
    }

    /**
     * Predicate<T> 断言型接口
     */
    @Test
    public void predicate(){
        List<String> list = Arrays.asList("hello", "world", "zhaoxin", "jiaxin", "ok");
        List<String> strings = filterString(list, (s) -> s.length() > 3);
        for(String s: strings){
            System.out.println(s);
        }
    }

    //将满足条件的字符串放入集合中
    private List<String> filterString(List<String>list, Predicate<String> pre){
        List newList = new ArrayList();

        for(String str : list){
            if(pre.test(str)){
                newList.add(str);
            }
        }

        return newList;
    }
}
