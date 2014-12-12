package com.way361.leetcode.candy;
/**
 * LeetCode | Candy
 * @author xuefeihu
 * 
 * ��Ŀ��
 * 	  There are N children standing in a line. Each child is assigned a rating value.
	You are giving candies to these children subjected to the following requirements:
	Each child must have at least one candy.
	Children with a higher rating get more candies than their neighbors.
	What is the minimum candies you must give?
 *	��⣺
 *		step1:�ȸ�ÿ����1��candy
 *		step2:�������ұ���,������������ֵcandyֵ ��1
 *		step3:�����������,������������ֵcandy�� +1������Ϊ��������ĵ�����
 *		step4:ȡ���ζ�Ӧλ�õ�candy���ֵ
 */
public class Candy {

	public static void main(String[] args) {
		int[] ratings = new int[]{1, 2, 2, 2, 1};
		int count = candy(ratings);
		System.out.println(count);
	}

	/**
	 * �����ǹ�
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
		//������������������  +1
		for (int i = 1; i < ratings.length; i++) {
			if (ratings[i - 1] < ratings[i])
				res[i] = res[i - 1] + 1;
		}
		//������������������   +1  ��ȡ��һ�ν�������ߵ����ֵ
		for (int i = ratings.length - 2; i >= 0; i--) {
			if (ratings[i + 1] < ratings[i]) {
				tmp = res[i + 1] + 1;
				if (tmp > res[i])
					res[i] = tmp;
			}
		}
		//���
		int result = 0;
		for (int i = 0; i < res.length; i++) {
			result += res[i];
		}
		return result;
	}
}
