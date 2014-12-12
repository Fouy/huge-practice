package com.way361.leetcode.reversewordsinastring;

import java.util.Stack;

/**
 * LeetCode | Reverse Words in a String
 * @author xuefeihu 
 * 
 * TOPIC�� Given an input string, reverse the string word by word. 
 * For example, Given s = "the sky is blue", return "blue is sky the".
 * ˼·��
	����1�����ȰѾ��ӿ����ɴ���ɵģ����硰A B C������˿��Խ����ӵ������ַ�ǰ�󽻻����õ���C' B' A'"��
	��ȻX����ʾ����Ĵ�X�����Եڶ����ǽ�ÿ�����е��ַ���ǰ�󽻻����������̵�ʱ�临�Ӷ�ΪO(n)���ռ临�Ӷ�
	ΪO(1)�����ַ�����ȱ����û�п��������������������ַ������������Ŀո��ַ�����ʼ��β���пո�ȡ�

	����2����������stack��һ����ʾ���ʣ�һ����ʾ���ӡ��������ǿո��ַ�ʱ���뵥��stack���������ո�ʱ����
	��stack�е��ַ�ѹ�����stack�У�ע�⣺���ʴ�ʱ�Ѿ�����һ�Σ���Ȼ������һ���ո���󽫾���stack
	�����������ʱ����������������ĵ���ͬ����1.
 */

public class ReverseWordsInaString {
	public static void main(String[] args) {
		String str = "the sky is blue";
		new ReverseWordsInaString2().reverseWords(str.toCharArray());
	}

	/**
	 * ��ת�ַ���
	 * @param str ����ת���ַ���
	 */
	void reverseWords(char[] str) {
		int begin = 0;
		int end = 0;
		System.out.println("before reverse : " + String.valueOf(str));
		//��ת���еĵ���
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
		//���������з�ת
		swapString(str, 0, str.length - 1);

		System.out.println("after reverse : " + String.valueOf(str));
	}

	/**
	 * ���ַ��е�beginλ�ú�end֮����ַ����з�ת
	 * @param str �ַ�����
	 * @param begin ��ʼλ��
	 * @param end ����λ��
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
 * ����2
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
