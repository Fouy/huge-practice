package com.way361.jvm.classloader;

public class SubClass extends SuperClass {
	static {
		System.out.println("subclass init!");
	}
}
