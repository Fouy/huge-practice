package com.way361.leetcode.trappingrainwater;
/**
 * LeetCode | Trapping Rain Water
 * @author xuefeihu
 * 
 * 题目：
     Given n non-negative integers representing an elevation map where the 
   width of each bar is 1, compute how much water it is able to trap after raining.
	 For example, Given [0,1,0,2,1,0,1,3,2,1,2,1], return 6.
	| 
    |
	|   (将数组中的值转化成直方图，算可以放的水量)
	|--|--|--|--|--|--|--|--|--|--|----------------------------
      The above elevation map is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this 
   case, 6 units of rain water (blue section) are being trapped.Thanks Marcos for contributing this image!
   思路:
         在原先数组的基础上，创建一个二维的数组，原先的数组全部分解为1的单位，从而可以得到一个  n * mostHigh 的矩阵，然后遍历即可。（效率不高） 
 */
public class TrappingRainWater2 {
	
	public static void main(String[] args) {
		int[] rains = {0,1,0,2,1,0,1,3,2,1,2,1};
		int count = trap(rains);
		System.out.println(count);
	}
	
	/**
	 * 计量rains的容量
	 * @param rains 数组
	 * @return
	 */
	public static int trap(int rains[]) {
		// Note: The Solution object is instantiated only once.
		if (rains == null)
			return 0;
		int n = rains.length;

		// 找到最大值
		int highest = rains[0];
		for (int i = 1; i < n; i++) {
			if (rains[i] > highest)
				highest = rains[i];
		}
		
		//构建含1的矩阵
		int[][] matrix = new int[n][highest];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < rains[i]; j++) {
				matrix[i][j] = 1;
			}
		}
		
		//遍历获取容量
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

