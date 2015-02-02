package com.way361.mongodb;

import java.net.UnknownHostException;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.util.JSON;

public class FirstTest {
	
	public static final String TABLE_PERSON = "person";

	public static void main(String[] args) throws UnknownHostException {
		//创建Mongodb数据库链接对象
		Mongo mongo = new MongoClient("127.0.0.1", 27017);
		for(String name : mongo.getDatabaseNames()){
			System.out.println("DBName: " + name);
		}
		//创建数据库连接
		DB db = mongo.getDB("hugege");
		for(String name: db.getCollectionNames()){
			System.out.println("collectionName: " + name);
		}
		
		DBCollection persons = db.getCollection(TABLE_PERSON);
		DBCursor cursor = persons.find();
		while(cursor.hasNext()){
			DBObject person = cursor.next();
			System.out.println(person.get("name"));
		}
		
		System.out.println(cursor.count());
		System.out.println(cursor.getCursorId());
		System.out.println(JSON.serialize(cursor));
		
	}

}
