package data_structures_and_algorithms.simpleSort;

/**
 * 冒泡排序
 */
public class BubbleSort {

    public static int[] sort(int[] arr) {
        int len = arr.length;
        int temp;
        for (int i = 1; i < len; i++) {
            boolean flag = false;
            for (int j = 0; j < len - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j + 1];
                    arr[j + 1] = arr[j];
                    arr[j] = temp;
                    flag = true;
                }
            }
            //可能存在没到最后一轮就已经排序完的情况
            //设置一个标识，当该轮没有交换的时候表示排序结束
            if (!flag) break;
            //看下每轮排序后的排序情况
            System.out.println("第" + i + "轮排序后，排序结果：");
            display(arr);

        }
        return arr;
    }

    public static void display(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] arr = {3, 4, 7, 5, 1, 2, 9, 6, 8};
        System.out.println("初始数据：");
        display(arr);
        System.out.println("-----------------------------------------");
        arr = sort(arr);
        System.out.println("-----------------------------------------");
        System.out.println("排序完成结果：");
        display(arr);
    }

}
