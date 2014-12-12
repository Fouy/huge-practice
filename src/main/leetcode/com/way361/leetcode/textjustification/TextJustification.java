package com.way361.leetcode.textjustification;

import java.util.ArrayList;
/**
 * LeetCode | Text Justification
 * @author xuefeihu<br>
 * ��Ŀ��<br>
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
 *	˼·��<br>
 *		�� �������ڴ�����ַ���������Ҫ��һ�����ʰ��ųɶ����޶����ȵ��ַ�������Ҫ�ѵ����ڿո�İ��ţ�����ÿ������֮������пո������������ǰ�з�
 *	���¸���� ���ʲ����ַ��ֲ�����������Lʱ������Ҫ�ѿո���ȵ�����ڵ���֮�䡣���ʣ��Ŀո����պ��Ǽ��������ô�;��ȷ��伴�ɣ����򻹱���Ѷ��
 *	һ���ո�ŵ� ǰ��ļ�����档ʵ��������ά��һ��count������¼��ǰ���ȣ�����֮�����Ǽ��㹲ͬ�Ŀո����Լ����һ���Ŀո�����Ȼ�󽫵����ַ�������
 *	���������һ ��ϸ�ھ������һ�в���Ҫ���ȷ���ո񣬾�β���վͿ��ԣ�����Ҫ��������һ�¡�ʱ����������Ҫɨ�赥��һ�飬Ȼ�����ҵ���β��ʱ����ɨ��һ
 *	�鵱ǰ�еĵ� �ʣ���������ÿ�����ʲ��ᱻ���ʳ������飬��������ʱ�临�Ӷ���O(n)�����ռ临�Ӷ����ǽ���Ĵ�С�������������ͳ����йأ�����׼ȷ���壬
 *	���֪���� ������r������O(r*L)����
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
	 * �ַ�������
	 * @param words ��������
	 * @param L line����
	 * @return
	 */
	public static ArrayList<String> fullJustify(String[] words, int L) {
		
		ArrayList<String> result = new ArrayList<String>();
		if (words == null || words.length == 0)
			return result;
		
		int lineCount = 0; //lineCount�ǵ�ǰ�е�   ��ƴ�ӵ��ʵĳ���
		int lastWordIndex = 0;
		for (int i = 0; i < words.length; i++) {
			// words[i].length()�ǵ�ǰ���Էŵ�һ�����ʵĳ���
			// ���赱ǰ������������ʣ���ô��һ�е��ʼ�ļ��������     i - lastWordIndex
			if (lineCount + words[i].length() + (i - lastWordIndex) > L) {// �ж���Щ�ܵĳ��ȼ������ǲ��Ǵ���L������ǰ��word[i]Ӧ�÷�������һ�У�
				int spaceNum = 0;//ÿ�����ʵ�ƽ�������
				int extraNum = 0;//Ӧ�������ҷ���ļ����
				if (i - lastWordIndex - 1 > 0) {// ��Ϊ���Ե�words[i]ʧ���ˣ����Լ������1.��ʱ�ж�ʣ��ļ�����Ƿ����0
					spaceNum = (L - lineCount) / (i - lastWordIndex - 1);//ÿ������ļ����
					extraNum = (L - lineCount) % (i - lastWordIndex - 1);//��Ҫ������·���ļ����
				}
				
				StringBuilder line = new StringBuilder();
				for (int j = lastWordIndex; j < i; j++) {
					line.append(words[j]);
					if (j < i - 1) {// words[i-1]���治����ո���������j<i-1
						for (int k = 0; k < spaceNum; k++)
							line.append(" ");

						if (extraNum > 0)
							line.append(" ");

						extraNum--;
					}
				}

				// �������forѭ��������һ��ֻ��һ�����ʻ�û����һ�е����
				for (int j = line.length(); j < L; j++)
					line.append(" ");

				result.add(line.toString());
				lineCount = 0;
				lastWordIndex = i;// ��һ����ʼ�ĵ���
			}
			lineCount += words[i].length();
		}

		// �������һ��
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
	 * ��ӡ��������
	 * @param list 
	 */
	private static void printList(ArrayList<String> list) {
		for(String str : list){
			System.out.println(str);
		}
	}
	
}
