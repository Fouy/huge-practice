package com.way361.heima2;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 进程通信--Condition-----》由传统的改造而来{@link TraditionalThreadCommunication}
 * 
 * @author xuefeihu
 *
 */
public class ConditionCommunication {

	public static void main(String[] args) {

		final Business business = new Business();
		new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 1; i <= 50; i++) {
					business.sub(i);
				}
			}
		}).start();

		for (int i = 1; i <= 50; i++) {
			business.main(i);
		}
	}

	/**
	 * 业务处理类（分别打印主线程和子线程的业务）
	 * @author xuefeihu
	 *
	 */
	static class Business {
		
		Lock lock = new ReentrantLock();//线程锁
		Condition condition = lock.newCondition();
		private boolean bShouldSub = true;//是否该子线程执行

		public void sub(int i) {
			lock.lock();
			try {
				while (!bShouldSub) {
					try {
						condition.await();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				for (int j = 1; j <= 10; j++) {
					System.out.println("sub thread sequence of " + j + ",loop of " + i);
				}
				bShouldSub = false;
				condition.signal();
			} finally {
				lock.unlock();
			}
		}

		public void main(int i) {
			lock.lock();
			try {
				while (bShouldSub) {
					try {
						condition.await();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				for (int j = 1; j <= 100; j++) {
					System.out.println("main thread sequence of " + j + ",loop of " + i);
				}
				bShouldSub = true;
				condition.signal();
			} finally {
				lock.unlock();
			}
		}
	}
}
