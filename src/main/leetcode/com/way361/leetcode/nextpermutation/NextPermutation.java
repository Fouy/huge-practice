package com.way361.leetcode.nextpermutation;

/**
 * leetCode | Next Permutation
 * @author xuefeihu <br>
 *	��Ŀ��<br>
		Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.
	If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending order).
	The replacement must be in-place, do not allocate extra memory.<br>

		Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the right-hand column.
	<pre>
	1,2,3 �� 1,3,2
	3,2,1 �� 1,2,3
	1,1,5 �� 1,5,1
	</pre>
	˼·��
	<pre>
		������Ǹ���һ�������һ�����У�����һ�����С�����������һ��������˵��������������(2,3,6,5,4,1)������һ�����еĻ���������������
	1) �ȴӺ���ǰ�ҵ���һ����������������������¼��λ��p�����������е�3����Ӧ��λ����1;
	2) �����������������
		(1) ������е����ֶ������������ģ���ô˵���������һ�����У���һ�����ǵ�һ������ʵ���������ַ�ת��������(����(6,5,4,3,2,1)��һ����(1,2,3,4,5,6));
		(2) �������p���ڣ���p��ʼ�����ң��ҵ���pλ��С�����ֵ�ǰһ��λ��q��Ȼ����������λ�ã����������е�4������λ�ú�õ�(2,4,6,5,3,1)������p֮�����
		�����ֵ��򣬱��������еõ�(2,4,1,3,5,6), �������Ҫ�����һ�����С�
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
		
		//�Ӻ���ǰ�ҵ���һ�������λ��P
		int i = num.length - 2;
		while (i >= 0 && num[i] >= num[i + 1]) {
			i--;
		}
		
		if (i >= 0) {
			int j = i + 1;
			while (j < num.length && num[j] > num[i]) {//�ҵ�Pλ��֮�󣬵�һ��С�ڸ�����λ�õ�ǰһ��λ��Q
				j++;
			}
			j--;
			//����P��Qλ�õ���
			int temp = num[i];
			num[i] = num[j];
			num[j] = temp;
		}
		//��תPλ���Ժ����������
		reverse(num, i + 1);
	}

	/**
	 * ��num����index֮������ݷ�ת������index��
	 * @param num ����
	 * @param index ��ʼλ��
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
	 * ��ӡint[]����
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
