package com.way361.leetcode.dividetwointegers;

/**
 * Divide Two Integers -- LeetCode
 * @author xuefeihu
 * ��Ŀ��
		Divide two integers without using multiplication, division and mod operator.
       ˼·��
       	�����������ֵ�������Ŀ������������������⣬��Reverse Integer�������ᵽ�����Ƚ���Ҫ��ע�������
       ���źʹ���Խ������⡣ ���������Ŀ����Ϊ�����ó˳�����ȡ�����㣬����ֻ��ʹ��λ����ͼӼ������Ƚ�ֱ�ӵķ�����
       �ñ�����һֱ��ȥ������ֱ��Ϊ0�����ַ����ĵ��������ǽ���Ĵ�С����������Ϊn���㷨���Ӷ���O(n)��
       	��ô��û�а취�Ż��أ� ������Ǿ͵�ʹ��λ���㡣����֪���κ�һ���������Ա�ʾ����2����Ϊ�׵�һ���������
       ��ϣ���num=a_0*2^0+a_1*2^1+a_2*2^2+...+a_n*2^n���������������ʽ�Լ�����һλ�൱�ڳ���2��������
       �ó�������ֱ�����ڱ�����֮ǰ�õ�һ�����Ļ���Ȼ�����������ÿ�γ��Լ�ȥ���������������������Ӽ�2^k,Ȼ��
       ���������Ƶ�����ֱ����Ϊ0Ϊֹ����Ϊ��������ĵ��������ǰ�2����ֱ���������������ʱ�临�Ӷ�ΪO(logn)��	
	
 */
public class DivideTwoIntegers {

	public static void main(String[] args) {
		System.out.println(divide(541, 19));
	}

	/**
	 * ��������
	 * @param dividend ������
	 * @param divisor ����
	 * @return
	 */
	public static int divide(int dividend, int divisor) {
		if (divisor == 0) {
			return Integer.MAX_VALUE;
		}
		boolean isNeg = (dividend ^ divisor) >>> 31 == 1;	//�жϷ���λ��>>>����ʱ��߲���0��>>����ʱ��߲��Ϸ���λ
		
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
		int digit = 0;//���Ƶ�λ��
		while (divisor < dividend) {//������������Ƶ�����С�ڱ���������
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
