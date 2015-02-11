package com.way361.mongodb.test;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;

public class SecondTest {
	
	public static final String COLL_PERSON = "person";
	
	private static Mongo connection = null;
	
	private static DB db = null;
	
	public SecondTest(String dbName) throws UnknownHostException{
		this.connection = new MongoClient("127.0.0.1", 27017);
		this.db = connection.getDB(dbName);
	}

	public static void main(String[] args) throws UnknownHostException {
		SecondTest mongo = new SecondTest("cloud");
		//create collection
//		DBObject document = new BasicDBObject();
//		db.createCollection(COLL_PERSON, document);
		
		//add data
//		DBObject document = new BasicDBObject();
//		document.put("name", "hugege");
//		document.put("age", 23);
//		List<String> books = new ArrayList<String>();
//		books.add("java");
//		books.add("JVM");
//		books.add("SQL");
//		db.getCollection(COLL_PERSON).insert(document);
		
		//add batch data
//		List<DBObject> dbObjects = new ArrayList<DBObject>();
//		for(int i = 0; i < 10; i++){
//			Map map = new HashMap();
//			map.put("name", "hugege" + i);
//			map.put("age", i);
//			dbObjects.add(new BasicDBObject(map));
//		}
//		db.getCollection(COLL_PERSON).insert(dbObjects);
		
		//delete data by id
//		DBObject dbObject = new BasicDBObject("_id", new ObjectId("54cf1cb0822aa7de94db51b6"));
//		int count = db.getCollection(COLL_PERSON).remove(dbObject).getN();
//		System.out.println("remove " + count + " records.");
		
		//delete data by others
//		DBObject dbObject = new BasicDBObject("name", "hugege0");
//		int count = db.getCollection(COLL_PERSON).remove(dbObject).getN();
//		System.out.println("remove " + count + " records.");
		
		//update data(add email to all the records)
//		DBObject dbObject = new BasicDBObject();
//		dbObject.put("$set", new BasicDBObject("e", 70));
//		int count = db.getCollection(COLL_PERSON).update(new BasicDBObject(), dbObject, false, true).getN();
//		System.out.println("updated " + count + " records.");
		
		//query name and age from the collection of person
//		DBObject dbObject = new BasicDBObject();
//		dbObject.put("_id", false);
//		dbObject.put("name", true);
//		dbObject.put("age", true);
//		DBCursor cursor = db.getCollection(COLL_PERSON).find(null, dbObject);
//		while(cursor.hasNext()){
//			DBObject dbObject2 = cursor.next();
//			System.out.println("name: " + dbObject2.get("name") +" age: " + dbObject2.get("age"));
//		}
		
		//query age>26 and e<80'
//		DBObject ref = new BasicDBObject();
//		ref.put("age", new BasicDBObject("$gte", 26));
//		ref.put("e", new BasicDBObject("$lte", 80));
//		DBCursor cursor = db.getCollection(COLL_PERSON).find(ref, null);
//		while(cursor.hasNext()){
//			DBObject dbObject = cursor.next();
//		    System.out.print(dbObject.get("name")+"-->");
//		    System.out.print(dbObject.get("age")+"-->");
//		    System.out.println(dbObject.get("e"));
//		}
		
		//query by page
		DBCursor cursor = db.getCollection(COLL_PERSON).find(null, null);
		cursor.skip(4).limit(4);
		while (cursor.hasNext()) {
			DBObject dbObject = cursor.next();
			System.out.print(dbObject.get("name") + "-->");
			System.out.print(dbObject.get("age") + "-->");
			System.out.println(dbObject.get("e"));
		}
		
		connection.close();
		System.out.println("SUCCESS!");
	}

}


















