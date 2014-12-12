package com.way361.thread;

import java.util.Random;

/**
 * ThreadLocal测试-------6
 * @author xuefeihu
 *
 */
public class ThreadLocalTest {
	public static ThreadLocal<Integer> threadX = new ThreadLocal<Integer>();

	public static void main(String[] args) {
		for(int i = 0; i < 2; i++){
			new Thread(new Runnable(){
				@Override
				public void run() {
					int data = new Random().nextInt();
					//mode 1  在主类内部操作变量
					threadX.set(data);
					System.out.println("threadX : " + threadX.get() + Thread.currentThread().getName() + "============");
					//mode 2  将数据分离开，并设置相应的数据操作者
					System.out.println(Thread.currentThread().getName() 
							+ "has put data :" + data);
					MyThreadScopeData.getInstance().setAge(data);
					MyThreadScopeData.getInstance().setName(data + "hugege");
					new OperateA().get();
					new OperateB().get();
				}
			}).start();
		}
	}
}

/**
 * 数据操作者A
 */
class OperateA{
	public void get(){
		MyThreadScopeData scopeData = MyThreadScopeData.getInstance();
		System.out.println("operaterA from " + Thread.currentThread().getName()
				+ "scopeData name is : " + scopeData.getName()
				+ "scopeData age is : " + scopeData.getAge());
	}
}

/**
 * 数据操作者B
 */
class OperateB{
	public void get(){
		MyThreadScopeData scopeData = MyThreadScopeData.getInstance();
		System.out.println("operaterB from " + Thread.currentThread().getName()
				+ "scopeData name is : " + scopeData.getName()
				+ "scopeData age is : " + scopeData.getAge());
	}
}

/**
 * 线程范围内的数据
 */
class MyThreadScopeData{
	private static ThreadLocal<MyThreadScopeData> map = new ThreadLocal<MyThreadScopeData>();
	private String name;
	private int age;
	
	private MyThreadScopeData() {}
	
	public static MyThreadScopeData getInstance(){
		MyThreadScopeData instance = map.get();
		if(instance == null){
			instance = new MyThreadScopeData();
			map.set(instance);
		}
		return instance;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
}


