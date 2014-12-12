package com.way361.jvm.args;

public class ArgsTest {

	public static void main(String[] args) {
		StringBuffer stringBuffer = new StringBuffer();
		for(int i = 0; i < 500; i++){
			stringBuffer.append("hello " + i);
			try {
				Thread.currentThread().sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}

}
