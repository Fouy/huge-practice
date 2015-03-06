package com.way361.mongodb.test.example;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.data.authentication.UserCredentials;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;

import com.mongodb.Mongo;

public class MongoApp {
	
	private static final Logger LOGGER = Logger.getLogger(MongoApp.class);

	public static void main(String[] args) throws Exception {
		
		UserCredentials credentials = new UserCredentials("huge", "123");
		MongoOperations operations = new MongoTemplate(new Mongo(), "database", credentials);
		
		// operation 1
//		operations.insert(new Person("Joe", 34));
//		
//		LOGGER.info(operations.findOne(new Query(where("name").is("Joe")), Person.class));
		
//		operations.dropCollection("person");
		
		// operation 2
//		operations.insert(new Person("Tom", 21));
//		operations.insert(new Person("Dick", 22));
//		operations.insert(new Person("Harry", 23));
//		
//		Query query = new Query(Criteria.where("name").is("Harry"));
//		Update update = new Update().inc("age", 1);
//		Person p = operations.findAndModify(query, update, Person.class);
//		System.out.println(p);
		
//		Assert.isTrue(p.getName().equals("Harry"));
//		Assert.isTrue(p.getAge() == 23);
//		p = operations.findOne(query, Person.class);
//		System.out.println(p);
//		Assert.isTrue(p.getAge() == 34);
		
//		p = operations.findAndModify(query, update, new FindAndModifyOptions().returnNew(true), Person.class);
//		System.out.println(p);
//		Assert.isTrue(p.getAge() == 34);
		
		//operation 3
//		Query query = new Query(Criteria.where("name").is("Harry"));
//		Update update = new Update().inc("age", 1);
//		Person p = operations.findAndModify(query, update, new FindAndModifyOptions().returnNew(true).upsert(true), Person.class);
//		System.out.println(p);
		
		//operation 4
//		BasicQuery query = new BasicQuery("{ age : { $lt : 50 }, name : \"Tom\"}");
//		List<Person> result = operations.find(query, Person.class);
//		System.out.println(result);
		
		//operation 5 Querying for documents using the MongoTemplate
//		List<Person> result = operations.find(query(where("age").lt(50).and("name").is("Tom")), Person.class);
//		System.out.println(result);
		
		
		
	}

}
