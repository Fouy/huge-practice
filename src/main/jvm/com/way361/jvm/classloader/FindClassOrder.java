package com.way361.jvm.classloader;
/**
 * VM Args: -Xbootclasspath/a:E:\tmp
 * @author xuefeihu
 *
 */
public class FindClassOrder {

	public static void main(String[] args) {
		
		HelloLoader loader = new HelloLoader();
		loader.print();
		
	}

}
