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
         在原先数组的基础上，创建两个数组   leftMostHeight[] (i位置左边的最大值) 、rightMostHeight[] (i位置右边的最大值)。
   之后遍历原先数组，获取i位置左侧最大和右侧最大的最小值min, 之后用最小值减去rains[i]，最后取大于0的相加即可。
 */
public class TrappingRainWater {

	public static void main(String[] args) {
		int[] rains = {0,1,0,2,1,0,1,3,2,1,2,1};
		int water = trap(rains);
		System.out.println("the water is " + water);
	}
	
	/**
	 * 计量rains的容量
	 * @param rains 数组
	 * @return
	 */
	public static int trap(int[] rains) {
        // Note: The Solution object is instantiated only once.
        if(rains == null) return 0;
        int n = rains.length;
        
        //获取各个点的左边的最高点
		int maxheight = 0;
		int[] leftMostHeight = new int[n];
		for(int i = 0; i < n; i++) {
			leftMostHeight[i] = maxheight;
			maxheight = maxheight > rains[i] ? maxheight : rains[i];
		}

		//获取各个点右边的最高点
		maxheight = 0;
		int[] rightMostHeight = new int[n];
		for(int i = n-1; i >= 0; i--) {
			rightMostHeight[i] = maxheight;
			maxheight = maxheight > rains[i] ? maxheight : rains[i];
		}

		//获取各个点的容量
		int water = 0;
		for(int i = 0; i < n; i++) {
			int min = leftMostHeight[i] > rightMostHeight[i] ? rightMostHeight[i] : leftMostHeight[i];
			int high = min - rains[i];
			if(high > 0)
				water += high;
		}
		return water;
    }

}
