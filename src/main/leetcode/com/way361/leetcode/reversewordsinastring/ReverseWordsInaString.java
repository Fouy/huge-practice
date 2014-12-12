package com.way361.leetcode.reversewordsinastring;

import java.util.Stack;

/**
 * LeetCode | Reverse Words in a String
 * @author xuefeihu 
 * 
 * TOPIC： Given an input string, reverse the string word by word. 
 * For example, Given s = "the sky is blue", return "blue is sky the".
 * 思路：
	方法1：首先把句子看做由词组成的，例如“A B C”，因此可以将句子的所有字符前后交换，得到“C' B' A'"。
	显然X‘表示逆序的词X，所以第二步是将每个词中的字符串前后交换。整个过程的时间复杂度为O(n)，空间复杂度
	为O(1)。这种方法的缺点是没有考虑许多特殊情况，例如字符串中有连续的空格，字符串开始结尾处有空格等。

	方法2：利用两个stack，一个表示单词，一个表示句子。当遇到非空格字符时放入单词stack；当遇到空格时将单
	词stack中的字符压入句子stack中（注意：单词此时已经逆序一次），然后仅添加一个空格。最后将句子stack
	依次输出，此时句子逆序。两次逆序的道理同方法1.
 */

public class ReverseWordsInaString {
	public static void main(String[] args) {
		String str = "the sky is blue";
		new ReverseWordsInaString2().reverseWords(str.toCharArray());
	}

	/**
	 * 反转字符串
	 * @param str 待反转的字符串
	 */
	void reverseWords(char[] str) {
		int begin = 0;
		int end = 0;
		System.out.println("before reverse : " + String.valueOf(str));
		//反转所有的单词
		while (end < str.length) {
			if (str[end] == ' ') {
				swapString(str, begin, end - 1);
				begin = end + 1;
				end = begin;
			} else {
				end++;
			}
		}
		swapString(str, begin, end - 1);
		//将整个序列反转
		swapString(str, 0, str.length - 1);

		System.out.println("after reverse : " + String.valueOf(str));
	}

	/**
	 * 将字符中的begin位置和end之间的字符序列反转
	 * @param str 字符序列
	 * @param begin 开始位置
	 * @param end 结束位置
	 */
	void swapString(char[] str, int begin, int end) {
		while (end > begin) {
			char c = str[begin];
			str[begin] = str[end];
			str[end] = c;
			begin++;
			end--;
		}
	}
}

/**
 * 方法2
 * @author xuefeihu
 *
 */
class ReverseWordsInaString2 {
	
    void reverseWords(char[] str) {
    	Stack<Character> word = new Stack<Character>();
    	Stack<Character> sentence = new Stack<Character>();
        int i = 0;
        System.out.println("before reverse :" + String.valueOf(str));
        
        while(i <= str.length){
            if(i == str.length || str[i] == ' '){
                if(!word.empty()){
                    if(!sentence.empty()){
                        sentence.push(' ');
                    }
                    while(!word.empty()){
                        sentence.push(word.pop());
                    }
                }
            } else {
                word.push(str[i]);
            }
            i++;
        }
        
        for(int j = 0; j < str.length; j++){
        	str[j] = sentence.pop();
        }
        
        System.out.println("after reverse :" + String.valueOf(str));
    }
}
