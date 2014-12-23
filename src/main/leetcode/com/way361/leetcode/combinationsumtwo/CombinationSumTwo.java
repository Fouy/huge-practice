package com.way361.leetcode.combinationsumtwo;

import java.util.ArrayList;
import java.util.Arrays;

public class CombinationSumTwo {

	public static void main(String[] args) {
		
		int[] numbers = new int[] { 10, 1, 2, 7, 6, 1, 5, 5 };
		ArrayList<ArrayList<Integer>> result = combinationSum2(numbers, 8);
		System.out.println(result);
		
	}

	/**
	 * 从数组中获取组成目标的数值集合
	 * @param numbers 集合数组
	 * @param target 目标数值
	 * @return 结果
	 */
	public static ArrayList<ArrayList<Integer>> combinationSum2(int[] numbers, int target) {
		
		ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
		if (numbers == null || numbers.length == 0)
			return result;
		
		Arrays.sort(numbers);
		helper(numbers, 0, target, new ArrayList<Integer>(), result);
		return result;
		
	}

	/**
	 * 递归查找合适的数
	 * @param numbers 集合数组
	 * @param start 开始查找索引
	 * @param target 查找目标数值
	 * @param item 目前已找到的项
	 * @param result 结果
	 */
	private static void helper(int[] numbers, int start, int target, ArrayList<Integer> item, ArrayList<ArrayList<Integer>> result) {
		
		if (target == 0) {//查找目标为零时，查找结束
			result.add(new ArrayList<Integer>(item));
			return;
		}
		if (target < 0 || start >= numbers.length)//查找目标小于0，查找索引出界时，回溯
			return;
		
		for (int i = start; i < numbers.length; i++) {
			if (i > start && numbers[i] == numbers[i - 1])//还未明白意思
				continue;
			item.add(numbers[i]);
			helper(numbers, i + 1, target - numbers[i], item, result);
			item.remove(item.size() - 1);//回溯，移除上一次添加的数据
		}
		
	}
	
}
