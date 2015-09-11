package com.way361.leetcode.candy;
/**
 * LeetCode | Candy
 * @author xuefeihu
 * 
 * 题目：
 * 	  There are N children standing in a line. Each child is assigned a rating value.
	You are giving candies to these children subjected to the following requirements:
	Each child must have at least one candy.
	Children with a higher rating get more candies than their neighbors.
	What is the minimum candies you must give?
 *	题解：
 *		step1:先给每个人1块candy
 *		step2:从左往右遍历,遇到递增的数值candy值 ＋1
 *		step3:从右往左遍历,遇到递增的数值candy至 +1（方向为从右往左的递增）
 *		step4:取两次对应位置的candy最大值
 */
public class Candy {

	public static void main(String[] args) {
		int[] ratings = new int[]{1, 2, 2, 2, 1};
		int count = candy(ratings);
		System.out.println(count);
	}

	/**
	 * 分配糖果
	 * @param ratings 
	 * @return
	 */
	public static int candy(int[] ratings) {
		if (null == ratings || ratings.length == 0)
			return 0;
		
		int[] res = new int[ratings.length];
		int tmp = 0;
		
		for (int i = 0; i < res.length; i++)
			res[i] = 1;
		//从左往右遇到递增的  +1
		for (int i = 1; i < ratings.length; i++) {
			if (ratings[i - 1] < ratings[i])
				res[i] = res[i - 1] + 1;
		}
		//从右往左遇到递增的   +1  并取上一次结果中两者的最大值
		for (int i = ratings.length - 2; i >= 0; i--) {
			if (ratings[i + 1] < ratings[i]) {
				tmp = res[i + 1] + 1;
				if (tmp > res[i])
					res[i] = tmp;
			}
		}
		//求和
		int result = 0;
		for (int i = 0; i < res.length; i++) {
			result += res[i];
		}
		return result;
	}
}
