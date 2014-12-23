package com.way361.jvm.monitor;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class WaitThreadTest {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		br.readLine();
		createBusyThread();
		br.readLine();
		Object obj = new Object();
		createLockThread(obj);
	}
	
	public static void createBusyThread() {
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				while (true)
					;
			}
		}, "testBusyThread");
		thread.start();
	}
	
	public static void createLockThread( final Object lock) {
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				synchronized (lock) {
					try {
						lock.wait();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}, "testLockThread");
		thread.start();
	}
	
}
