package com.way361.interview.bank.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * VIP服务窗口
 * @author xuefeihu
 *
 */
public class VipServiceWindow {
	
	/** 当前窗口已经服务过的客户 */
	private List<CustomerArrival> serviced = new ArrayList<CustomerArrival>();
	/** 是否空闲 */
	public boolean isFree = false;
	
	/**
	 * 服务客户
	 * @param arrival
	 */
	public void service(CustomerArrival arrival){
		
	}
	
	
}
