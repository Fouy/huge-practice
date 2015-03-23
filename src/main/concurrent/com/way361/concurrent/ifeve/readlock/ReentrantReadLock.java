package com.way361.concurrent.ifeve.readlock;

import java.util.HashMap;
import java.util.Map;

/**
 * java读写锁实现---读锁可重入
 * @author xuefeihu
 *
 */
public class ReentrantReadLock {
	/** 存储读锁线程 */
	private Map<Thread, Integer> readingThreads = new HashMap<Thread, Integer>();

	/** 已加写锁数量 */
	private int writers = 0;
	/** 请求写锁数量 */
	private int writeRequests = 0;

	public synchronized void lockRead() throws InterruptedException {
		
		Thread callingThread = Thread.currentThread();
		while (!canGrantReadAccess(callingThread)) {
			wait();
		}

		readingThreads.put(callingThread, (getReadAccessCount(callingThread) + 1));
	}

	public synchronized void unlockRead() {
		
		Thread callingThread = Thread.currentThread();
		int accessCount = getReadAccessCount(callingThread);
		if (accessCount == 1) {
			readingThreads.remove(callingThread);
		} else {
			readingThreads.put(callingThread, (accessCount - 1));
		}
		notifyAll();
	}

	/**
	 * 是否可以分配读锁
	 * @param callingThread
	 * @return
	 */
	private boolean canGrantReadAccess(Thread callingThread) {
		if (writers > 0)
			return false;
		if (isReader(callingThread))
			return true;
		if (writeRequests > 0)
			return false;
		return true;
	}

	/**
	 * 获取读锁数量
	 * @param callingThread
	 * @return
	 */
	private int getReadAccessCount(Thread callingThread) {
		
		Integer accessCount = readingThreads.get(callingThread);
		if (accessCount == null)
			return 0;
		return accessCount.intValue();
	}

	/**
	 * 判断是否可重入
	 * @param callingThread
	 * @return
	 */
	private boolean isReader(Thread callingThread) {
		
		return readingThreads.get(callingThread) != null;
	}
}
