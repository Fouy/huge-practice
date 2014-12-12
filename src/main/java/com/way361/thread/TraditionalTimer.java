package com.way361.thread;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * ��ͳ�Ķ�ʱ��----2
 * @author xuefeihu
 *
 */
public class TraditionalTimer {
	private static int count = 0;

	public static void main(String[] args) {
		/*//��������ʱ��
		new Timer().schedule(new TimerTask() {
			@Override
			public void run() {
				System.out.println("Bombing!");
			}
		}, 1000, 1000);*/
		
		//�Զ���TimeTaskÿ������ʱ��2S��4S����ѯ��� mytimertask bombing
		class MyTimerTask extends TimerTask {
			@Override
			public void run() {
				count = (count+1) % 2;
				System.out.println("mytimertask bombing!");
				new Timer().schedule(new MyTimerTask(), 2000 + 2000*count);
			}
		}
		
		new Timer().schedule(new MyTimerTask(), 2000);
		
		//������ʾ����м��ʱ������
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
