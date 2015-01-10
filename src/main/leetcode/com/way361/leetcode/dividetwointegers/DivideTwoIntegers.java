package com.way361.leetcode.dividetwointegers;

/**
 * Divide Two Integers -- LeetCode
 * @author xuefeihu
 * 题目：
		Divide two integers without using multiplication, division and mod operator.
       思路：
       	这道题属于数值处理的题目，对于整数处理的问题，在Reverse Integer中我有提到过，比较重要的注意点在于
       符号和处理越界的问题。 对于这道题目，因为不能用乘除法和取余运算，我们只能使用位运算和加减法。比较直接的方法是
       用被除数一直减去除数，直到为0。这种方法的迭代次数是结果的大小，即比如结果为n，算法复杂度是O(n)。
       	那么有没有办法优化呢？ 这个我们就得使用位运算。我们知道任何一个整数可以表示成以2的幂为底的一组基的线性
       组合，即num=a_0*2^0+a_1*2^1+a_2*2^2+...+a_n*2^n。基于以上这个公式以及左移一位相当于乘以2，我们先
       让除数左移直到大于被除数之前得到一个最大的基。然后接下来我们每次尝试减去这个基，如果可以则结果增加加2^k,然后
       基继续右移迭代，直到基为0为止。因为这个方法的迭代次数是按2的幂直到超过结果，所以时间复杂度为O(logn)。	
	
 */
public class DivideTwoIntegers {

	public static void main(String[] args) {
		System.out.println(divide(541, 19));
	}

	/**
	 * 除法运算
	 * @param dividend 被除数
	 * @param divisor 除数
	 * @return
	 */
	public static int divide(int dividend, int divisor) {
		if (divisor == 0) {
			return Integer.MAX_VALUE;
		}
		boolean isNeg = (dividend ^ divisor) >>> 31 == 1;	//判断符号位，>>>右移时左边补上0，>>右移时左边补上符号位
		
		int res = 0;
		if (dividend == Integer.MIN_VALUE) {
			dividend += Math.abs(divisor);
			if (divisor == -1) {
				return Integer.MAX_VALUE;
			}
			res++;
		}
		if (divisor == Integer.MIN_VALUE) {
			return res;
		}
		
		dividend = Math.abs(dividend);
		divisor = Math.abs(divisor);
		int digit = 0;//左移的位数
		while (divisor < dividend) {//计算出除数左移到最大的小于被除数的数
			divisor <<= 1;
			digit++;
		}
		
		while (digit >= 0) {
			if (dividend >= divisor) {
				res += (1 << digit);
				dividend -= divisor;
			}
			divisor >>= 1;
			digit--;
		}
		return isNeg ? -res : res;
	}

}
