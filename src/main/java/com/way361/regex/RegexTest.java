package com.way361.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexTest {
	
	public static void main(String[] args) {
		String email = "sunrise@revenco.com";
		String regex = "\\w+@\\w+\\.com";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(email);
		if(matcher.find())
			System.out.println(matcher.group());
		
	}
	
	
	
	
	
	
	
	
	
}
