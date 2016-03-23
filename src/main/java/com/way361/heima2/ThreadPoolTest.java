package com.way361.heima2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 线程池测试，线程池主要用来程序运行时的线程数量，这样可以减少因线程的反复创建而造成的资源浪费，可以合理的调整线程数量来使计算机 的硬件得到合理的利用。
 * 
 * @author xuefeihu
 *
 */
public class ThreadPoolTest {

	public static void main(String[] args) {
		// ExecutorService threadPool = Executors.newFixedThreadPool(3);//创建固定线程
		// ExecutorService threadPool = Executors.newCachedThreadPool();//创建缓存线程
		ExecutorService threadPool = Executors.newSingleThreadExecutor();// 创建单一线程
		for (int i = 1; i <= 10; i++) {
			final int task = i;
			threadPool.execute(new Runnable() {
				@Override
				public void run() {
					for (int j = 1; j <= 10; j++) {
						try {
							Thread.sleep(20);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						System.out.println(Thread.currentThread().getName()
								+ " is looping of " + j + " for  task of "
								+ task);
					}
				}
			});
		}
		System.out.println("all of 10 tasks have committed! ");
		// threadPool.shutdownNow();

		//创建一个定时器线程池
		Executors.newScheduledThreadPool(3).scheduleAtFixedRate(new Runnable() {
			@Override
			public void run() {
				System.out.println("bombing!");

			}
		}, 6, 2, TimeUnit.SECONDS);
	}

}
