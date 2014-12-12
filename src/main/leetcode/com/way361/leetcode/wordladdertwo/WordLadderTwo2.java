package com.way361.leetcode.wordladdertwo;

import com.way361.structure.graph.AdjListGraph;

public class WordLadderTwo2 {
	public static void main(String[] args) {
		String[] words = {"hit", "hot","dot","dog","lot","log", "cog"};
		AdjListGraph<String> graph = initGraph(words);
//		graph.BFSTraverse(0);
//		graph.DFSTraverse(0);
		
		graph.shortestPath(0);
	}

	/**
	 * 构建图结构
	 * @param words 初始单词数组
	 * @return 初始图
	 */
	private static AdjListGraph<String> initGraph(String[] words) {
		AdjListGraph<String> graph = new AdjListGraph<String>(words.length);
		for(String word : words){
			graph.insertVertex(word);
		}
		for(int i = 0; i < words.length-1; i++){
			for(int j = words.length-1; j > i; j--){
				if(canConvert(words[i], words[j])){
					graph.insertEdge(i, j, 1);
				}
			}
		}
		return graph;
	}

	/**
	 * 比较两字符是否可以通过一个字母进行转换
	 * @param string 第一个单词
	 * @param string2 第二个单词
	 * @return 结果
	 */
	private static boolean canConvert(String string, String string2) {
		if(string.length() == string2.length()){
			if(string.length() <= 1) return true;
			else{
				int different=0,same=0;
				for(int i=0; i < string.length(); i++){
					if(string.charAt(i) == string2.charAt(i)){
						same++;
					}else{
						different++;
					}
				}
				if(different <= 1) return true;
			}
		}
		return false;
	}
	
}
