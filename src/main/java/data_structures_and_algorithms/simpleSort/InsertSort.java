package data_structures_and_algorithms.simpleSort;

/**
 * 插入排序
 */
public class InsertSort {

    public static int[] sort(int[] arr){
        int len = arr.length;
        for (int i = 0; i < len-1; i++) {
            //第1个数据默认有序，从后面开始排序
            int j = i+1;
            //待排数据插入有序序列时，待排数据是一个不断前移的过程
            //可以直接获取待排序列，将有序序列后移，达到边界条件时：j=0 或 待排数据大于等于前面的数据
            //将待排数据插入该位置
            int temp = arr[j];
            while(j>0 && temp<arr[j-1]){//未达到边界条件，不断后移，腾出待排数据的位置
                arr[j]=arr[--j];
            }
            arr[j]=temp;

            //看下每轮排序后的排序情况
            System.out.println("第"+(i+1)+"轮排序后，排序结果：");
            display(arr);
        }
        return arr;
    }

    public static void display(int[] arr){
        for(int i=0;i<arr.length;i++){
            System.out.print(arr[i]+" ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] arr = {3,4,7,5,1,2,9,6,8};
        System.out.println("初始数据：");
        display(arr);
        System.out.println("-----------------------------------------");
        arr = sort(arr);
        System.out.println("-----------------------------------------");
        System.out.println("排序完成结果：");
        display(arr);
    }
}
