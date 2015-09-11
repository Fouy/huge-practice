package com.way361.leetcode.combinationsum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * LeetCode | Combination Sum
 * @author xuefeihu<br>
 *
 *	题目：<br>
		Given a set of candidate numbers (C) and a target number (T), find all unique combinations in C where the candidate numbers sums to T.
	The same repeated number may be chosen from C unlimited number of times.<br>

	Note:
		<ul>
		<li>All numbers (including target) will be positive integers.</li>
		<li>Elements in a combination (a1, a2, … , ak) must be in non-descending order. (ie, a1 ≤ a2 ≤ … ≤ ak).</li>
		<li>The solution set must not contain duplicate combinations.</li>
		</ul>
	For example, given candidate set 2,3,6,7 and target 7, 
		A solution set is: 
		<pre>
		[7] 
		[2, 2, 3] 
		</pre>
	
	思路：
		这个题是一个NP问题，方法仍然是N-Queens中介绍的套路。基本思路是先排好序，然后每次递归中把剩下的元素一一加到结果集合中，并且把目标减去加入的元素，然后把剩下元素（包括当前加入的元素）
		放到下一层递归中解决子问题。算法复杂度因为是NP问题，所以自然是指数量级的。

 */
public class CombinationSum {
	
	public static void main(String[] args) {
		int[] candidates = {2, 3, 6, 7};
		int target = 7;
		List<ArrayList<Integer>> list = new CombinationSum().combinationSum(candidates, target);
		System.out.println(list);
	}

	public ArrayList<ArrayList<Integer>> combinationSum(int[] candidates, int target) {
		
		ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
		if (candidates == null || candidates.length == 0)
			return res;
		
		Arrays.sort(candidates);
		helper(candidates, 0, target, new ArrayList<Integer>(), res);
		return res;
	}

	/**
	 * 递归获取排序数组中的后续值
	 * @param candidates 候选数组（固定不变）
	 * @param start 开始检索的位置
	 * @param target 目标值
	 * @param item 当前的数值数组
	 * @param res 结果集
	 */
	private void helper(int[] candidates, int start, int target,
			ArrayList<Integer> item, ArrayList<ArrayList<Integer>> res) {
		if (target < 0)
			return;
		
		if (target == 0) {
			res.add(new ArrayList<Integer>(item));
			return;
		}
		
		for (int i = start; i < candidates.length; i++) {
			if (i > 0 && candidates[i] == candidates[i - 1])
				continue;
			item.add(candidates[i]);
			helper(candidates, i, target - candidates[i], item, res);
			item.remove(item.size() - 1);
		}
	}

}
