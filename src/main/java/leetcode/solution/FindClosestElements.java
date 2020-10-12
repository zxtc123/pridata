package leetcode.solution;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 给定一个排序好的数组，两个整数 k 和 x，从数组中找到最靠近 x（两数之差最小）的 k 个数。
 * 返回的结果必须要是按升序排好的。如果有两个数与 x 的差值一样，优先选择数值较小的那个数。
 * 示例 1:

输入: [1,2,3,4,5], k=4, x=3
输出: [1,2,3,4]
 

示例 2:

输入: [1,2,3,4,5], k=4, x=-1
输出: [1,2,3,4]
 * @author Administrator
 *
 */
public class FindClosestElements {
	
 	public List<Integer> findClosestElements4(int[] arr, int k, int x) {
        List<Integer> ret = Arrays.stream(arr).boxed().collect(Collectors.toList());
		int n = ret.size();
		if (x <= ret.get(0)) {//目标值比arr最小的要小
			return ret.subList(0, k);
		} else if (ret.get(n - 1) <= x) {//目标值比arr最大的要大
			return ret.subList(n - k, n);
		} else {
			//-(low + 1) key not found
			int index = Collections.binarySearch(ret, x);
		    int leftindex = index-1;
	        int rightindex = index+1;
	        while(rightindex-leftindex-1<k){
	            if(leftindex<0){
	                rightindex++;
	                continue;
	            } 
	            if(rightindex>=arr.length){
	                leftindex--;
	                continue;
	            } 
	            if(x-arr[leftindex]<=arr[rightindex]-x) leftindex--;
	            else rightindex++;
	        }
			
			return ret.subList(leftindex, rightindex-1);
		}
	}
	
 	public List<Integer> findClosestElements3(int[] arr, int k, int x) {
        List<Integer> ret = Arrays.stream(arr).boxed().collect(Collectors.toList());
		int n = ret.size();
		if (x <= ret.get(0)) {//目标值比arr最小的要小
			return ret.subList(0, k);
		} else if (ret.get(n - 1) <= x) {//目标值比arr最大的要大
			return ret.subList(n - k, n);
		} else {
			//-(low + 1) key not found
			int index = Collections.binarySearch(ret, x);
			if (index < 0)
				index = -index - 1;//low 且这个low恰巧大于x一点
			int low = Math.max(0, index - k - 1), high = Math.min(ret.size() - 1, index + k - 1);
			//2个指针指向取值范围的最低和最大值
			//判断最小、最大值与x相差的值，不断移动最大、最小指针
			while (high - low > k - 1) {
				if ((x - ret.get(low)) <= (ret.get(high) - x))
					high--;
				else if ((x - ret.get(low)) > (ret.get(high) - x))
					low++;
				else
					System.out.println("unhandled case: " + low + " " + high);
			}
			return ret.subList(low, high + 1);
		}
	}

	
	/**
	 * 我们可以将数组中的元素按照与目标 x 的差的绝对值排序，排好序后前 k 个元素就是我们需要的答案。
	 * @param arr
	 * @param k
	 * @param x
	 * @return
	 */
    public List<Integer> findClosestElements2(int[] arr, int k, int x) {
    	//              将char[]转换为intStream  将intStream转换为Stream		收集器，将流转换为其他数据结构
        List<Integer> ret = Arrays.stream(arr).boxed().collect(Collectors.toList());
        Collections.sort(ret, (a,b) -> a == b ? a - b : Math.abs(a-x) - Math.abs(b-x));//比较与目标值X差值的大小
        ret = ret.subList(0, k);
        Collections.sort(ret);
        return ret;
    }
	
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
    	int[] search = binarySearch(arr,x);
    	List<Integer> closlist = new ArrayList<>();
    	int l = search[0];
    	int r = search[1];
    	if(l==r){
    		closlist.add(arr[l]);
    		k--;
    		l--;
    		r++;
    	}else if(arr[r]-x < x-arr[l]){
    		closlist.add(arr[r]);
    		r++;
    		k--;
    	}
    	while(k > 0){
    		if(l>=0){
    			closlist.add(arr[l]);
    			l--;
    			k--;
    		}
    		
    		if(k>0 && r<arr.length){
    			closlist.add(arr[r]);
    			r++;
    			k--;
    		}
    	}
    	Collections.sort(closlist);
    	return closlist;
    }
    
    private int[] binarySearch(int[] arr, int search){
    	int l=0;
    	int r=arr.length-1;    	
    	while(l<=r){
    		int mid = (l+r)/2;
    		if(search ==arr[mid])
    			return new int[]{mid,mid};
    		else if(search > arr[mid])
    			l=mid+1;
    		else if(search < arr[mid])
    			r=mid-1;
    	}
    	return new int[]{r,l};
    }
    
    public static void main(String[] args) {
    	FindClosestElements f = new FindClosestElements();
    	//int[]arr={1,1,1,10,10,10};
    	int[]arr={1,2,3,4,5};
    	List<Integer> list = f.findClosestElements(arr,4,3);
    	for(int i:list){
    		System.out.print(i);
    	}
    
	}
}
