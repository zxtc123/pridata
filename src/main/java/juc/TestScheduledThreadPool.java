package juc;

import java.util.Random;
import java.util.concurrent.*;

public class TestScheduledThreadPool {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ScheduledExecutorService pool = Executors.newScheduledThreadPool(5);

        for (int i = 0; i < 5; i++) {
            ScheduledFuture<Integer> schedule = pool.schedule(new Callable<Integer>() {
                @Override
                public Integer call() {
                    int num = new Random().nextInt(100);
                    System.out.println(Thread.currentThread().getName() + ":" + num);
                    return num;
                }
            }, 3, TimeUnit.SECONDS);
            System.out.println(schedule.get());
        }
        pool.shutdown();
    }
}
