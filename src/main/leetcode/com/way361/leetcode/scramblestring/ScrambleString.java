package com.way361.leetcode.scramblestring;

import java.util.Arrays;
/**
 * LeetCode| Scramble String
 * @author xuefeihu <br>
 * ˼·��
 *		����⿴�����ǱȽϸ��ӵģ������brute force��ÿ�����иȻ��ݹ���⣬��һ���Ƕ���ʽ�ĸ��Ӷȣ�һ����˵�ⲻ�����Թ���Ҫ�Ĵ𰸡�<br>
		����ʵ��һ����ά��̬�滮����Ŀ���������ά����res[i][j][n]������i��s1����ʼ�ַ���j��s2����ʼ�ַ�����n�ǵ�ǰ���ַ������ȣ�
	res[i][j][len]��ʾ������i��j�ֱ�Ϊs1��s2���ĳ���Ϊlen���ַ����ǲ��ǻ�Ϊscramble��<br>
		����ά�������ǽ�������������ʽ��Ҳ������ô������ʷ��Ϣ���õ�res[i][j][len]���ж�����ǲ������㣬��ʵ���������ǰѵ�ǰ
	s1[i...i+len-1]�ַ�����һ���ֳ������֣�Ȼ��������������һ������ߺ�s2[j...j+len-1]��߲����ǲ���scramble���Լ��ұߺ�
	s2[j...j+len-1]�ұ߲����ǲ���scramble���ڶ����������ߺ�s2[j...j+len-1]�ұ߲����ǲ���scramble���Լ��ұߺ�s2[j...j+len-1]
	��߲����ǲ���scramble������������������һ�ֳ�����˵��s1[i...i+len-1]��s2[j...j+len-1]��scramble�ġ��������ж���Щ����
	�����ǲ���scramble����������ʷ��Ϣ�ģ���Ϊ����С��n������������Ƕ���ǰ�������ˣ�Ҳ���ǳ����������ѭ������<br>
		����˵������һ�������������s1[i...i+len-1]������len-1������������Щ������ֻҪ��һ�ֳ�������ô����������scramble�ġ�<br>
		�ܽ���������ʽ��res[i][j][len] = || (res[i][j][k]&&res[i+k][j+k][len-k] || res[i][j+len-k][k]&&res[i+k][j][len-k]) 
	��������1<=k<len��Ҳ���Ƕ�������len-1�������Ľ��������㡣��Ϊ��Ϣ���Ǽ�����ģ�����ÿ������ֻ��Ҫ��������������ɣ����������ʽ��
	��ҪO(len)����Ϊlen-1���������������ʱ�临�Ӷ���Ϊ����ά��̬�滮����Ҫ����ѭ��������ÿһ����Ҫ����ʱ��������ʽ��������O(n^4)����Ȼ
	�Ѿ��Ƚϸ��ˣ��������ٲ���ָ�������ģ���̬�滮�����кܴ����µģ��ռ临�Ӷ���O(n^3)��
 *
 */
public class ScrambleString {

	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		boolean result = isScramble2("stringbuffer", "tsringbuffer");
		long end = System.currentTimeMillis();
		System.out.println("the result is ��" + result + "��, and cost time " + (end-start));
	}

	/**
	 * �ж����ַ����Ƿ�Ϊscramble��DP����̬�滮��������ʱ�临�Ӷ�(O)n^3��
	 * @param s1
	 * @param s2
	 * @return
	 */
	public static boolean isScramble1(String s1, String s2) {
		
		if (s1 == null || s2 == null || s1.length() != s2.length())
			return false;
		if (s1.length() == 0)
			return true;
		
		boolean[][][] res = new boolean[s1.length()][s2.length()][s1.length() + 1];
		for (int i = 0; i < s1.length(); i++) {
			for (int j = 0; j < s2.length(); j++) {
				res[i][j][1] = s1.charAt(i) == s2.charAt(j);
			}
		}
		
		for (int len = 2; len <= s1.length(); len++) {
			for (int i = 0; i < s1.length() - len + 1; i++) {
				for (int j = 0; j < s2.length() - len + 1; j++) {
					for (int k = 1; k < len; k++) {
						res[i][j][len] |= res[i][j][k] && res[i + k][j + k][len - k]
								|| res[i][j + len - k][k] && res[i + k][j][len - k];
					}
				}
			}
		}
		return res[0][0][s1.length()];
	}
	
	/**
	 * �ж����ַ����Ƿ�Ϊscramble���ݹ鷽�����󼯺����г�ʱ��ʱ�临�Ӷ�(O)3^n��
	 * @param s1
	 * @param s2
	 * @return
	 */
	public static boolean isScramble2(String s1, String s2) {

		if (s1 == null || s2 == null || s1.length() != s2.length())
			return false;
		
		char[] c1 = s1.toCharArray(), c2 = s2.toCharArray();
		Arrays.sort(c1);
		Arrays.sort(c2);
		if (!(new String(c1)).equals(new String(c2)))
			return false;
		else if (s1.length() == 1)
			return true;

		for (int i = 0; i < s1.length() - 1; i++) {
			if (isScramble2(s1.substring(0, i + 1), s2.substring(s1.length() - i - 1))
					&& isScramble2(s1.substring(i + 1), s2.substring(0, s1.length() - i - 1))
					|| isScramble2(s1.substring(0, i + 1), s2.substring(0, i + 1))
					&& isScramble2(s1.substring(i + 1), s2.substring(i + 1)))
				return true;
		}
		return false;
	}

}
