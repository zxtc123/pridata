package leetcode.solution.sort;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 设计一个算法，找出数组中最小的k个数。以任意顺序返回这k个数均可。
 * <p>
 * 输入： arr = [1,3,5,7,2,4,6,8], k = 4
 * 输出： [1,2,3,4]
 */
public class SmallestK {
    public int[] smallestK(int[] arr, int k) {
        Arrays.sort(arr);
        int[] newArr = new int[k];
        for (int i = 0; i < k; i++) {
            newArr[i] = arr[i];
        }
        return newArr;
    }

    public int[] smallestK2(int[] arr, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue();
        for (int i : arr) {
            pq.add(i);
        }
        int[] newArr = new int[k];
        for (int i = 0; i < k; i++) {
            newArr[i] = pq.poll();
        }
        return newArr;
    }

    private void insertSort(int[] arr) {
        int length = arr.length;
        for (int i = 1; i < length; i++) {
            int a = arr[i];
            int j = i;
            while (j > 0 && arr[j - 1] > a)
                arr[j] = arr[j--];
            arr[j] = a;
        }
    }

    public int[] smallestK3(int[] arr, int k) {
        if (k >= arr.length) {
            return arr;
        }

        int low = 0;
        int high = arr.length - 1;
        while (low < high) {
            int pos = partition(arr, low, high);
            if (pos == k - 1) {
                break;
            } else if (pos < k - 1) {
                low = pos + 1;
            } else {
                high = pos - 1;
            }
        }

        int[] dest = new int[k];
        System.arraycopy(arr, 0, dest, 0, k);
        return dest;
    }

    private int partition(int[] arr, int low, int high) {
        int pivot = arr[low];
        while (low < high) {
            while (low < high && arr[high] >= pivot) {
                high--;
            }

            arr[low] = arr[high];
            while (low < high && arr[low] <= pivot) {
                low++;
            }
            arr[high] = arr[low];
        }
        arr[low] = pivot;
        return low;
    }


}
