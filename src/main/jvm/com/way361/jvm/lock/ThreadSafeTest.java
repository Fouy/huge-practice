package com.way361.jvm.lock;

import java.util.ArrayList;
import java.util.List;
/**
 * java线程安全测试
 * @author xuefeihu
 *
 */
public class ThreadSafeTest {

	public static void main(String[] args) throws InterruptedException {
		Thread t1 = new Thread(new AddToList(0));
		Thread t2 = new Thread(new AddToList(1));
		t1.start();
		t2.start();
		while (t1.isAlive() || t2.isAlive()) {
			Thread.sleep(1);
		}
		System.out.println(numberList.size());

	}

	public static List<Integer> numberList = new ArrayList<Integer>();

	public static class AddToList implements Runnable {
		int startnum = 0;

		public AddToList(int startnumber) {
			startnum = startnumber;
		}

		@Override
		public void run() {
			int count = 0;
			while (count < 1000000) {
				numberList.add(startnum);
				startnum += 2;
				count++;
			}
		}
	}

}
