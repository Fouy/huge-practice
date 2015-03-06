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
 * callable �� Future ��callable���߳�ִ�е����񣬲��ҷ��ؽ����Futrue�ǽ���callable���صĽ��
 * @author xuefeihu
 *
 */
public class CallableAndFuture {

	public static void main(String[] args) {
		//ʾ��һ��ֱ��new�̣߳�������
		Callable<Integer> callable = new Callable<Integer>() {
			public Integer call() throws Exception {
				return new Random().nextInt(100);
			}
		};
		FutureTask<Integer> future = new FutureTask<Integer>(callable);
		new Thread(future).start();
		try {
			Thread.sleep(5000);// ������һЩ����
			System.out.println(future.get());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		
		//ʾ���������̳߳��ύһ�����񣬵ȴ��������
//		ExecutorService threadPool =  Executors.newSingleThreadExecutor();
//		Future<String> future = threadPool.submit(
//							new Callable<String>() {
//								public String call() throws Exception {
//									Thread.sleep(4000);
//									return "hello";
//								};
//							}
//					);
//		System.out.println("�ȴ����");
//		try {
//			System.out.println("�õ������" + future.get());//�ȴ��ص���������߳�����
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		
		//ʾ���������̳߳��ύ�������񣬵ȴ�����������
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
