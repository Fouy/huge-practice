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
	 * �������л�ȡ���Ŀ�����ֵ����
	 * @param numbers ��������
	 * @param target Ŀ����ֵ
	 * @return ���
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
	 * �ݹ���Һ��ʵ���
	 * @param numbers ��������
	 * @param start ��ʼ��������
	 * @param target ����Ŀ����ֵ
	 * @param item Ŀǰ���ҵ�����
	 * @param result ���
	 */
	private static void helper(int[] numbers, int start, int target, ArrayList<Integer> item, ArrayList<ArrayList<Integer>> result) {
		
		if (target == 0) {//����Ŀ��Ϊ��ʱ�����ҽ���
			result.add(new ArrayList<Integer>(item));
			return;
		}
		if (target < 0 || start >= numbers.length)//����Ŀ��С��0��������������ʱ������
			return;
		
		for (int i = start; i < numbers.length; i++) {
			if (i > start && numbers[i] == numbers[i - 1])//��δ������˼
				continue;
			item.add(numbers[i]);
			helper(numbers, i + 1, target - numbers[i], item, result);
			item.remove(item.size() - 1);//���ݣ��Ƴ���һ����ӵ�����
		}
		
	}
	
}
