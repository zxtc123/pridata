package juc;

/**
 *生产者/消费者模型可能存在虚假唤醒
 * 永远都要把wait()放到循环语句里面
 */
public class TestProductConsumer {
    public static void main(String[] args) {
        Clerk clerk = new Clerk();
        Productor productor = new Productor(clerk);
        Consumer consumer = new Consumer(clerk);

        new Thread(productor,"生产者A").start();
        new Thread(consumer,"消费者B").start();
        new Thread(productor,"生产者C").start();
        new Thread(consumer,"消费者D").start();
    }
}

class Clerk{
    private int product = 0;

    //进货
    public synchronized void get(){
        while (product>=1){
            System.out.println("货物已满");
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName()+":"+ ++product);
        this.notifyAll();

    }

    //出货
    public synchronized void sale(){
        while(product<=0){
            System.out.println("货物缺货");
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName()+":"+ --product);
        this.notifyAll();

    }
}

class Productor implements Runnable{

    private Clerk clerk;

    public Productor(Clerk clerk){
        this.clerk = clerk;
    }

    @Override
    public void run() {
        for (int i = 0; i <20 ; i++) {
            clerk.get();
        }
    }
}

class Consumer implements Runnable{

    private Clerk clerk;

    public Consumer(Clerk clerk){
        this.clerk = clerk;
    }
    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            clerk.sale();
        }
    }
}
