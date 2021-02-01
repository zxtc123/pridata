package juc;

import java.util.concurrent.atomic.AtomicInteger;

public class TestAtomic {
    public static void main(String[] args) {
        AtomicThread at = new AtomicThread();
        for (int i = 0; i < 10; i++) {
            new Thread(at).start();
        }
    }
}

class AtomicThread implements Runnable {

    //private volatile int serialNumber = 0;
    private AtomicInteger serialNumber = new AtomicInteger();

    @Override
    public void run() {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + ":" + getSerialNumber());
    }

    public int getSerialNumber() {
        return serialNumber.getAndIncrement();
    }
}
