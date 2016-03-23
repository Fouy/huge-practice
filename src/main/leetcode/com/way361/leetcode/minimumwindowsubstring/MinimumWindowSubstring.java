package com.way361.leetcode.minimumwindowsubstring;

import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode | Minimum Window Substring
 *  <pre>
 *	Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).
 *	For example,
 *	S = "ADOBECODEBANC"
 *	T = "ABC"
 *	Minimum window is "BANC".
 *	Note:
 *	If there is no such window in S that covers all characters in T, return the emtpy string "".
 *	If there are multiple such windows, you are guaranteed that there will always be only one unique minimum window in S.
 * </pre>
 * <pre>
 * 思路：
 * 最基本的思路是设置一个首指针与一个尾指针，当未包含所有字符时，尾指针移动；当包含重复字符时，首指针移动。每次恰好包含所以字符时，
 * 记录是否为最小长度。这个方法的问题是尾指针经过的无效点，首指针可能会重复经过。如下例，首指针不动时，尾指针移动直到包含a...b，当
 * 尾指针移动到a...b...a时首指针开始移动，这时首指针需要重复移动尾指针经过的位置。
 * 优化的思路是针对每一个字符建立一个队列来记录字符出现的位置，当队列中的字符数大于T中需要的字符数时，队列中的首个字符便可以出列。
 * 备注：ASCII码中有效的字符从'!'开始，共95个。
 * </pre>
 *  @author xuefeihu
 *
 */
public class MinimumWindowSubstring {
	
	public static void main(String[] args) {
		String S = "ADOBECODEBANCABC";
		String T = "ABC";
		System.out.println(minWindow(S, T));
	}

	/**
	 * 获取最小窗口
	 * @param S 目标串
	 * @param T 窗口
	 * @return
	 */
	public static String minWindow(String S, String T) {
		String result = null;
		List<BarrelNode> list = prepareLists(T);
		for(int i = 0; i < S.length(); i++){
			char ch = S.charAt(i);
			int index = checkAndgetIndex(list, ch);
			if(index >= 0){
				list.get(index).index = i;
			}
			if(checkFull(list)) {
				String temp = getMinWindow(list, S);
				if(null != result) {
					result = result.length() > temp.length() ? temp : result;
				}else {
					result = temp;
				}
			}
		}
		return result;
	}

	/**
	 * 获取最小窗口（当桶满时）
	 * @param list
	 * @param s
	 */
	private static String getMinWindow(List<BarrelNode> list, String s) {
		int end = Integer.MIN_VALUE, start = Integer.MAX_VALUE;
		for(BarrelNode entity : list) {
			start = start > entity.index ? entity.index : start;
			end = end < entity.index ? entity.index : end;
		}
		return s.substring(start, end+1);
	}

	/**
	 * 检查桶是否装满
	 * @param list
	 * @return
	 */
	private static boolean checkFull(List<BarrelNode> list) {
		for(BarrelNode entity : list) {
			if(entity.index  < 0)
				return false;
		}
		return true;
	}

	/**
	 * 查找并获取索引
	 * @param lists
	 * @param ch
	 * @return 查不到时返回-2
	 */
	private static int checkAndgetIndex(List<BarrelNode> lists, char ch) {
		for(int i = 0; i < lists.size(); i++) {
			if(lists.get(i).ch.equals(ch)) {
				return i;
			}
		}
		return -2;
	}

	/**
	 * 生成T.length个桶
	 * 
	 * @param T
	 * @return
	 */
	private static List<BarrelNode> prepareLists(String T) {
		int length = T.length();
		List<BarrelNode> result = new ArrayList<BarrelNode>();
		for (int i = 0; i < length; i++) {
			BarrelNode barrel = new BarrelNode(T.charAt(i), -1);
			result.add(barrel);
		}
		return result;
	}

}

/**
 * 桶节点
 * 
 * @author xuefeihu
 *
 */
class BarrelNode {

	/** 当前字符 */
	Character ch;

	/** 字符索引 */
	int index;

	public BarrelNode(Character ch, int index) {
		this.ch = ch;
		this.index = index;
	}

}
