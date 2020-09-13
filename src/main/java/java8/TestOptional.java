package java8;

import org.junit.Test;

import java.util.Optional;

public class TestOptional {

    @Test
    public void test1(){
        Optional<Employee> op = Optional.of(new Employee());
        System.out.println(op.get());
    }
}
