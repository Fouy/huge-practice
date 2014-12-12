package com.way361.thread;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 传统的定时器----2
 * @author xuefeihu
 *
 */
public class TraditionalTimer {
	private static int count = 0;

	public static void main(String[] args) {
		/*//简单匿名定时器
		new Timer().schedule(new TimerTask() {
			@Override
			public void run() {
				System.out.println("Bombing!");
			}
		}, 1000, 1000);*/
		
		//自定义TimeTask每隔交替时间2S和4S，轮询输出 mytimertask bombing
		class MyTimerTask extends TimerTask {
			@Override
			public void run() {
				count = (count+1) % 2;
				System.out.println("mytimertask bombing!");
				new Timer().schedule(new MyTimerTask(), 2000 + 2000*count);
			}
		}
		
		new Timer().schedule(new MyTimerTask(), 2000);
		
		//用于显示间隔中间的时间秒数
		while(true){
			System.out.println(new Date().getSeconds());
			try {
				Thread.currentThread().sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	
	
	

}
