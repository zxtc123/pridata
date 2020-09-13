package java8;

/**
 * 策略模式，不同的判断方式
 * @Author: zhaoxin
 * @Date: 2020/9/8 11:22
 */
public interface MyPredicate<T> {

    boolean test(T t);
}
