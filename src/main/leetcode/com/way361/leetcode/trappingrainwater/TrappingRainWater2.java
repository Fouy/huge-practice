package com.way361.leetcode.trappingrainwater;
/**
 * LeetCode | Trapping Rain Water
 * @author xuefeihu
 * 
 * ��Ŀ��
     Given n non-negative integers representing an elevation map where the 
   width of each bar is 1, compute how much water it is able to trap after raining.
	 For example, Given [0,1,0,2,1,0,1,3,2,1,2,1], return 6.
	| 
    |
	|   (�������е�ֵת����ֱ��ͼ������Էŵ�ˮ��)
	|--|--|--|--|--|--|--|--|--|--|----------------------------
      The above elevation map is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this 
   case, 6 units of rain water (blue section) are being trapped.Thanks Marcos for contributing this image!
   ˼·:
         ��ԭ������Ļ����ϣ�����һ����ά�����飬ԭ�ȵ�����ȫ���ֽ�Ϊ1�ĵ�λ���Ӷ����Եõ�һ��  n * mostHigh �ľ���Ȼ��������ɡ���Ч�ʲ��ߣ� 
 */
public class TrappingRainWater2 {
	
	public static void main(String[] args) {
		int[] rains = {0,1,0,2,1,0,1,3,2,1,2,1};
		int count = trap(rains);
		System.out.println(count);
	}
	
	/**
	 * ����rains������
	 * @param rains ����
	 * @return
	 */
	public static int trap(int rains[]) {
		// Note: The Solution object is instantiated only once.
		if (rains == null)
			return 0;
		int n = rains.length;

		// �ҵ����ֵ
		int highest = rains[0];
		for (int i = 1; i < n; i++) {
			if (rains[i] > highest)
				highest = rains[i];
		}
		
		//������1�ľ���
		int[][] matrix = new int[n][highest];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < rains[i]; j++) {
				matrix[i][j] = 1;
			}
		}
		
		//������ȡ����
		int water = 0;
		for(int i = 0; i < highest; i++){
			int left = 0;
			for(int j = 0; j < n; j++){
				if(matrix[j][i] == 1){
					int added = left == 0 ? 0 : (j - left - 1);
					water += added;
					left = j;
				}
			}
		}
		return water;
	}
	
}

