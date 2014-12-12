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
         ��ԭ������Ļ����ϣ�������������   leftMostHeight[] (iλ����ߵ����ֵ) ��rightMostHeight[] (iλ���ұߵ����ֵ)��
   ֮�����ԭ�����飬��ȡiλ����������Ҳ�������Сֵmin, ֮������Сֵ��ȥrains[i]�����ȡ����0����Ӽ��ɡ�
 */
public class TrappingRainWater {

	public static void main(String[] args) {
		int[] rains = {0,1,0,2,1,0,1,3,2,1,2,1};
		int water = trap(rains);
		System.out.println("the water is " + water);
	}
	
	/**
	 * ����rains������
	 * @param rains ����
	 * @return
	 */
	public static int trap(int[] rains) {
        // Note: The Solution object is instantiated only once.
        if(rains == null) return 0;
        int n = rains.length;
        
        //��ȡ���������ߵ���ߵ�
		int maxheight = 0;
		int[] leftMostHeight = new int[n];
		for(int i = 0; i < n; i++) {
			leftMostHeight[i] = maxheight;
			maxheight = maxheight > rains[i] ? maxheight : rains[i];
		}

		//��ȡ�������ұߵ���ߵ�
		maxheight = 0;
		int[] rightMostHeight = new int[n];
		for(int i = n-1; i >= 0; i--) {
			rightMostHeight[i] = maxheight;
			maxheight = maxheight > rains[i] ? maxheight : rains[i];
		}

		//��ȡ�����������
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
