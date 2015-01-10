package com.way361.interview.bank.bean;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * 文件转换工具
 * @author xuefeihu
 *
 */
public class ConvertFile {

	/** 文件路径 */
	public static String filePath = "C:/bank.txt";
	
	/**
	 * 获取客户到达情况
	 * @param path 文件路径
	 * @return
	 */
	public static List<CustomerArrival> getCustomers(String path){
		if(null != path && !"".equals(path.trim())){
			filePath = path;
		}
		
		List<CustomerArrival> arrivals = new ArrayList<CustomerArrival>();
		FileReader reader = null;
		BufferedReader bufferedReader = null;
		try {
			reader = new FileReader(new File(filePath));
			bufferedReader = new BufferedReader(reader);
			String line = null;
			while((line = bufferedReader.readLine()) != null){
				arrivals.add(convertLine(line));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				bufferedReader.close();
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return arrivals;
	}

	/**
	 * 转换行
	 * @param line
	 * @return
	 */
	private static CustomerArrival convertLine(String line) {
		CustomerArrival arrival = null;
		String[] items = line.split(" ");
		if(items.length == 4){
			int type = 0;
			if(!items[1].trim().equals("普通")) type = 1;
			Calendar time = Calendar.getInstance();
			time.set(Calendar.HOUR, Integer.parseInt(items[2].substring(0, 2)));
			time.set(Calendar.MINUTE, Integer.parseInt(items[2].substring(3)));
			arrival = new CustomerArrival(Integer.parseInt(items[0]), type, time, Integer.parseInt(items[3]));
		}
		return arrival;
	}
	
	public static void main(String[] args) {
		Calendar calendar = Calendar.getInstance();
		System.out.println(calendar);
	}
	
}
