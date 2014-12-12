package com.way361.thread;
/**
 * 传统的Thread----1
 * @author xuefeihu
 *
 */
public class TraditionalThread {

	public static void main(String[] args) {
		/*//直接new Thread类
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
		//使用new Runnable()作为入参
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
		
		//两种方式复合则使用new Thread方法体中重写的run()方法
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
