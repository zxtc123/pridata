package juc;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class TestCallable {

    public static void main(String[] args) {
        TheadDemo theadDemo = new TheadDemo();
        //用FutureTask来接受结果
        FutureTask result = new FutureTask(theadDemo);
        new Thread(result).start();

        try {
            //类似闭锁的操作
            //线程未执行完，这步操作不进行
            Integer sum = (Integer) result.get();
            System.out.println(sum);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}

class TheadDemo implements Callable<Integer> {

    int sum = 0;

    @Override
    public Integer call() throws Exception {
        for (int i = 0; i <= 100; i++) {
            System.out.println(i);
            sum += i;
        }
        return sum;
    }
}
