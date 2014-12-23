package com.way361.jvm.classloader;

import java.io.InputStream;

/**
 * 类加载器与 instanceof 关键字演示
 * @author xuefeihu
 *
 */
public class ClassLoaderTest {

	public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException {

		ClassLoader classLoader = new ClassLoader() {
			@Override
			public Class<?> loadClass(String name) throws ClassNotFoundException {
				try {
					String fileName = name.substring(name.lastIndexOf('.') + 1) + ".class";
					InputStream is = getClass().getResourceAsStream(fileName);
					if(is == null){
						return super.loadClass(name);
					}
					byte[] b = new byte[is.available()];
					is.read(b);
					return defineClass(name, b, 0, b.length);
				} catch (Exception e) {
					throw new ClassNotFoundException(name);
				}
			}
			
			@Override
			public String toString() {
				return "i am huge classloader...";
			}
			
		};
		
		Object obj = classLoader.loadClass("com.way361.jvm.classloader.ClassLoaderTest").newInstance();
		System.out.println(obj.getClass());
		System.out.println(obj instanceof com.way361.jvm.classloader.ClassLoaderTest);
		
		System.out.println("obj's classloader is " + obj.getClass().getClassLoader().getParent().getParent().getParent());
		System.out.println("this object's classloader is " + ClassLoaderTest.class.getClassLoader());
		
	}

}
