package data_structures_and_algorithms.queue;

/**
 * 优先队列
 */
public class MyPriorityQueue {
    private int[] array;//使用int[]便于比较
    private int maxSize;//最大容量
    private int nItem;//实际元素数

    public MyPriorityQueue(int size){
        maxSize = size;
        array = new int[size];
        nItem = 0;
    }

    /**
     * 插入
     */
    public void insert(int s){
        if(!isFull()){
            if(nItem == 0){
                array[nItem++] = s;
            }else{
                int j = nItem - 1;
                //使用插入排序
                while(j >= 0 && array[j] > s){
                    array[j+1] = array[j];
                    j--;
                }
                array[j+1] = s;
                nItem++;
            }
        }
    }

    /**
     * 删除
     */
    public int remove(){
        int removeValue = -1;//置为-1表示没有
        //判断队列是否为空
        if(!isEmpty()){
            int k = nItem - 1;
            removeValue = array[k];
            array[k] = -1;
            nItem--;
        }
        return removeValue;
    }

    public int peek(){
        return array[nItem-1];
    }

    public boolean isFull(){
        return nItem == maxSize;
    }

    public boolean isEmpty(){
        return nItem == 0;
    }

    public static void main(String[] args) {
        MyPriorityQueue queue = new MyPriorityQueue(5);
        queue.insert(3);
        System.out.println(queue.peek());//[3,0,0,0,0]
        queue.insert(5);
        System.out.println(queue.peek());//[3,5,0,0,0]
        queue.insert(7);
        System.out.println(queue.peek());//[3,5,7,0,0]
        queue.insert(2);
        System.out.println(queue.peek());//[2,3,5,7,0]
        queue.insert(1);
        System.out.println(queue.peek());//[1,2,3,5,7]
        queue.remove();
        System.out.println(queue.peek());//[1,2,3,5,-1]
    }
}
