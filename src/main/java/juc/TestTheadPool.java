package juc;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestTheadPool {
    public static void main(String[] args) {
        TheadPoolDemo tp = new TheadPoolDemo();
        ExecutorService pool = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 10; i++) {
            pool.submit(tp);
        }
        pool.shutdown();
    }
}

class TheadPoolDemo implements Runnable {
    private int i = 0;

    @Override
    public void run() {
        while (i < 100) {
            System.out.println(Thread.currentThread().getName() + ":" + ++i);
        }
    }
}