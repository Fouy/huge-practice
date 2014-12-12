package com.way361.thread;
/**
 * ��ͳ�߳�ͬ��------3
 * @author xuefeihu
 *
 */
public class TraditionalThreadSynchronized {

	public static void main(String[] args) {
		new TraditionalThreadSynchronized().init();
	}
	
	//�̳߳�ʼ��
	private void init() {
		final Outputer outputer = new Outputer();
		final Outputer outputer2 = new Outputer();
		
		new Thread(new Runnable(){
			@Override
			public void run() {
				while(true){
					outputer.output2("111111111111111111111111111111");
					try {
						Thread.currentThread().sleep(20);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}){}.start();
		
		new Thread(new Runnable(){
			@Override
			public void run() {
				while(true){
					outputer2.output2("000000000000000000000000000000");
					try {
						Thread.currentThread().sleep(20);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}){}.start();
		
	}
	
	static class Outputer {
		//���ɽ����߳�ͬ������
		public void output(String temp){
			for(int i = 0; i < temp.length(); i++){
				System.out.print(temp.charAt(i));
			}
			System.out.println();
		}
		//ͬ������1
		public synchronized void output1(String temp){
			for(int i = 0; i < temp.length(); i++){
				System.out.print(temp.charAt(i));
			}
			System.out.println();
		}
		//ͬ������2
		public synchronized void output2(String temp){
			synchronized(this.getClass()) {
				for(int i = 0; i < temp.length(); i++){
					System.out.print(temp.charAt(i));
				}
				System.out.println();
			}
		}
		//ͬ������3
		public synchronized static void output3(String temp){
			for(int i = 0; i < temp.length(); i++){
				System.out.print(temp.charAt(i));
			}
			System.out.println();
		}
	}
	

}
