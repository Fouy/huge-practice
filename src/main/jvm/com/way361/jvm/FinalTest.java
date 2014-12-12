package com.way361.jvm;

public class FinalTest {

	public static void main(String[] args) {
		
		FinalObject obj1 = new FinalObject();
		FinalObject obj2 = new FinalObject();
		System.out.println(obj1.obj == obj2.obj);
		
	}

}


class FinalObject {
	
	public final Object obj = new Object();
	
}
