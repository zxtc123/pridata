package data_structures_and_algorithms.simpleSort;

/**
 * 选择排序
 */
public class ChoiceSort {

    public static int[] sort(int[] arr) {
        int len = arr.length;
        int min = 0;//每轮找到的最小值
        int temp;
        for (int i = 0; i < len - 1; i++) {
            //将最小值放在最前面，前面数据不断有序
            //未排数据范围不断缩小
            for (int j = i + 1; j < len; j++) {
                if (arr[j] < arr[min]) {
                    min = j;
                }
            }

            //将找到的最小值与队列最前面比较
            if (arr[min] < arr[i]) {
                temp = arr[i];
                arr[i] = arr[min];
                arr[min] = temp;
            }

            //看下每轮排序后的排序情况
            System.out.println("第" + (i + 1) + "轮排序后，排序结果：");
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
