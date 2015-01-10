package com.way361.interview.bank.bean;

import java.util.Calendar;

/**
 * 客户到达情况实体
 * @author xuefeihu
 *
 */
public class CustomerArrival {
	/** 用户到达次序 **/
	public int sequenceId;
	/** 0-普通客户， 1-VIP客户 */
	public int type;
	/** 到达时间 */
	public Calendar arriveTime;
	/** 占用时长 */
	public int operateTime;
	/** 当前服务状态 */
	public boolean serviceStatus = false;
	/** 等待时长 */
	public int waitedTime = -1;
	
	/**
	 * 
	 * @param sequenceId
	 * @param type
	 * @param arriveTime
	 * @param operateTime
	 */
	public CustomerArrival(int sequenceId, int type, Calendar arriveTime, int operateTime) {
		this.sequenceId = sequenceId;
		this.type = type;
		this.arriveTime = arriveTime;
		this.operateTime = operateTime;
	}
	
}
