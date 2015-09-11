package com.way361.leetcode.nextpermutation;

/**
 * leetCode | Next Permutation
 * @author xuefeihu <br>
 *	题目：<br>
		Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.
	If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending order).
	The replacement must be in-place, do not allocate extra memory.<br>

		Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the right-hand column.
	<pre>
	1,2,3 → 1,3,2
	3,2,1 → 1,2,3
	1,1,5 → 1,5,1
	</pre>
	思路：
	<pre>
		这道题是给定一个数组和一个排列，求下一个排列。下面我们用一个例子来说明，比如排列是(2,3,6,5,4,1)，求下一个排列的基本步骤是这样：
	1) 先从后往前找到第一个不是依次增长的数，记录下位置p。比如例子中的3，对应的位置是1;
	2) 接下来分两种情况：
		(1) 如果所有的数字都是依次增长的，那么说明这是最后一个排列，下一个就是第一个，其实把所有数字反转过来即可(比如(6,5,4,3,2,1)下一个是(1,2,3,4,5,6));
		(2) 否则，如果p存在，从p开始往后找，找到比p位置小的数字的前一个位置q，然后两个调换位置，比如例子中的4。调换位置后得到(2,4,6,5,3,1)。最后把p之后的所
		有数字倒序，比如例子中得到(2,4,1,3,5,6), 这个即是要求的下一个排列。
	</pre>
 */
public class NextPermutation {
	
	public static void main(String[] args) {
		int[] num = {2,3,6,5,4,1};
		new NextPermutation().nextPermutation(num);
		printArray(num);
	}
	
	public void nextPermutation(int[] num) {
		if (num == null || num.length == 0)
			return;
		
		//从后往前找到第一个降序的位置P
		int i = num.length - 2;
		while (i >= 0 && num[i] >= num[i + 1]) {
			i--;
		}
		
		if (i >= 0) {
			int j = i + 1;
			while (j < num.length && num[j] > num[i]) {//找到P位置之后，第一个小于该数的位置的前一个位置Q
				j++;
			}
			j--;
			//调换P和Q位置的数
			int temp = num[i];
			num[i] = num[j];
			num[j] = temp;
		}
		//反转P位置以后的所有数据
		reverse(num, i + 1);
	}

	/**
	 * 将num数组index之后的数据反转（包含index）
	 * @param num 数组
	 * @param index 开始位置
	 */
	private void reverse(int[] num, int index) {
		int l = index;
		int r = num.length - 1;
		while (l < r) {
			int temp = num[l];
			num[l] = num[r];
			num[r] = temp;
			l++;
			r--;
		}
	}
	
	/**
	 * 打印int[]数组
	 * @param num
	 */
	private static void printArray(int[] num){
		System.out.print("[ ");
		for(int i : num){
			System.out.print(i + ", ");
		}
		System.out.print("]");
	}
	
	
}
