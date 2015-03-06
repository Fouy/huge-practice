package com.way361.heima2;

import java.util.Random;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * ��д��
 * @author xuefeihu
 *
 */
public class ReadWriteLockTest {
	
	public static void main(String[] args) {
		
		final ReadWriteData data = new ReadWriteData();
		
		//���������̣߳�ʵ�ֶ�д������ʱ���Զ���дʱ���ɶ�Ҳ������д��
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
 * ��д����
 * @author xuefeihu
 *
 */
class ReadWriteData {
	
	private Object data = null;// �������ݣ�ֻ����һ���߳���д�����ݣ��������ж���߳�ͬʱ�������ݡ�
	ReadWriteLock rwlock = new ReentrantReadWriteLock();//��д��

	/**
	 * ������
	 */
	public void get() {
		
		rwlock.readLock().lock();//�Ӷ���
		try {
			System.out.println(Thread.currentThread().getName() + " be ready to read data!");
			Thread.sleep((long) (Math.random() * 1000));
			System.out.println(Thread.currentThread().getName() + "have read data :" + data);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			rwlock.readLock().unlock();//ȥ����
		}
	}

	/**
	 * д����
	 * @param data
	 */
	public void put(Object data) {

		rwlock.writeLock().lock();//��д��
		try {
			System.out.println(Thread.currentThread().getName() + " be ready to write data!");
			Thread.sleep((long) (Math.random() * 1000));
			this.data = data;
			System.out.println(Thread.currentThread().getName() + " have write data: " + data);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			rwlock.writeLock().unlock();//ȥд��
		}
	}
}
