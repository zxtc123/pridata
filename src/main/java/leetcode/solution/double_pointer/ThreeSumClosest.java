package leetcode.solution.double_pointer;

import java.util.Arrays;

/**
 * 给定一个包括 n 个整数的数组 nums 和 一个目标值 target。找出 nums 中的三个整数，
 * 使得它们的和与 target 最接近。返回这三个数的和。假定每组输入只存在唯一答案。

 *输入：nums = [-1,2,1,-4], target = 1
输出：2
解释：与 target 最接近的和是 2 (-1 + 2 + 1 = 2) 。
 *
 */
public class ThreeSumClosest {
    public int threeSumClosest(int[] nums, int target) {
    	int threeClosest=0;
    	int length = nums.length;
    	Arrays.sort(nums);
		int third = length-1;
		threeClosest = nums[0]+nums[1]+nums[third];
    	for(int first=0;first<length-2;first++){
    		int rest = target - nums[first];
    		for(int second = first+1;second<length-1 && second < third;second++){
    			while(second < third && nums[second]+nums[third]<rest){
    				third--;
    				int sum = nums[first] + nums[second] + nums[third];
       			 	if(Math.abs(target - sum) < Math.abs(target - threeClosest))
       			 		threeClosest = sum;
    			}
    			if (second == third) {
    				break;
    			}
    			if(second < third && nums[second]+nums[third]==rest){
    				threeClosest = target;
    				return threeClosest;
    			}
    		}
    	}
    	
    	
    	return threeClosest;
    }
    
    public int threeSumClosest2(int[] nums, int target) {
        Arrays.sort(nums);
        int ans = nums[0] + nums[1] + nums[2];
        for(int i=0;i<nums.length;i++) {
            int start = i+1, end = nums.length - 1;
            while(start < end) {
                int sum = nums[start] + nums[end] + nums[i];
                if(Math.abs(target - sum) < Math.abs(target - ans))
                    ans = sum;
                if(sum > target)
                    end--;
                else if(sum < target)
                    start++;
                else
                    return ans;
            }
        }
        return ans;
    }
    
    public static void main(String[] args) {
    	ThreeSumClosest t = new ThreeSumClosest();
    	int[]nums = {-1,2,1,-4};
    	int[]nums2={1,1,1,0};
    	int[]nums3={0,2,1,-3};
    	int[]nums4={1,1,-1,-1,3};
    	int[]nums5={1,2,4,8,16,32,64,128};
//    	System.out.println(t.threeSumClosest(nums2, -100));
//    	System.out.println(t.threeSumClosest(nums2, 100));
//    	System.out.println(t.threeSumClosest(nums3, 1));
//    	System.out.println(t.threeSumClosest(nums4, -1));
    	System.out.println(t.threeSumClosest(nums5, 82));
	}
    
}
