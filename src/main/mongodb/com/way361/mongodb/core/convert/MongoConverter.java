package com.way361.mongodb.core.convert;

import org.springframework.data.convert.EntityConverter;
import org.springframework.data.convert.EntityReader;

import com.mongodb.DBObject;
import com.way361.mongodb.core.mapping.MongoPersistentEntity;
import com.way361.mongodb.core.mapping.MongoPersistentProperty;

/**
 * Central Mongo specific converter interface which combines MongoWriter and
 * MongoReader.
 * 
 * @author xuefeihu
 *
 */
public interface MongoConverter extends
		EntityConverter<MongoPersistentEntity<?>, MongoPersistentProperty, Object, DBObject>,
		MongoWriter<Object>, EntityReader<Object, DBObject> {

	/**
	 * Returns thw TypeMapper being used to write type information into
	 * DBObjects created with that converter.
	 * 
	 * @return
	 */
	MongoTypeMapper getTypeMapper();

}
