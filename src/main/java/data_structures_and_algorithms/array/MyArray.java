package data_structures_and_algorithms.array;

/**
 * 实现基本的数据结构功能
 * 1.查找
 * 2.插入
 * 3.删除
 * 4.迭代
 */
public class MyArray {
    //存储数据的数组
    private int[] array;
    //数组内实际元素个数
    private int elems;
    //数据最大容量
    private int length;

    public MyArray() {
        this.elems = 0;
        this.length = 50;
        array = new int[length];
    }

    public MyArray(int length) {
        this.elems = 0;
        this.length = length;
        array = new int[length];
    }

    //根据下标查找数据元素
    public int get(int i) {
        if (i < 0 || i >= elems) System.out.println("数组越界");
        return array[i];
    }

    //在数组末尾插入元素
    public void insert(int i) {
        if (elems == length) System.out.println("数组已达到最大容量，需要扩容");
        array[elems] = i;
        elems++;
    }

    //删除指定元素 true删除成功 false删除失败
    public boolean delete(int value) {
        int k = find(value);
        if (k == -1) {
            return false;
        } else {
            for (int i = k; i < elems - 1; i++) {
                array[k] = array[k + 1];
            }
            elems--;
        }
        return true;
    }

    //通过遍历，查询指定元素,返回元素下标,没有找到返回-1
//    public int find(int value){
//        for (int i = 0; i < elems; i++) {
//            if(array[i]==value)return i;
//        }
//        return -1;
//    }

    //当数组有序的时候，使用二分查找，查询指定元素
    public int find(int value) {
        int low = 0;
        int high = elems - 1;
        int cur;

        while (true) {
            cur = low + (high - low) / 2;
            if (array[cur] == value) {
                return cur;
            } else if (low > high) {
                return -1;
            } else if (array[cur] > value) {
                high = cur - 1;
            } else {
                low = cur + 1;
            }
        }
    }

    //遍历数组
    public void display() {
        for (int i = 0; i < elems; i++) {
            System.out.println(array[i]);
        }
    }
}
