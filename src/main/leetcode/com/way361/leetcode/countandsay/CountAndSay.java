package com.way361.leetcode.countandsay;
/**
 * LeetCode | Count and Say
 * @author xuefeihu
 *	��Ŀ��
		The count-and-say sequence is the sequence of integers beginning as follows:
	<pre>
	1, 11, 21, 1211, 111221, ...

	1 is read off as "one 1" or 11.
	11 is read off as "two 1s" or 21.
	21 is read off as "one 2, then one 1" or 1211.
	Given an integer n, generate the nth sequence.
	</pre>
		Note: The sequence of integers will be represented as a string.<br>
	˼·��
 		����һ�¾��ǣ�����n����ô�Ҿʹ����n�е��ַ�����
		��ôȷ����n���ַ����أ�����������й��ɵġ�
<pre>	
 	n = 1ʱ����ӡһ��1��
 	n = 2ʱ����n=1��һ�У��1��1�����Դ�ӡ��11��
 	n = 3ʱ����n=2��һ�У��2��1�����Դ�ӡ��21��
 	n = 4ʱ����n=3��һ�У��һ��2һ��1�����Դ�ӡ��1211��
	�Դ����ơ�(ע������n�Ǵ�1��ʼ�ģ�
</pre>
		���Թ�����ǰ�е��ַ���Ҫ������һ�е��ַ�������С�����������ѭ��֮��ǵð����һ���ַ�Ҳ���ϣ���Ϊ֮ǰֻ�Ǽ������ѡ�
	
 */
public class CountAndSay {

	public static void main(String[] args) {
//		for(int i = 1; i <= 10; i++){
//			System.out.println(countAndSay(i));;
//		}
		System.out.println(countAndSay(5));;
	}

	/**
	 * ���ص�n�ε��ƽ��
	 * @param n
	 * @return
	 */
	public static String countAndSay(int n) {
		if (n <= 0)
			return "";
		
		String curRes = "1";//���صĽ����
		int start = 1;// ��1��ʼ��
		while (start < n) {//���ƹ���
			StringBuilder res = new StringBuilder();
			int count = 1;
			for (int j = 1; j < curRes.length(); j++) {//ƴ��һ�ε��ƴ�
				if (curRes.charAt(j) == curRes.charAt(j - 1))//ͳ���ظ��ַ�
					count++;
				else {
					res.append(count);
					res.append(curRes.charAt(j - 1));
					count = 1;
				}
			}
			res.append(count);
			res.append(curRes.charAt(curRes.length() - 1));//ƴ�����һ���ַ�
			curRes = res.toString();
			start++;
		}
		return curRes;
	}

}
