package com.way361.jvm.gc;

import java.util.HashMap;
/**
 * VM Args: -Xmx512M -Xms512M -XX:+UseSerialGC -Xloggc:gc.log -XX:+PrintGCDetails  -Xmn1m -XX:PretenureSizeThreshold=50 -XX:MaxTenuringThreshold=1
 * @author xuefeihu
 *
 */
public class GCThreadTest{
	public static void main(String[] args) {
		new MyThread().start();
	}
}

class MyThread extends Thread{
	HashMap<Long,byte[]> map=new HashMap<Long,byte[]>();
	@Override
	public void run(){
		try{
			while(true){
				if(map.size()*512/1024/1024>=450){
					System.out.println("=====准备清理=====:" + map.size());
					map.clear();
				}
				
				for(int i=0;i<1024;i++){
					map.put(System.nanoTime(), new byte[512]);
				}
				Thread.sleep(1);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
