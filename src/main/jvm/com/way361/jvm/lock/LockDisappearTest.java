package com.way361.jvm.lock;

/**
 * 消除锁测试
 * 
 * @author xuefeihu
 *
 *	VM Args: -server -XX:+DoEscapeAnalysis -XX:+EliminateLocks
 *			 -server -XX:+DoEscapeAnalysis -XX:-EliminateLocks
 */
public class LockDisappearTest {

	public static int CIRCLE = 2000000;

	public static void main(String args[]) throws InterruptedException {
		long start = System.currentTimeMillis();
		for (int i = 0; i < CIRCLE; i++) {
			craeteStringBuffer("JVM", "Diagnosis");
		}
		long bufferCost = System.currentTimeMillis() - start;
		System.out.println("craeteStringBuffer: " + bufferCost + " ms");
	}

	public static String craeteStringBuffer(String s1, String s2) {
		StringBuffer sb = new StringBuffer();
		sb.append(s1);
		sb.append(s2);
		return sb.toString();
	}

}
