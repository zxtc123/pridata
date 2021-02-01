package data_structures_and_algorithms.queue;

/**
 * 队列
 */
public class MyQueue {
    private Object[] array;
    private int maxSize;//最大容量
    private int head;//队头
    private int tail;//队尾
    private int nItem;//实际元素数

    public MyQueue(int size) {
        maxSize = size;
        array = new Object[size];
        head = 0;
        tail = -1;
        nItem = 0;
    }

    /**
     * 插入
     *
     * @param obj
     */
    public void insert(Object obj) {
        //判断是否超过最大容量
        if (!isFull()) {
            //到头时，将尾部移到队列开头
            if (tail == maxSize - 1) tail = -1;
            array[++tail] = obj;
            nItem++;
        }
    }

    /**
     * 删除
     *
     * @return
     */
    public Object remove() {
        Object removeValue = null;
        //判断队列是否为空
        if (!isEmpty()) {
            Object headValue = array[head];
            array[head] = null;//gc
            if (head == maxSize - 1)
                head = 0;
            else
                head++;
            removeValue = headValue;
            nItem--;
        }
        return removeValue;
    }

    /**
     * 查看队头元素
     *
     * @return
     */
    public Object peekHead() {
        return array[head];
    }

    /**
     * 查看队尾元素
     *
     * @return
     */
    public Object peekTail() {
        return array[tail];
    }

    public boolean isFull() {
        return nItem == maxSize;
    }

    public boolean isEmpty() {
        return nItem == 0;
    }


    public static void main(String[] args) {
        MyQueue queue = new MyQueue(5);
        queue.insert(1);
        queue.insert(2);
        queue.insert(3);
        queue.insert(4);
        queue.insert(5);
        System.out.println(queue.peekHead());
        System.out.println(queue.peekTail());//[1,2,3,4,5]

        System.out.println("---------------------");

        queue.remove();
        System.out.println(queue.peekHead());
        System.out.println(queue.peekTail());//[null,2,3,4,5]
        queue.remove();
        System.out.println(queue.peekHead());
        System.out.println(queue.peekTail());//[null,null,3,4,5]

        System.out.println("---------------------");

        queue.insert(6);
        System.out.println(queue.peekHead());
        System.out.println(queue.peekTail());//[6,null,3,4,5]
        queue.insert(7);
        System.out.println(queue.peekHead());
        System.out.println(queue.peekTail());//[6,7,3,4,5]
        queue.insert(8);
        System.out.println(queue.peekHead());
        System.out.println(queue.peekTail());//满了[6,7,3,4,5]

    }


}
