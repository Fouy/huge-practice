package com.way361.leetcode.substringwithconcatenationofallwords;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SubstringwithConcatenationofAllWords {
	
	public static void main(String[] args) {
		String S = "barfoothefoobarman";
		String[] L = {"foo", "bar"};
		List<Integer> list = findSubstring(S, L);
		System.out.println(list);
	}
	
	public static ArrayList<Integer> findSubstring(String str, String[] strArray) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		int len = strArray.length;
		if (len == 0)  return list;
		
		//记录数组中每个单词的个数
		int wordLen = strArray[0].length();
		Map<String, Integer> wordsMap = new HashMap<String, Integer>();
		for (int i = 0; i < len; i++) {
			int num = 1;
			if (wordsMap.get(strArray[i]) != null) {
				num += wordsMap.get(strArray[i]);
			}
			wordsMap.put(strArray[i], num);
		}
		
		int slen = str.length();
		int max = slen - len * wordLen + 1;
		for (int i = 0; i < max; i++) {
			Map<String, Integer> numMap = new HashMap<String, Integer>();
			int j = 0;
			for (; j < len; j++) {
				int start = i + j * wordLen;
				int end = start + wordLen;
				String tempStr = str.substring(start, end);
				if (!wordsMap.containsKey(tempStr)) {
					break;
				}
				int num = 1;
				if (numMap.get(tempStr) != null) {
					num += numMap.get(tempStr);
				}
				if (num > wordsMap.get(tempStr)) {
					break;
				}
				numMap.put(tempStr, num);
			}
			if (j == len) {
				list.add(i);
			}
		}
		return list;
	}
}