package com.way361.jvm.gc;

public class AllocationTest {
	
	private static final int _1MB = 1024 * 1024;

	/**
	 * VM Args: -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:SurvivorRatio=8
	 * 			-XX:+PrintGCDetails
	 * @param args
	 */
	public static void main(String[] args) {
		byte[] allocation1, allocation2, allocation3, allocation4;
		allocation1 = new byte[2 * _1MB];
		allocation2 = new byte[2 * _1MB];
		allocation3 = new byte[2 * _1MB];
		allocation4 = new byte[2 * _1MB];
	}

}
