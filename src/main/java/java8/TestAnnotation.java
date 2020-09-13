package java8;

import org.junit.Test;

import java.lang.reflect.Method;

/**
 * 重复注解与类型注解
 */
public class TestAnnotation {

    @Test
    public void test() throws Exception {
        Class<TestAnnotation> clazz = TestAnnotation.class;
        Method method = clazz.getMethod("show");

        MyAnnotation[] annotationsByType = method.getAnnotationsByType(MyAnnotation.class);
        for(MyAnnotation annotation : annotationsByType){
            System.out.println(annotation.value());
        }
    }

    @MyAnnotation("hello")
    @MyAnnotation("world")
    public void show(@MyAnnotation("abc") String str){}
}
