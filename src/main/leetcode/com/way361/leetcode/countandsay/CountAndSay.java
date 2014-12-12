package com.way361.leetcode.countandsay;
/**
 * LeetCode | Count and Say
 * @author xuefeihu
 *	题目：
		The count-and-say sequence is the sequence of integers beginning as follows:
	<pre>
	1, 11, 21, 1211, 111221, ...

	1 is read off as "one 1" or 11.
	11 is read off as "two 1s" or 21.
	21 is read off as "one 2, then one 1" or 1211.
	Given an integer n, generate the nth sequence.
	</pre>
		Note: The sequence of integers will be represented as a string.<br>
	思路：
 		解释一下就是，输入n，那么我就打出第n行的字符串。
		怎么确定第n行字符串呢？他的这个是有规律的。
<pre>	
 	n = 1时，打印一个1。
 	n = 2时，看n=1那一行，念：1个1，所以打印：11。
 	n = 3时，看n=2那一行，念：2个1，所以打印：21。
 	n = 4时，看n=3那一行，念：一个2一个1，所以打印：1211。
	以此类推。(注意这里n是从1开始的）
</pre>
		所以构建当前行的字符串要依据上一行的字符串。“小陷阱就是跑完循环之后记得把最后一个字符也加上，因为之前只是计数而已。
	
 */
public class CountAndSay {

	public static void main(String[] args) {
//		for(int i = 1; i <= 10; i++){
//			System.out.println(countAndSay(i));;
//		}
		System.out.println(countAndSay(5));;
	}

	/**
	 * 返回第n次递推结果
	 * @param n
	 * @return
	 */
	public static String countAndSay(int n) {
		if (n <= 0)
			return "";
		
		String curRes = "1";//返回的结果串
		int start = 1;// 从1开始算
		while (start < n) {//递推过程
			StringBuilder res = new StringBuilder();
			int count = 1;
			for (int j = 1; j < curRes.length(); j++) {//拼接一次递推串
				if (curRes.charAt(j) == curRes.charAt(j - 1))//统计重复字符
					count++;
				else {
					res.append(count);
					res.append(curRes.charAt(j - 1));
					count = 1;
				}
			}
			res.append(count);
			res.append(curRes.charAt(curRes.length() - 1));//拼接最后一个字符
			curRes = res.toString();
			start++;
		}
		return curRes;
	}

}
