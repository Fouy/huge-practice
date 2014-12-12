package com.way361.jvm.classloader;

public class SuperClass {
	static {
		System.out.println("superclass init!");
	}
	
	public static int value = 123;
}
