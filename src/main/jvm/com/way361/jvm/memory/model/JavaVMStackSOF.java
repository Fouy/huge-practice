package com.way361.jvm.memory.model;
/**
 * vm Args: -Xss128k
 * @author xuefeihu
 *
 */
public class JavaVMStackSOF {
	
	private int stackLength = 1;
	
	public void stackLeak(){
		stackLength++;
		stackLeak();
	}

	public static void main(String[] args) throws Throwable {
		JavaVMStackSOF vmStackSOF = new JavaVMStackSOF();
		try {
			vmStackSOF.stackLeak();
		} catch (Throwable e) {
			System.out.println("stack length:" + vmStackSOF.stackLength);
			throw e;
		}
	}

}
