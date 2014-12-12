package com.way361.socket.learn;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * URL≤‚ ‘
 * @author xuefeihu
 *
 */
public class URLTest {

	public static void main(String[] args) throws IOException {
		URL url = new URL("http://www.baidu.com/");
		InputStream inputStream = url.openStream();
		BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
		String msg = null;
		while((msg = reader.readLine()) != null){
			System.out.println(msg);
		}
		reader.close();
		
//		System.out.println(url.getProtocol());
//		System.out.println(url.getAuthority());
//		System.out.println(url.getContent());
//		System.out.println(url.getDefaultPort());
//		System.out.println(url.getFile());
//		System.out.println(url.getHost());
//		System.out.println(url.getPath());
//		System.out.println(url.getPort());
//		System.out.println(url.getQuery());
//		System.out.println(url.getRef());
//		System.out.println(url.getUserInfo());
	}

}
