package com.way361.interview.bank.bean;

import java.util.ArrayList;
import java.util.List;

public class MainExecute {
	
	public static List<CustomerArrival> sequence = new ArrayList<CustomerArrival>();

	public static void main(String[] args) {
		System.out.println(ConvertFile.getCustomers(null));
	}

}
