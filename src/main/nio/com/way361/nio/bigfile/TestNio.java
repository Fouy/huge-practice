package com.way361.nio.bigfile;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class TestNio {
	
	public static final int BUFSIZE = 1024;
	
	public static final String SEPARATION = "\r\n";
	
	public static int count = 0;

	@SuppressWarnings("resource")
	public static void main(String args[]) throws Exception {
		File srcFile = new File("D:/111.bak");
		File destFile = new File("D:/shifenzheng/s00001.bak");

		FileChannel srcChannel = new RandomAccessFile(srcFile, "r").getChannel();
		ByteBuffer readBuf = ByteBuffer.allocate(BUFSIZE);

		FileChannel destChannel = new RandomAccessFile(destFile, "rws").getChannel();
		ByteBuffer writeBuf = ByteBuffer.allocateDirect(BUFSIZE);

		readFileByLine(srcChannel, readBuf, destChannel, writeBuf);

		System.out.print("OK!!!");
	}

	/* 读文件同时写文件 */
	public static void readFileByLine(FileChannel srcChannel, ByteBuffer readBuf, FileChannel destChannel, ByteBuffer writeBuf) {
		try {
			byte[] buffer = new byte[BUFSIZE];

			StringBuffer tempLine = new StringBuffer("");
			while (srcChannel.read(readBuf) != -1) {
				int rSize = readBuf.position();
				readBuf.rewind();
				readBuf.get(buffer);
				readBuf.clear();
				String tempString = new String(buffer, 0, rSize);

				int startIndex = 0;
				int endIndex = 0;
				while ((endIndex = tempString.indexOf(SEPARATION, startIndex)) != -1) {
					String line = tempString.substring(startIndex, endIndex);
					line = new String(tempLine.toString() + line);
					writeFileByLine(destChannel, writeBuf, line);

					tempLine.delete(0, tempLine.length());
					startIndex = endIndex + 1;
				}
				if (rSize > tempString.length()) {
					tempLine.append(tempString.substring(startIndex, tempString.length()));
				} else {
					tempLine.append(tempString.substring(startIndex, rSize));
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 写文件
	 * @param destChannel
	 * @param writeBuf
	 * @param lineStr
	 */
	@SuppressWarnings("static-access")
	public static void writeFileByLine(FileChannel destChannel, ByteBuffer writeBuf, String lineStr) {
		try {
			// write on file head
			// fcout.write(wBuffer.wrap(line.getBytes()));
			// wirte append file on foot
			destChannel.write(writeBuf.wrap(lineStr.getBytes()), destChannel.size());
			System.out.println(lineStr);
			count++;
			if(count > 5) System.exit(0);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	

}