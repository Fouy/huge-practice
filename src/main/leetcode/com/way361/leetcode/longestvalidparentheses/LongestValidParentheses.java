package com.way361.leetcode.longestvalidparentheses;
/**
 * LeetCode | Longest Valid Parentheses
 * @author xuefeihu<br>
 * 题目：<br>
		Given a string containing just the characters '(' and ')', find the length of the longest 
	valid (well-formed) parentheses substring.<br>
 		For "(()", the longest valid parentheses substring is "()", which has length = 2.<br>
		Another example is ")()())", where the longest valid parentheses substring is "()()", which has length = 4.<br>
		
	方案1：DP，dp[i]代表从索引i到末尾最长的有效括号组合的长度。<br>
		dp[s.length()-1]=0;从后向前逆向求解dp[i]，<br>
		1、如果s[i]=')'，则显然dp[i]=0;<br>
		2、如果s[i]='('，跳过dp[i+1]这段长度从j=i+1+dp[i+1]开始，如果j没越界并且s[j]='0'，正好和s[i]匹配，则dp[i]=dp[i+1]+2；<br>
		另外此时可能j之后的也可以连上，所以，可能要加上dp[j+1];<br>
 */
public class LongestValidParentheses {
	
	public static void main(String[] args) {
		int length = new LongestValidParentheses().longestValidParentheses("(((()(()");
		System.out.println(length);
	}
	
	public int longestValidParentheses(String s) {
		if (s == null || s.length() < 2)
			return 0;
		
		int max = 0;
		int[] dp = new int[s.length()];
		dp[s.length() - 1] = 0;
		for (int i = s.length() - 2; i >= 0; i--) {
			char ch = s.charAt(i);
			if (ch == '(') {
				int j = i + 1 + dp[i + 1];
				if (j < s.length() && s.charAt(j) == ')') {
					dp[i] = dp[i + 1] + 2;
					if (j + 1 < s.length())
						dp[i] += dp[j + 1];
				}
			}
			max = Math.max(max, dp[i]);
		}
		return max;
	}

}