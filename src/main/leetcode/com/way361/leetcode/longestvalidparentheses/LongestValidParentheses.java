package com.way361.leetcode.longestvalidparentheses;
/**
 * LeetCode | Longest Valid Parentheses
 * @author xuefeihu<br>
 * ��Ŀ��<br>
		Given a string containing just the characters '(' and ')', find the length of the longest 
	valid (well-formed) parentheses substring.<br>
 		For "(()", the longest valid parentheses substring is "()", which has length = 2.<br>
		Another example is ")()())", where the longest valid parentheses substring is "()()", which has length = 4.<br>
		
	����1��DP��dp[i]���������i��ĩβ�����Ч������ϵĳ��ȡ�<br>
		dp[s.length()-1]=0;�Ӻ���ǰ�������dp[i]��<br>
		1�����s[i]=')'������Ȼdp[i]=0;<br>
		2�����s[i]='('������dp[i+1]��γ��ȴ�j=i+1+dp[i+1]��ʼ�����jûԽ�粢��s[j]='0'�����ú�s[i]ƥ�䣬��dp[i]=dp[i+1]+2��<br>
		�����ʱ����j֮���Ҳ�������ϣ����ԣ�����Ҫ����dp[j+1];<br>
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