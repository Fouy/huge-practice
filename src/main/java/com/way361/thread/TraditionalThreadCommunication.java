package com.way361.thread;
/**
 * 在主线程和子线程中分别轮换输出50次，在主线程中每次输出100次、子线程中每次输出10次---------4
 * @author xuefeihu
 *
 */
public class TraditionalThreadCommunication {
	public static void main(String[] args) {
		final Business business = new Business();
		
		new Thread(new Runnable(){
			@Override
			public void run() {
				for(int i = 0; i < 50; i++){
					business.subOutput();
				}
			}
		}).start();
		
		for(int i = 0; i < 50; i++){
			business.mainOutput();
		}
	}
}

class Business {
	private boolean isSub = true;
	
	//子线程的输出方法
	public synchronized void subOutput(){
		while(!isSub){
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		for(int i = 0; i < 10; i++){
			System.out.println("this is the sub thread output... The order is" + (i + 1));
		}
		isSub = false;
		this.notifyAll();
	}
	
	//主线程的输出方法
	public synchronized void mainOutput(){
		while(isSub){
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		for(int i = 0; i < 100; i++){
			System.out.println("this is the main thread output... The order is" + (i + 1));
		}
		isSub = true;
		this.notifyAll();
	}
	
}
