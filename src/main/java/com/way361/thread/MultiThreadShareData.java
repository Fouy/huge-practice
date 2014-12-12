package com.way361.thread;
/**
 * 线程范围内共享变量------5
 * @author xuefeihu
 *
 */
public class MultiThreadShareData {

	public static void main(String[] args) {
		ShareData data = new ShareData();
		new Thread(new MyRunnable1(data)).start();
		new Thread(new MyRunnable2(data)).start();
	}

}

/**
 * 自减处理
 */
class MyRunnable1 implements Runnable{
	private ShareData data;
	
	public MyRunnable1(ShareData data) {
		this.data = data;
	}

	@Override
	public void run() {
		for(int i = 0; i < 50; i++){
			data.decrement();
			System.out.println("after "+ Thread.currentThread().getName() +" seq " + i + " decrement the data is" + data.getJ());
			try {
				Thread.currentThread().sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

/**
 * 自增处理
 */
class MyRunnable2 implements Runnable{
	private ShareData data;
	
	public MyRunnable2(ShareData data) {
		this.data = data;
	}

	@Override
	public void run() {
		for(int i = 0; i < 50; i++){
			data.increment();
			System.out.println("after " + Thread.currentThread().getName() + " seq " + i + " increment the data is" + data.getJ());
			try {
				Thread.currentThread().sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

/**
 * 共享的数据
 */
class ShareData{
	private int j = 0;
	
	public synchronized void increment(){
		j++;
	}
	
	public synchronized void decrement(){
		j--;
	}

	public int getJ() {
		return j;
	}
	
}
