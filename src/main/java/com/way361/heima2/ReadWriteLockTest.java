package com.way361.heima2;

import java.util.Random;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁
 * @author xuefeihu
 *
 */
public class ReadWriteLockTest {
	
	public static void main(String[] args) {
		
		final ReadWriteData data = new ReadWriteData();
		
		//启用三个线程，实现读写锁（读时可以读，写时不可读也不可以写）
		for (int i = 0; i < 3; i++) {
			new Thread() {
				public void run() {
					while (true) {
						data.get();
					}
				}
			}.start();

			new Thread() {
				public void run() {
					while (true) {
						data.put(new Random().nextInt(10000));
					}
				}
			}.start();
		}
	}
}

/**
 * 读写数据
 * @author xuefeihu
 *
 */
class ReadWriteData {
	
	private Object data = null;// 共享数据，只能有一个线程能写该数据，但可以有多个线程同时读该数据。
	ReadWriteLock rwlock = new ReentrantReadWriteLock();//读写锁

	/**
	 * 读操作
	 */
	public void get() {
		
		rwlock.readLock().lock();//加读锁
		try {
			System.out.println(Thread.currentThread().getName() + " be ready to read data!");
			Thread.sleep((long) (Math.random() * 1000));
			System.out.println(Thread.currentThread().getName() + "have read data :" + data);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			rwlock.readLock().unlock();//去读锁
		}
	}

	/**
	 * 写操作
	 * @param data
	 */
	public void put(Object data) {

		rwlock.writeLock().lock();//加写锁
		try {
			System.out.println(Thread.currentThread().getName() + " be ready to write data!");
			Thread.sleep((long) (Math.random() * 1000));
			this.data = data;
			System.out.println(Thread.currentThread().getName() + " have write data: " + data);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			rwlock.writeLock().unlock();//去写锁
		}
	}
}
