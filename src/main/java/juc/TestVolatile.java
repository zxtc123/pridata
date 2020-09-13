package juc;

public class TestVolatile {
    public static void main(String[] args) {
        RunnableDemo r = new RunnableDemo();
        new Thread(r).start();
        while (true){
            if (r.getFlag()){
                System.out.println("---------------");
                break;
            }

        }
    }
}

class RunnableDemo implements Runnable{

    private volatile boolean flag = false;

    @Override
    public void run() {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        flag = true;
       System.out.println("flag="+flag);
    }

    public boolean getFlag(){
        return this.flag;
    }
}