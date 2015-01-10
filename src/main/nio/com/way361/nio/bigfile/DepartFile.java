package com.way361.nio.bigfile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class DepartFile {

	/** 每个文件的记录条数 */
	public static int count = 0;
	
	public static String fileName = "D:/cdsgus.txt";

	public static void main(String[] args) {
		
		DepartFile.readFileByLines(fileName);
		
	}
	
	
	/**
	 * 以行为单位读取文件，常用于读面向行的格式化文件
	 */
	public static void readFileByLines(String fileName) {
		File file = new File(fileName);
		BufferedReader reader = null;
		try {
			System.out.println("以行为单位读取文件内容，一次读一整行：");
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			int line = 1;
			// 一次读入一行，直到读入null为文件结束
			while ((tempString = reader.readLine()) != null) {
				// 显示行号
				System.out.println("line " + line + ": " + tempString);
				count++;
				if (count > 10000)
					System.exit(0);
				line++;
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
		}
	}


}