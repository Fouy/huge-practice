package com.way361.heima2;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 线程同步通信-------三个线程同步通信（唤醒线程要有顺序）
 * 
 * @author xuefeihu
 *
 */
public class ThreeConditionCommunication {

	public static void main(String[] args) {

		final Business business = new Business();
		
		//开启线程一
		new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 1; i <= 50; i++) {
					business.sub2(i);
				}
			}
		}).start();

		//开启线程二
		new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 1; i <= 50; i++) {
					business.sub3(i);
				}
			}
		}).start();

		//开启线程三
		for (int i = 1; i <= 50; i++) {
			business.main(i);
		}

	}

	static class Business {
		Lock lock = new ReentrantLock();//同步锁
		Condition condition1 = lock.newCondition();
		Condition condition2 = lock.newCondition();
		Condition condition3 = lock.newCondition();
		private int shouldSub = 1;

		public void sub2(int i) {
			lock.lock();
			try {
				while (shouldSub != 2) {
					try {
						condition2.await();//使当前线程等待，并绑定到当前程程
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				for (int j = 1; j <= 10; j++) {
					System.out.println("sub2 thread sequence of " + j + ",loop of " + i);
				}
				shouldSub = 3;//设置下一次执行的方法
				condition3.signal();//唤醒之前绑定的线程
			} finally {
				lock.unlock();
			}
		}

		public void sub3(int i) {
			lock.lock();
			try {
				while (shouldSub != 3) {
					try {
						condition3.await();//使当前线程等待，并绑定到当前程程
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				for (int j = 1; j <= 20; j++) {
					System.out.println("sub3 thread sequence of " + j + ",loop of " + i);
				}
				shouldSub = 1;//设置下一次执行的方法
				condition1.signal();//唤醒之前绑定的线程
			} finally {
				lock.unlock();
			}
		}

		public void main(int i) {
			lock.lock();
			try {
				while (shouldSub != 1) {
					try {
						condition1.await();//使当前线程等待，并绑定到当前程程
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				for (int j = 1; j <= 100; j++) {
					System.out.println("main thread sequence of " + j + ",loop of " + i);
				}
				shouldSub = 2;//设置下一次执行的方法
				condition2.signal();//唤醒之前绑定的线程
			} finally {
				lock.unlock();
			}
		}
	}
}