package leetcode.solution;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 给定一个无重复元素的数组 candidates 和一个目标数 target ，
 * 找出 candidates 中所有可以使数字和为 target 的组合。
 * candidates 中的数字可以无限制重复被选取。
 * @author Administrator
 *
 */
public class CombinationSum {
	Set<List<Integer>>set = new HashSet<>();
    public List<List<Integer>> combinationSum(int[] candidates, int target) {

    }
    
    public void dfs(int[] candidates, int target, int low, int high){
    	
    	if(target == candidates[low]){
    		
    	}
    	dfs(candidates, target, low+1, high);
    	
    	
    	
    	
    }
}
