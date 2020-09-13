package java8;

/**
 * 2个泛型，R标识返回值，T为参数
 * 2个入参
 * @param <R>
 * @param <T>
 */
@FunctionalInterface
public interface MyFunction2<R, T> {

    R getValue(T t1, T t2);
}
