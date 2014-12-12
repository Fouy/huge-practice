package com.way361.socket.learn.udp;

import java.io.*;
import java.net.*;

public class UDPClient {
	public static void main(String[] args) throws IOException {
		DatagramSocket client = new DatagramSocket();

		String sendStr = "Hello! Its from the Client";
		byte[] sendBuf;
		sendBuf = sendStr.getBytes();
		InetAddress addr = InetAddress.getByName("127.0.0.1");
		int port = 5050;
		DatagramPacket sendPacket = new DatagramPacket(sendBuf, sendBuf.length, addr, port);
		client.send(sendPacket);
		byte[] recvBuf = new byte[100];
		DatagramPacket recvPacket = new DatagramPacket(recvBuf, recvBuf.length);
		client.receive(recvPacket);
		String recvStr = new String(recvPacket.getData(), 0,
				recvPacket.getLength());
		System.out.println("�յ�:" + recvStr);
		client.close();
	}
}