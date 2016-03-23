package com.way361.heima2;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 锁测试
 * @author xuefeihu
 *
 */
public class LockTest {

	public static void main(String[] args) {
		new LockTest().init();
	}

	private void init() {
		final Outputer outputer = new Outputer();
		
		//线程一   打印字符串"zhangxiaoxiang"
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					outputer.output("zhangxiaoxiang");
				}
			}
		}).start();

		//线程二   打印字符串"lihuoming"
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					outputer.output("lihuoming");
				}
			}
		}).start();
	}

	/**
	 * 输出器
	 * @author xuefeihu
	 *
	 */
	static class Outputer {
		Lock lock = new ReentrantLock();//所对象

		public void output(String name) {
			int len = name.length();
			lock.lock();//加锁
			try {
				for (int i = 0; i < len; i++) {
					System.out.print(name.charAt(i));
				}
				System.out.println();
			} finally {
				lock.unlock();//解锁
			}
		}

		public synchronized void output2(String name) {
			int len = name.length();
			for (int i = 0; i < len; i++) {
				System.out.print(name.charAt(i));
			}
			System.out.println();
		}

		public static synchronized void output3(String name) {
			int len = name.length();
			for (int i = 0; i < len; i++) {
				System.out.print(name.charAt(i));
			}
			System.out.println();
		}
	}
}
