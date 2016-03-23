package com.way361.heima2;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * callable 与 Future ，callable是线程执行的任务，并且返回结果，Futrue是接收callable返回的结果
 * @author xuefeihu
 *
 */
public class CallableAndFuture {

	public static void main(String[] args) {
		//示例一，直接new线程，并启动
		Callable<Integer> callable = new Callable<Integer>() {
			public Integer call() throws Exception {
				return new Random().nextInt(100);
			}
		};
		FutureTask<Integer> future = new FutureTask<Integer>(callable);
		new Thread(future).start();
		try {
			Thread.sleep(5000);// 可能做一些事情
			System.out.println(future.get());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		
		//示例二，用线程池提交一个任务，等待任务完成
//		ExecutorService threadPool =  Executors.newSingleThreadExecutor();
//		Future<String> future = threadPool.submit(
//							new Callable<String>() {
//								public String call() throws Exception {
//									Thread.sleep(4000);
//									return "hello";
//								};
//							}
//					);
//		System.out.println("等待结果");
//		try {
//			System.out.println("拿到结果：" + future.get());//等待回调完成任务，线程阻塞
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		
		//示例三，用线程池提交批量任务，等待任务逐个完成
//		ExecutorService threadPool2 =  Executors.newFixedThreadPool(10);
//		CompletionService<Integer> completionService = new ExecutorCompletionService<Integer>(threadPool2);
//		for(int i=1;i<=10;i++){
//			final int seq = i;
//			completionService.submit(new Callable<Integer>() {
//				@Override
//				public Integer call() throws Exception {
//					Thread.sleep(new Random().nextInt(5000));
//					return seq;
//				}
//			});
//		}
//		for(int i=0;i<10;i++){
//			try {
//				System.out.println( completionService.take().get());
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
	}
	

}
