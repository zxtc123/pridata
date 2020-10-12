package leetcode.solution.double_pointer;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
 * @author Administrator
 *
 */
public class TwoSum {
	 public int[] twoSum(int[] nums, int target) {
	        int[] array = new int[2];
	        int length = nums.length;
	        if(length<2)return array;
	        for(int i=0;i<length-1;i++){
	        	for(int j=length-1;j>i;j--){
	        		if((nums[i]+nums[j])==target){
	        			array[0]=i;
	        			array[1]=j;
	        			return array;
	        		}
	        	}
	        }
	        return array;
	    }
	 
	 //2遍hash，遍历数组，将值存入hashmap，第2次遍历数组时，查询map里是否有对应结果
	    public int[] twoSum2(int[] nums, int target) {
	        Map<Integer, Integer> map = new HashMap<>();
	        for (int i = 0; i < nums.length; i++) {
	            map.put(nums[i], i);
	        }
	        for (int i = 0; i < nums.length; i++) {
	            int complement = target - nums[i];
	            if (map.containsKey(complement) && map.get(complement) != i) {
	                return new int[] { i, map.get(complement) };
	            }
	        }
	        throw new IllegalArgumentException("No two sum solution");
	    }
	    
	    //一遍hash，在遍历数组的同时，查询map里是否有对应值
	    public int[] twoSum3(int[] nums, int target) {
	        Map<Integer, Integer> map = new HashMap<>();
	        for (int i = 0; i < nums.length; i++) {
	            int complement = target - nums[i];
	            if (map.containsKey(complement)) {
	                return new int[] { map.get(complement), i };
	            }
	            map.put(nums[i], i);
	        }
	        throw new IllegalArgumentException("No two sum solution");
	    }
	  
	  
	  public static void main(String[] args) {
		  TwoSum t =new TwoSum();
		  int[]nums={2,7,11,15};
		  int[]nums2={3,2,4};
		  int[]nums3={2,5,5,11};
		 
		  for(int i : t.twoSum2(nums3, 10)){
			  System.out.println(i);
		  }
	}
}
