package com.way361.leetcode.substringwithconcatenationofallwords;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SubstringwithConcatenationofAllWords3 {

	public static void main(String[] args) {
		String string = "barfoothefoobarman";
		String[] strArray = { "foo", "bar" };
		List<Integer> list = findSubstring(string, strArray);
		System.out.println(list);
	} 

	public static ArrayList<Integer> findSubstring(String string, String[] strArray) {
		// Note: The Solution object is instantiated only once and is reused by each test case.
		ArrayList<Integer> result = new ArrayList<Integer>();
		if (string == null || string.length() == 0 || strArray == null || strArray.length == 0)
			return result;
		
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		for (int i = 0; i < strArray.length; i++) {
			if (map.containsKey(strArray[i])) {
				map.put(strArray[i], map.get(strArray[i]) + 1);
			} else {
				map.put(strArray[i], 1);
			}
		}
		
		for (int i = 0; i < strArray[0].length(); i++) {
			HashMap<String, Integer> curMap = new HashMap<String, Integer>();
			int count = 0;
			int left = i;
			for (int j = i; j <= string.length() - strArray[0].length(); j += strArray[0].length()) {
				String str = string.substring(j, j + strArray[0].length());
				if (map.containsKey(str)) {
					if (curMap.containsKey(str))
						curMap.put(str, curMap.get(str) + 1);
					else
						curMap.put(str, 1);
					if (curMap.get(str) <= map.get(str))
						count++;
					else {
						while (curMap.get(str) > map.get(str)) {
							String temp = string.substring(left, left + strArray[0].length());
							if (curMap.containsKey(temp)) {
								curMap.put(temp, curMap.get(temp) - 1);
								if (curMap.get(temp) < map.get(temp))
									count--;
							}
							left += strArray[0].length();
						}
					}
					if (count == strArray.length) {
						result.add(left);
						String temp = string.substring(left, left + strArray[0].length());
						if (curMap.containsKey(temp))
							curMap.put(temp, curMap.get(temp) - 1);
						count--;
						left += strArray[0].length();
					}
				} else {
					curMap.clear();
					count = 0;
					left = j + strArray[0].length();
				}
			}
		}
		return result;
	}

}