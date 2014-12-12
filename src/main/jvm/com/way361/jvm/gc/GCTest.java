package com.way361.jvm.gc;

public class GCTest{
	public static void main(String[] args) {
		new PrintThread().start();
	}
}


class PrintThread extends Thread {
	public static final long starttime = System.currentTimeMillis();

	@Override
	public void run() {
		try {
			while (true) {
				long t = System.currentTimeMillis() - starttime;
				System.out.println("time:" + t);
				Thread.sleep(100);
			}
		} catch (Exception e) {

		}
	}
}
