package com.way361.jvm.memory.model;
/**
 * VM Args: -Xss2M
 * @author xuefeihu
 *
 */
public class JavaVMStackOOM {
	
	private void dontStop(){
		while(true){
		}
	}
	
	public void stackLeakByThread(){
		while(true){
			Thread thread = new Thread(new Runnable() {
				@Override
				public void run() {
					dontStop();
				}
			});
			thread.start();
		}
	}

	public static void main(String[] args) {
		JavaVMStackOOM vmStackOOM = new JavaVMStackOOM();
		vmStackOOM.stackLeakByThread();
	}

}
