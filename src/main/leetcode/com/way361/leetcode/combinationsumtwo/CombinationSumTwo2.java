package com.way361.leetcode.combinationsumtwo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinationSumTwo2 {
	List<List<Integer>> result = new ArrayList<List<Integer>>();

	public static void main(String[] args) {
		int[] numbers = new int[] { 10, 1, 2, 7, 6, 1, 5, 5 };
		List<List<Integer>> result = new CombinationSumTwo2().combinationSum2(numbers, 8);
		System.out.println(result);
	}

	public List<List<Integer>> combinationSum2(int[] num, int target) {
		Arrays.sort(num);
		int length = num.length - 1;
		for (; length >= 0 && num[length] > target; length--)
			;
		if (length < 0) {
			return result;
		}
		int[] newNum = Arrays.copyOf(num, length + 1);
		// 以上只是做了一个简答的预处理，完全可以不写，对复杂度也无影响
		for (int i = 0; i < newNum.length; i++) {
			dfs(new ArrayList<Integer>(), newNum, i, target);
			for (int j = i + 1; j < newNum.length && newNum[j] == newNum[i]; j++, i++)
				;
		}
		return result;
	}

	private void dfs(List<Integer> list, int[] num, int index, int target) {
		if (index >= num.length) {
			return;
		}
		if (num[index] == target) {
			List<Integer> copy = new ArrayList<Integer>(list);
			copy.add(target);
			result.add(copy);
		}
		if (num[index] >= target) {
			return;
		}
		list.add(num[index]);
		for (int j = index + 1; j < num.length; j++) {
			dfs(list, num, j, target - num[index]);
			if (num[j] >= target - num[index]) {// dont search the same combination
				break;
			}
			for (int k = j + 1; k < num.length && num[k] == num[j]; k++, j++)
				;
		}
		list.remove(list.size() - 1);
	}
}
