package leetcode.solution.basic;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 给你一个整数数组 nums，请你将该数组升序排列。
 * @author Administrator
 *1 <= nums.length <= 50000
 *-50000 <= nums[i] <= 50000
 *
 *归并排序利用了分治的思想来对序列进行排序。对一个长为 n 的待排序的序列，我们将其分解成两个长度为n/2
 *的子序列。每次先递归调用函数使两个子序列有序，然后我们再线性合并两个有序的子序列使整个序列有序。
 */
public class SortArray {
    public int[] sortArray(int[] nums) {
        Arrays.sort(nums);
        return nums;
    }
    
    public int[] sortArray2(int[] nums) {
    	int[]sort=new int[nums.length];
    	PriorityQueue<Integer>pq=new PriorityQueue<Integer>();
    	for(int i:nums){
    		pq.offer(i);
    	}
    	for(int j=0;j<nums.length;j++){
    		sort[j]=pq.poll();
    	}
    	return sort;
    }
    
    
    
    // 归并排序

    /**
     * 列表大小等于或小于该大小，将优先于 mergeSort 使用插入排序
     */
    private static final int INSERTION_SORT_THRESHOLD = 7;

    public int[] sortArray4(int[] nums) {
        int len = nums.length;
        int[] temp = new int[len];
        mergeSort(nums, 0, len - 1, temp);
        return nums;
    }
    
    /**
     * 对数组 nums 的子区间 [left, right] 进行归并排序
     *
     * @param nums
     * @param left
     * @param right
     * @param temp  用于合并两个有序数组的辅助数组，全局使用一份，避免多次创建和销毁
     */
    private void mergeSort(int[] nums, int left, int right, int[] temp) {
        // 小区间使用插入排序
        if (right - left <= INSERTION_SORT_THRESHOLD) {
            insertionSort(nums, left, right);
            return;
        }

        int mid = left + (right - left) / 2;
        // Java 里有更优的写法，在 left 和 right 都是大整数时，即使溢出，结论依然正确
        // int mid = (left + right) >>> 1;

        mergeSort(nums, left, mid, temp);
        mergeSort(nums, mid + 1, right, temp);
        // 如果数组的这个子区间本身有序，无需合并
        if (nums[mid] <= nums[mid + 1]) {
            return;
        }
        mergeOfTwoSortedArray(nums, left, mid, right, temp);
    }
    
    /**
     * 对数组 arr 的子区间 [left, right] 使用插入排序
     *
     * @param arr   给定数组
     * @param left  左边界，能取到
     * @param right 右边界，能取到
     */
    private void insertionSort(int[] arr, int left, int right) {
        for (int i = left + 1; i <= right; i++) {
            int temp = arr[i];
            int j = i;
            while (j > left && arr[j - 1] > temp) {
                arr[j] = arr[j - 1];
                j--;
            }
            arr[j] = temp;
        }
    }
    
    /**
     * 合并两个有序数组：先把值复制到临时数组，再合并回去
     *
     * @param nums
     * @param left
     * @param mid   [left, mid] 有序，[mid + 1, right] 有序
     * @param right
     * @param temp  全局使用的临时数组
     */
    private void mergeOfTwoSortedArray(int[] nums, int left, int mid, int right, int[] temp) {
        System.arraycopy(nums, left, temp, left, right + 1 - left);

        int i = left;
        int j = mid + 1;

        for (int k = left; k <= right; k++) {
            if (i == mid + 1) {
                nums[k] = temp[j];
                j++;
            } else if (j == right + 1) {
                nums[k] = temp[i];
                i++;
            } else if (temp[i] <= temp[j]) {
                // 注意写成 < 就丢失了稳定性（相同元素原来靠前的排序以后依然靠前）
                nums[k] = temp[i];
                i++;
            } else {
                // temp[i] > temp[j]
                nums[k] = temp[j];
                j++;
            }
        }
    }  
    
    public static void main(String[] args) {
    	SortArray s = new SortArray();
		int[]nums={5,2,3,1};
		int[]nums2={5,1,1,2,0,0};
		for(int i : s.sortArray4(nums)){
			System.out.println(i);	
		}
	}
}
