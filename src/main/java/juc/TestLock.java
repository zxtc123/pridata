package juc;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TestLock {
    public static void main(String[] args) {
        Ticket ticket = new Ticket();
        new Thread(ticket, "一号窗口").start();
        new Thread(ticket, "二号窗口").start();
        new Thread(ticket, "三号窗口").start();
    }
}

class Ticket implements Runnable{

    private int ticket = 100;

    Lock lock = new ReentrantLock();

    @Override
    public void run() {
        while (true){

            lock.lock();
            try {
                if(ticket>0){
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName()+"余票"+ --ticket);
                }
            }finally {
                lock.unlock();
            }

        }
    }
}
