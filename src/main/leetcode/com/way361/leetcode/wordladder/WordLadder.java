package com.way361.leetcode.wordladder;

import java.util.HashSet;
import java.util.LinkedList;

public class WordLadder {

	public static void main(String[] args) {
		HashSet<String> dict = new HashSet<String>();
		dict.add("hot");
		dict.add("dot");
		dict.add("dog");
		dict.add("lot");
		dict.add("log");
		int result = new WordLadder().ladderLength("hit", "cog", dict);
		System.out.println(result);
	}

	/**
	 * ¼ÆËã×ÖÌÝ³¤¶È
	 * @param start
	 * @param end
	 * @param dict
	 * @return
	 */
	public int ladderLength(String start, String end, HashSet<String> dict) {
		if (start == null || end == null || start.length() == 0 || end.length() == 0 || start.length() != end.length())
			return 0;
		
		LinkedList<String> queue = new LinkedList<String>();
		HashSet<String> visited = new HashSet<String>();
		int level = 1;
		int lastNum = 1;
		int curNum = 0;
		queue.offer(start);
		visited.add(start);
		while (!queue.isEmpty()) {
			String cur = queue.poll();
			lastNum--;
			for (int i = 0; i < cur.length(); i++) {
				char[] charCur = cur.toCharArray();
				for (char c = 'a'; c <= 'z'; c++) {
					charCur[i] = c;
					String temp = new String(charCur);
					if (temp.equals(end))
						return level + 1;
					if (dict.contains(temp) && !visited.contains(temp)) {
						curNum++;
						queue.offer(temp);
						visited.add(temp);
					}
				}
			}
			if (lastNum == 0) {
				lastNum = curNum;
				curNum = 0;
				level++;
			}
		}
		return 0;
	}

}
