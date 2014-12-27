package com.way361.leetcode.combinationsum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * LeetCode | Combination Sum
 * @author xuefeihu<br>
 *
 *	��Ŀ��<br>
		Given a set of candidate numbers (C) and a target number (T), find all unique combinations in C where the candidate numbers sums to T.
	The same repeated number may be chosen from C unlimited number of times.<br>

	Note:
		<ul>
		<li>All numbers (including target) will be positive integers.</li>
		<li>Elements in a combination (a1, a2, �� , ak) must be in non-descending order. (ie, a1 �� a2 �� �� �� ak).</li>
		<li>The solution set must not contain duplicate combinations.</li>
		</ul>
	For example, given candidate set 2,3,6,7 and target 7, 
		A solution set is: 
		<pre>
		[7] 
		[2, 2, 3] 
		</pre>
	
	˼·��
		�������һ��NP���⣬������Ȼ��N-Queens�н��ܵ���·������˼·�����ź���Ȼ��ÿ�εݹ��а�ʣ�µ�Ԫ��һһ�ӵ���������У����Ұ�Ŀ���ȥ�����Ԫ�أ�Ȼ���ʣ��Ԫ�أ�������ǰ�����Ԫ�أ�
		�ŵ���һ��ݹ��н�������⡣�㷨���Ӷ���Ϊ��NP���⣬������Ȼ��ָ�������ġ�

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
	 * �ݹ��ȡ���������еĺ���ֵ
	 * @param candidates ��ѡ���飨�̶����䣩
	 * @param start ��ʼ������λ��
	 * @param target Ŀ��ֵ
	 * @param item ��ǰ����ֵ����
	 * @param res �����
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
