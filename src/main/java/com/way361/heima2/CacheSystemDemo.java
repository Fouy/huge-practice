package com.way361.heima2;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * ������--����ϵͳ
 * 
 * @author xuefeihu
 *
 */
public class CacheSystemDemo {

	private Map<String, Object> cache = new HashMap<String, Object>();//���ݻ���

	public static void main(String[] args) {

	}

	private ReadWriteLock rwl = new ReentrantReadWriteLock();//��д��

	//��ȡ���ݣ������߳�����ȡ��������ʱ����Ҫ���϶�д���Ӷ�����ʹ�����ȱ�С�����Ч��
	public Object getData(String key) {
		
		rwl.readLock().lock();//�Ӷ���
		Object value = null;
		
		try {
			value = cache.get(key);
			if (value == null) {
				rwl.readLock().unlock();//ȥ����
				//����ȥ��֮�������߳̿��ܶ�data����ֵ���и��ģ�����������Ҫ�ٶ����ݽ����ж�
				rwl.writeLock().lock();//��д��
				try {
					if (value == null) {
						value = "aaaa";// ʵ��ʧȥqueryDB();
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
