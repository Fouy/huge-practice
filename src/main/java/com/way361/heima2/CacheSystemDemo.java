package com.way361.heima2;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 面试题--缓存系统
 * 
 * @author xuefeihu
 *
 */
public class CacheSystemDemo {

	private Map<String, Object> cache = new HashMap<String, Object>();//数据缓存

	public static void main(String[] args) {

	}

	private ReadWriteLock rwl = new ReentrantReadWriteLock();//读写锁

	//获取数据，当多线程来获取缓存数据时，需要加上读写锁从而可以使锁粒度变小，提高效率
	public Object getData(String key) {
		
		rwl.readLock().lock();//加读锁
		Object value = null;
		
		try {
			value = cache.get(key);
			if (value == null) {
				rwl.readLock().unlock();//去读锁
				//读锁去除之后，其他线程可能对data的数值进行更改，所以下面需要再对数据进行判断
				rwl.writeLock().lock();//加写锁
				try {
					if (value == null) {
						value = "aaaa";// 实际失去queryDB();
					}
				} finally {
					rwl.writeLock().unlock();
				}
				rwl.readLock().lock();
			}
		} finally {
			rwl.readLock().unlock();
		}
		return value;
	}
}
