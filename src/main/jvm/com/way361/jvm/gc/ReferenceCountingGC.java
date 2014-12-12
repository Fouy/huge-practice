package com.way361.jvm.gc;

public class ReferenceCountingGC {
	
	public Object instance = null;

	private static final int _1MB = 1024 * 1024;
	
	/**
	 * 用于占据内存，以便在GC日志中能看清楚是否被回收过
	 */
	private byte[] bigSize = new byte[2 * _1MB];
	
	public static void main(String[] args) {
		ReferenceCountingGC objA = new ReferenceCountingGC();
		ReferenceCountingGC objB = new ReferenceCountingGC();
		objA.instance = objB;
		objB.instance = objA;
		
		objA = null;
		objB = null;
		
		System.gc();
	}

}
