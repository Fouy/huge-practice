package com.way361.mongodb.test.spring;

/**
 * Person entity
 * 
 * @author xuefeihu
 *
 */
public class Person {

	private int id;
	private int age;
	private String name;
	private String email;
	/** English score */
	private int e;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getE() {
		return e;
	}

	public void setE(int e) {
		this.e = e;
	}

}
