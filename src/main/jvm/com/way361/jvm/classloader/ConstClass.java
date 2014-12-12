package com.way361.jvm.classloader;

public class ConstClass {
	
	static {
		System.out.println("constClass init!");
	}
	
	public static final String HELLOWORLD = "hello world";
	
}
