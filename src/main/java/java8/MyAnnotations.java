package java8;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.ElementType.LOCAL_VARIABLE;


@Target({TYPE, FIELD, METHOD, PARAMETER, CONSTRUCTOR, LOCAL_VARIABLE})  //可以作用到什么上
@Retention(RetentionPolicy.RUNTIME)   //生命周期
public @interface MyAnnotations {  //重复注解的容器类
    MyAnnotation[] value();
}
