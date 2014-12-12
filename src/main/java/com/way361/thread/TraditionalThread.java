package com.way361.thread;
/**
 * ��ͳ��Thread----1
 * @author xuefeihu
 *
 */
public class TraditionalThread {

	public static void main(String[] args) {
		/*//ֱ��new Thread��
		new Thread(){
			@Override
			public void run() {
				while(true){
					try {
						Thread.currentThread().sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("new Thread()1: " + Thread.currentThread().getName());
//					System.out.println("new Thread()2: " + this.getName());
					System.out.println("new Thread(): " + Thread.currentThread().getPriority());
				}
			}
		}.start();
		//ʹ��new Runnable()��Ϊ���
		new Thread(new Runnable() {
			@Override
			public void run() {
				while(true){
					try {
						Thread.currentThread().sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("new Runnable(): " + Thread.currentThread().getName());
					System.out.println("new Runnable(): " + Thread.currentThread().getPriority());
				}
			}
		}){}.start();*/
		
		//���ַ�ʽ������ʹ��new Thread����������д��run()����
		new Thread(new Runnable(){
			@Override
			public void run() {
				while(true){
					try {
						Thread.currentThread().sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("new Runnable(): " + Thread.currentThread().getName() + " thread in runnable.");
				}
			}
		}){
			@Override
			public void run() {
				while(true){
					try {
						Thread.currentThread().sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("new Thread(): " + Thread.currentThread().getName() + " thread in thread.");
				}
			};
		}.start();
		
	}
	
}
