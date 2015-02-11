package com.way361.mongodb.test.spring;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapreduce.GroupBy;
import org.springframework.data.mongodb.core.mapreduce.GroupByResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.CommandResult;

/**
 * simple spring-data-mongodb demo
 * @author xuefeihu
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("applicationContext.xml")
public class MongoDbTest {

	@Autowired
	private MongoTemplate mongoTemplate;
	
	@Test
	public void testAdd() {
		mongoTemplate.dropCollection(Person.class);
		Person user = new Person();

		for (int i = 0; i < 11; i++) {
			user.setId(i);
			user.setName("hugege"+i);
			user.setEmail(i+"@gmail.com");
			user.setAge(i);
			user.setE(i+12);
			mongoTemplate.save(user);
		}

		List<Person> userList = mongoTemplate.findAll(Person.class);

		for (Person t : userList) {
			System.out.println(t.getName());
		}

		System.out.println("query all the records after the add operation. ");
	}

	@Test
	public void testFind() {
		Person person = mongoTemplate.findById(2, Person.class);
		assertEquals("hugege2", person.getName());

		Query query = new Query();
		Criteria criteria = new Criteria();
		criteria.and("e").is(20);
		query.addCriteria(criteria);
		query.limit(2);
		List<Person> persons = mongoTemplate.find(query, Person.class);
		for (Person p : persons) {
			System.out.println(p.getName());
		}

		System.out.println(persons.size());
	}

	@Test
	public void testGroup() {
		GroupBy groupBy = GroupBy.key("age").initialDocument("{count:0}")
				.reduceFunction("function(doc, prev){prev.count+=1}");

		GroupByResults<Person> persons = mongoTemplate.group("person", groupBy, Person.class);
		BasicDBList list = (BasicDBList) persons.getRawResults().get("name");
		for (int i = 0; i < list.size(); i++) {
			BasicDBObject obj = (BasicDBObject) list.get(i);
			System.out.println(obj.get("count"));
		}
		System.out.println(persons);
	}

	@Test
	public void testDistinct() {
		CommandResult result = mongoTemplate.executeCommand("{distinct:'person', key:'age'}");
		BasicDBList list = (BasicDBList) result.get("values");
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
	}
}


