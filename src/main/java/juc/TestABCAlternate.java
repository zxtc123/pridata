package juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 线程通信
 * 线程交替运行
 */
public class TestABCAlternate {
    public static void main(String[] args) {
        AlternateDemo ad = new AlternateDemo();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i <20 ; i++) {
                    ad.loopA(i);
                }
            }
        },"A").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i <20 ; i++) {
                    ad.loopB(i);
                }
            }
        },"B").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i <20 ; i++) {
                    ad.loopC(i);
                }
            }
        },"C").start();
    }
}

class AlternateDemo{
    private int number = 1;//当前执行线程标志

    private Lock lock = new ReentrantLock();
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();

    public void loopA(int totalLoop){
        lock.lock();

        try {
            //1判断
            if(number!=1) condition1.await();
            //2打印
            for (int i = 0; i <5 ; i++) {
                System.out.println(Thread.currentThread().getName()+"\t"+i+"\t"+totalLoop);
            }
            //3唤醒
            number=2;
            condition2.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
    public void loopB(int totalLoop){
        lock.lock();

        try {
            //1判断
            if(number!=2) condition2.await();
            //2打印
            for (int i = 0; i <10 ; i++) {
                System.out.println(Thread.currentThread().getName()+"\t"+i+"\t"+totalLoop);
            }
            //3唤醒
            number=3;
            condition3.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
    public void loopC(int totalLoop){
        lock.lock();

        try {
            //1判断
            if(number!=3) condition3.await();
            //2打印
            for (int i = 0; i <5 ; i++) {
                System.out.println(Thread.currentThread().getName()+"\t"+i+"\t"+totalLoop);
            }
            //3唤醒
            number=1;
            condition1.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}
