package com.way361.concurrent.ifeve.readlock;

/**
 * java读写锁实现--读写锁都不可以重入
 * @author xuefeihu
 * 
 * http://ifeve.com/read-write-locks/
 */
public class ReadWriteLock {
	
	private int readers = 0;
	private int writers = 0;
	private int writeRequests = 0;

	public synchronized void lockRead() throws InterruptedException {
		
		while (writers > 0 || writeRequests > 0) {
			wait();
		}
		readers++;
	}

	public synchronized void unlockRead() {
		
		readers--;
		notifyAll();
	}

	public synchronized void lockWrite() throws InterruptedException {
		
		writeRequests++;

		while (readers > 0 || writers > 0) {
			wait();
		}
		writeRequests--;
		writers++;
	}

	public synchronized void unlockWrite() throws InterruptedException {
		
		writers--;
		notifyAll();
	}
}