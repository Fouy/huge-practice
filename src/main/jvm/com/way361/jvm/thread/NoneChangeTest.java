package com.way361.jvm.thread;

import java.util.ArrayList;
import java.util.List;

/**
 * 不可见测试----final测试
 * @author xuefeihu
 *
 */
public class NoneChangeTest {
	
	public final List<String> lists = new ArrayList<String>();
	
	public static void main(String[] args) {
		NoneChangeTest noneChange = new NoneChangeTest();
		List<String> testLists = noneChange.lists;
		testLists.add("hugege");
		testLists.add("hugege");
		testLists.add("hugege");
		System.out.println(testLists.toString());
		System.out.println(noneChange.lists.toString());
		
	}
	
}
