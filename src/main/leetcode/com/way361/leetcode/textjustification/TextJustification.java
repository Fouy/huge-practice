package com.way361.leetcode.textjustification;

import java.util.ArrayList;
/**
 * LeetCode | Text Justification
 * @author xuefeihu<br>
 * 题目：<br>
		Given an array of words and a length L, format the text such that each line has exactly L characters 
	and is fully (left and right) justified.<br>
		You should pack your words in a greedy approach; that is, pack as many words as you can in each line. 
	Pad extra spaces ' ' when necessary so that each line has exactly Lcharacters.<br>
		Extra spaces between words should be distributed as evenly as possible. If the number of spaces on a 
	line do not divide evenly between words, the empty slots on the left will be assigned more spaces than the 
	slots on the right.<br>
		For the last line of text, it should be left justified and no extra space is inserted between words.<br>

	For example,
	<pre>
		words: ["This", "is", "an", "example", "of", "text", "justification."]
		L: 16.
	</pre>

	Return the formatted lines as:
	<pre>
	[
	   "This    is    an",
	   "example  of text",
	   "justification.  "
	]
	</pre>
	Note: Each word is guaranteed not to exceed L in length.

		click to show corner cases.

	Corner Cases:
		<ul><li>
		A line other than the last line might contain only one word. What should you do in this case?
		In this case, that line should be left-justified.
		</li></ul>
 *	思路：<br>
 *		这 道题属于纯粹的字符串操作，要把一串单词安排成多行限定长度的字符串。主要难点在于空格的安排，首先每个单词之间必须有空格隔开，而当当前行放
 *	不下更多的 单词并且字符又不能填满长度L时，我们要把空格均匀的填充在单词之间。如果剩余的空格量刚好是间隔倍数那么就均匀分配即可，否则还必须把多的
 *	一个空格放到 前面的间隔里面。实现中我们维护一个count计数记录当前长度，超过之后我们计算共同的空格量以及多出一个的空格量，然后将当行字符串构造
 *	出来。最后一 个细节就是最后一行不需要均匀分配空格，句尾留空就可以，所以要单独处理一下。时间上我们需要扫描单词一遍，然后在找到行尾的时候在扫描一
 *	遍当前行的单 词，不过总体每个单词不会被访问超过两遍，所以总体时间复杂度是O(n)。而空间复杂度则是结果的大小（跟单词数量和长度有关，不能准确定义，
 *	如果知道最 后行数r，则是O(r*L)）。
 *
 */
public class TextJustification {

	public static void main(String[] args) {
		String[] words = { "This", "is", "an", "example", "of", "text", "justification." };
		int L = 16;
		ArrayList<String> list = fullJustify(words, L);
		printList(list);
	}

	/**
	 * 字符串整理
	 * @param words 单词数组
	 * @param L line长度
	 * @return
	 */
	public static ArrayList<String> fullJustify(String[] words, int L) {
		
		ArrayList<String> result = new ArrayList<String>();
		if (words == null || words.length == 0)
			return result;
		
		int lineCount = 0; //lineCount是当前行的   已拼接单词的长度
		int lastWordIndex = 0;
		for (int i = 0; i < words.length; i++) {
			// words[i].length()是当前尝试放的一个单词的长度
			// 假设当前放上了这个单词，那么这一行单词间的间隔数就是     i - lastWordIndex
			if (lineCount + words[i].length() + (i - lastWordIndex) > L) {// 判断这些总的长度加起来是不是大于L（即当前的word[i]应该分配在下一行）
				int spaceNum = 0;//每隔单词的平均间隔数
				int extraNum = 0;//应从左往右分配的间隔数
				if (i - lastWordIndex - 1 > 0) {// 因为尝试的words[i]失败了，所以间隔数减1.此时判断剩余的间隔数是否大于0
					spaceNum = (L - lineCount) / (i - lastWordIndex - 1);//每个间隔的间隔数
					extraNum = (L - lineCount) % (i - lastWordIndex - 1);//需要从左从新分配的间隔数
				}
				
				StringBuilder line = new StringBuilder();
				for (int j = lastWordIndex; j < i; j++) {
					line.append(words[j]);
					if (j < i - 1) {// words[i-1]后面不用填空格，所以这里j<i-1
						for (int k = 0; k < spaceNum; k++)
							line.append(" ");

						if (extraNum > 0)
							line.append(" ");

						extraNum--;
					}
				}

				// 下面这个for循环作用于一行只有一个单词还没填满一行的情况
				for (int j = line.length(); j < L; j++)
					line.append(" ");

				result.add(line.toString());
				lineCount = 0;
				lastWordIndex = i;// 下一个开始的单词
			}
			lineCount += words[i].length();
		}

		// 处理最后一行
		StringBuilder line = new StringBuilder();
		for (int i = lastWordIndex; i < words.length; i++) {
			line.append(words[i]);
			if (line.length() < L)
				line.append(" ");
		}
		for (int i = line.length(); i < L; i++)
			line.append(" ");

		result.add(line.toString());
		return result;
	}


	/**
	 * 打印数组内容
	 * @param list 
	 */
	private static void printList(ArrayList<String> list) {
		for(String str : list){
			System.out.println(str);
		}
	}
	
}
