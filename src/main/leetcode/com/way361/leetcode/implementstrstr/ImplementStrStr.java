package com.way361.leetcode.implementstrstr;

/**
 * LeetCode | Implement strStr()
 * @author xuefeihu
 * 题目：
	Implement strStr().<br>
	Returns a pointer to the first occurrence of needle in haystack, or null if needle is not part of haystack.<br>
        思路：串的模式匹配
 */
public class ImplementStrStr {

	public static void main(String[] args) {
		System.out.println(indexOfByBF("areyouok,fuckyoumanfuckyouguys", "guy", 0));
		System.out.println(indexOfByKMP("areyouok,fuckyoumanfuckyouguys", "guy", 0));
	}

	/**
	 * KMP算法
	 * @param target
	 * @param pattern
	 * @param start
	 * @return
	 */
	public static int indexOfByKMP(String target, String pattern, int start) {
		if (null != target && null != pattern
				&& target.length() > pattern.length()) {
			int i = start, j = 0;
			int[] next = getNext(pattern);
			while (i < target.length()) {
				if (j == -1 || target.charAt(i) == pattern.charAt(j)) {
					i++;
					j++;
				} else {
					j = next[j];
				}
				if (j == pattern.length())
					return i - j;
			}
		}
		return -1;
	}

	/**
	 * 获取模式串的next数组
	 * @param pattern
	 * @return
	 */
	private static int[] getNext(String pattern) {
		int j = 0, k = -1;
		int[] next = new int[pattern.length()];
		next[0] = -1;
		while (j < pattern.length() - 1) {
			if (k == -1 || pattern.charAt(j) == pattern.charAt(k)) {
				j++;
				k++;
				next[j] = k;
			} else {
				k = next[k];
			}
		}
		return next;
	}

	/**
	 * Brute-Force算法
	 * @param target 目标串
	 * @param pattern 匹配串
	 * @param start 开始索引
	 * @return
	 */
	public static int indexOfByBF(String target, String pattern, int start){
		if(null != pattern && pattern.length() > 0 && target.length() > pattern.length()){
			int i = start, j = 0;
			while(i < target.length()){
				if(target.charAt(i) == pattern.charAt(j)){
					i++; j++;
				} else {
					i = i - j + 1;
					j = 0;
				}
				if(j == pattern.length())
					return i - j;
			}
		}
		return -1;
	}
	

}
