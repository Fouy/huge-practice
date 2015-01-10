package com.way361.interview.bank.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * 服务窗口
 * @author xuefeihu
 *
 */
public class ServiceWindows {
	/** 普通窗口 */
	private List<CommonServiceWindow> commonServiceWindows = new ArrayList<CommonServiceWindow>();
	/** 正在等候的客户 （普通）*/
	private List<CustomerArrival> commonWait = new ArrayList<CustomerArrival>();
	/** VIP窗口 */
	private List<VipServiceWindow> vipServiceWindows = new ArrayList<VipServiceWindow>();
	/** 正在等候的客户(VIP) */
	private List<CustomerArrival> vipWait = new ArrayList<CustomerArrival>();
	
	private ServiceWindows(){
	}
	
	private ServiceWindows(int commonCount, int vipCount){
		for(int i = 0; i < commonCount; i ++){
			commonServiceWindows.add(new CommonServiceWindow());
		}
		for(int i = 0; i < vipCount; i ++){
			vipServiceWindows.add(new VipServiceWindow());
		}
	}
	
	public static ServiceWindows create(int commonCount, int vipCount){
		return new ServiceWindows(commonCount, vipCount);
	}

	
	public void arrival(CustomerArrival arrival){
		int index = checkFree(arrival.type);
		if(index > 0){
			
		}else if(arrival.type == 0) {//普通客户
			commonWait.add(arrival);
		}else {
			vipWait.add(arrival);
		}
		
	}

	/**
	 * 检查窗口是否空闲,并返回空闲的窗口位置
	 * @param type 0--普通客户  1--VIP客户
	 * @return 正数为有空闲，负数为繁忙
	 */
	private int checkFree(int type) {
		int result = -1;
		if(type == 0){//普通客户
			for(int i = 0; i < commonServiceWindows.size(); i++){
				if(commonServiceWindows.get(i).isFree)
					return i;
			}
		}else {//VIP客户
			for(int i = 0; i < vipServiceWindows.size(); i++){
				if(vipServiceWindows.get(i).isFree)
					return i;
			}
		}
		return result;
	}
	
	
	
	
}
