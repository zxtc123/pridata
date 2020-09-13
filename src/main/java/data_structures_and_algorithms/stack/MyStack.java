package data_structures_and_algorithms.stack;

public class MyStack {
    //储存
    private Object[] array;
    //栈的顶部
    private int top;
    //最大容量
    private int max;

    public MyStack(int size){
        max = size;
        top = -1;
        array = new Object[size];
    }

    //压入数据
    public void push(Object x){
        if((top+1)<max){
            array[++top]=x;
        }
    }

    //弹出数据
    public Object pop(){
        return array[top--];
    }

    //获取栈顶数据
    public Object peek(){
        return array[top];
    }

    //栈是否为空
    public boolean isEmpty(){
        return top==-1;
    }

    //栈是否满了
    public boolean isFull(){
        return top == max-1;
    }

    public static void main(String[] args) {
//        MyStack stack = new MyStack(10);
//        stack.push(3);
//        stack.push(2);
//        stack.push(1);
//        System.out.println(stack.peek());
//        while (!stack.isEmpty()){
//            System.out.println(stack.pop());
//        }
        MyStack stack = new MyStack(20);
        String str = "Hello World!";
        char[] chars = str.toCharArray();
        for(char c: chars){
            stack.push(c);
        }
        while (!stack.isEmpty()){
            System.out.print(stack.pop());
        }
    }
}
