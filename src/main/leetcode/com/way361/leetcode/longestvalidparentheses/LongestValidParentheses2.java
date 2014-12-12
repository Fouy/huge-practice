package com.way361.leetcode.longestvalidparentheses;

import java.util.Stack;
/**
 * LeetCode | Longest Valid Parentheses
 * @author xuefeihu<br>
 *	方案2：stack，时空复杂度都是O(n)。依次遍历字符串中的字符。如果是'（'，压栈索引。如果是'）'，分情况讨论：<br>
		1、如果此时栈为空，表示前面没有需要匹配的或者前面已经计算完毕，'）'不可能作为新的计算开始点，所以更新start为'）'的索引+1.
		（start 表示当前能构成合法括号组合的起始处）<br>
		2、如果此时栈不空，则出栈，若此时栈为空，则表示当前匹配，更新结果为当前索引-start+1； 若此时不为空，则表示从此时栈顶元素下一
		位到当前索引是合法的，更新结果为当前索引-栈顶元素索引。
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