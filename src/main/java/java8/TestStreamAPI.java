package java8;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestStreamAPI {
    /**
     * 给定【1，2，3，4，5】返回【1，4，9，16，25】
     */
    @Test
    public void test() {
        Integer[] nums = new Integer[]{1, 2, 3, 4, 5};
        List<Integer> numList = Arrays.stream(nums)
                .map(x -> x * x)
                .collect(Collectors.toList());
        System.out.println(numList);
    }
}
