package com.way361.leetcode.wordladdertwo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class WordLadderTwo {
	
	public static void main(String[] args) {
		HashSet<String> dict = new HashSet<String>();
		dict.add("hot");
		dict.add("dot");
		dict.add("dog");
		dict.add("lot");
		dict.add("log");
		ArrayList<ArrayList<String>> result = new WordLadderTwo().findLadders1("hit", "cog", dict);
		System.out.println(result);
	}

	public ArrayList<ArrayList<String>> findLadders1(String start, String end, HashSet<String> dict) {
		ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
		ArrayList<String> path = new ArrayList<String>();
		HashSet<String> hash = new HashSet<String>();
		if (start == null || end == null || start.length() != end.length()) {
			return result;
		}
		Queue queue = new LinkedList<Pair>();
		path.add(start);
		hash.add(start);
		Pair node = new Pair(start, path, hash);
		int min_length = -1;
		queue.add(node);
		while (!queue.isEmpty()) {
			node = (Pair) queue.poll();
			String str = node.str;
			for (int i = 0; i < str.length(); i++) {
				char[] strCharArr = str.toCharArray();
				for (char ch = 'a'; ch <= 'z'; ch++) {
					if (strCharArr[i] == ch) {
						continue;
					}
					strCharArr[i] = ch;
					String newWord = new String(strCharArr);
					if (newWord.equals(end) == true) {//如果等于end
						path = node.path;
						path.add(newWord);
						if (min_length == -1) {
							min_length = path.size();
						}
						if (path.size() > min_length) {
							return result;
						} else {
							result.add(path);
							// dict.remove(newWord);
							continue;
						}
					} else {
						if (dict.contains(newWord) && !node.hash.contains(newWord)) {//添加未遍历的节点
							path = new ArrayList<String>(node.path);
							hash = new HashSet<String>(node.hash);
							path.add(newWord);
							hash.add(newWord);
							Pair newnode = new Pair(newWord, path, hash);
							queue.add(newnode);
							System.out.println("===========" + newWord);
							// dict.remove(newWord);
						}
					}
				}
			}
		}
		return result;
	}
	
	
	public ArrayList<ArrayList<String>> findLadders2(String start, String end, HashSet<String> dict) {
		dict.add(end);

		// Key: the dictionary string; Value: HashSet<ArrayList<String>>
		Map<String, HashSet<ArrayList<String>>> map = new HashMap<String, HashSet<ArrayList<String>>>();
		Queue<String> queue = new LinkedList<String>();

		ArrayList<String> startPath = new ArrayList<String>();
		startPath.add(start);
		HashSet<ArrayList<String>> startSet = new HashSet<ArrayList<String>>();
		startSet.add(startPath);
		queue.offer(start);
		map.put(start, startSet);

		ArrayList<ArrayList<String>> ret = new ArrayList<ArrayList<String>>();

		while (!queue.isEmpty()) {
			String str = queue.poll();

			if (str.equals(end)) {
				ret.addAll(map.get(end));
				return ret;
			}

			for (int i = 0; i < str.length(); i++) {
				for (int j = 0; j <= 25; j++) {
					// transform it into another word
					String newStr = replace(str, i, (char) ('a' + j));

					// if a new word is explored
					if (dict.contains(newStr)) {
						if (!map.containsKey(newStr)) {
							// construct a new path set
							HashSet<ArrayList<String>> prevSet = map.get(str);
							HashSet<ArrayList<String>> newSet = new HashSet<ArrayList<String>>();
							for (ArrayList<String> path : prevSet) {
								ArrayList<String> newPath = new ArrayList<String>(path);
								newPath.add(newStr);
								newSet.add(newPath);
							}

							map.put(newStr, newSet);
							queue.offer(newStr);
						} else {
							HashSet<ArrayList<String>> prevSet = map.get(str);
							HashSet<ArrayList<String>> newSet = map.get(newStr);

							Iterator<ArrayList<String>> prevIt = prevSet
									.iterator();
							Iterator<ArrayList<String>> newIt = newSet
									.iterator();

							// increase the path set
							if (prevIt.next().size() + 1 == newIt.next().size()) {
								for (ArrayList<String> path : prevSet) {
									ArrayList<String> newPath = new ArrayList<String>(path);
									newPath.add(newStr);
									newSet.add(newPath);
									// queue.offer(newStr); // will cause TLE!!!
								}
							}
						}
					}
				}
			}
		}

		return ret; // return an empty set
	}

	// replace the index of the given string with the given char
	private String replace(String str, int index, char c) {
		StringBuilder sb = new StringBuilder(str);
		sb.setCharAt(index, c);
		return sb.toString();
	}
	
}

class Pair {
	/** 记录当前遍历到的节点字符 */
	String str;
	/** 遍历到当前节点的路径 */
	ArrayList<String> path;
	/** 存放已遍历的节点 */
	HashSet<String> hash;
	
	Pair(String str, ArrayList<String> path, HashSet<String> hash) {
		this.str = str;
		this.path = path;
		this.hash = hash;
	}
}