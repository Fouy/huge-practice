package com.way361.leetcode.substringwithconcatenationofallwords;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SubstringwithConcatenationofAllWords2 {

	public static void main(String[] args) {
		String S = "barfoothefoobarman";
		String[] L = { "foo", "bar" };
		List<Integer> list = findSubstring(S, L);
		System.out.println(list);
	}

	public static ArrayList<Integer> findSubstring(String str, String[] strArray) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		int arrayLength = strArray.length;
		if (arrayLength == 0) return list;
		
		//统计strArray数组中单词的个数
		int wordLength = strArray[0].length();
		Map<String, Integer> wordsMap = new HashMap<String, Integer>();
		for (int i = 0; i < arrayLength; i++) {
			int num = 1;
			if (wordsMap.get(strArray[i]) != null) {
				num += wordsMap.get(strArray[i]);
			}
			wordsMap.put(strArray[i], num);
		}
		
		int strLength = str.length();
		int max = strLength - wordLength + 1;
		for (int i = 0; i < wordLength; i++) {//将按滑动窗口分割好的组进行循环
			Map<String, Integer> numMap = new HashMap<String, Integer>();
			int count = 0;
			int start = i;
			for (int end = start; end < max; end += wordLength) {//遍历单组中的匹配串（回溯算法）
				String tempStr = str.substring(end, end + wordLength);
				if (!wordsMap.containsKey(tempStr)) {//回溯后若下一个串不匹配则跳过当前串
					numMap.clear();
					count = 0;
					start = end + wordLength;
					continue;
				}
				int num = 1;
				if (numMap.containsKey(tempStr)) {//若组内MAP包含该词，则修改组内计数
					num += numMap.get(tempStr);
				}
				numMap.put(tempStr, num);
				if (num <= wordsMap.get(tempStr)) {
					count++;
				} else {//如果该组中的计数超过单词数组中的计数
					while (numMap.get(tempStr) > wordsMap.get(tempStr)) {
						tempStr = str.substring(start, start + wordLength);
						numMap.put(tempStr, numMap.get(tempStr) - 1);
						if (numMap.get(tempStr) < wordsMap.get(tempStr)) {
							count--;
						}
						start += wordLength;
					}
				}
				if (count == arrayLength) {
					list.add(start);
					tempStr = str.substring(start, start + wordLength);
					numMap.put(tempStr, numMap.get(tempStr) - 1);
					count--;
					start += wordLength;
				}
			}
		}
		return list;
	}
	
}