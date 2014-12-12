package com.way361.leetcode.longestvalidparentheses;

import java.util.Stack;
/**
 * LeetCode | Longest Valid Parentheses
 * @author xuefeihu<br>
 *	����2��stack��ʱ�ո��Ӷȶ���O(n)�����α����ַ����е��ַ��������'��'��ѹջ�����������'��'����������ۣ�<br>
		1�������ʱջΪ�գ���ʾǰ��û����Ҫƥ��Ļ���ǰ���Ѿ�������ϣ�'��'��������Ϊ�µļ��㿪ʼ�㣬���Ը���startΪ'��'������+1.
		��start ��ʾ��ǰ�ܹ��ɺϷ�������ϵ���ʼ����<br>
		2�������ʱջ���գ����ջ������ʱջΪ�գ����ʾ��ǰƥ�䣬���½��Ϊ��ǰ����-start+1�� ����ʱ��Ϊ�գ����ʾ�Ӵ�ʱջ��Ԫ����һ
		λ����ǰ�����ǺϷ��ģ����½��Ϊ��ǰ����-ջ��Ԫ��������
 *
 */
public class LongestValidParentheses2 {

	public static void main(String[] args) {
		int result = new LongestValidParentheses2().longestValidParentheses("()()()))(()))(");
		System.out.println(result);
	}

	public int longestValidParentheses(String s) {
		if (s == null || s.length() < 2)
			return 0;
		
		Stack<Integer> stack = new Stack<Integer>();
		int start = 0;
		int max = 0;
		for (int i = 0; i < s.length(); i++) {
			char ch = s.charAt(i);
			if (ch == '(')
				stack.push(i);
			else {
				if (stack.isEmpty())
					start = i + 1;
				else {
					stack.pop();
					if (stack.isEmpty())
						max = Math.max(max, i - start + 1);
					else
						max = Math.max(max, i - stack.peek());
				}
			}
		}

		return max;
	}

}