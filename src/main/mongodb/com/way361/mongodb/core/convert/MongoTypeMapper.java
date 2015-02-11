package com.way361.mongodb.core.convert;

import java.util.Set;

import org.springframework.data.convert.TypeMapper;

import com.mongodb.DBObject;

/**
 * Mongo-specific TypeMapper exposing that DBObjects might contain a type key.
 * 
 * @author xuefeihu
 *
 */
public interface MongoTypeMapper extends TypeMapper<DBObject> {

	/**
	 * Returns whether the given key is the type key.
	 * 
	 * @param key
	 * @return
	 */
	boolean isTypeKey(String key);

	/**
	 * Writes type restrictions to the given DBObject. This usually results in
	 * an $in-clause to be generated that restricts the type-key (e.g. _class)
	 * to be in the set of type aliases for the given restrictedTypes.
	 * 
	 * @param result
	 * @param restrictedTypes
	 */
	void writeTypeRestrictions(DBObject result, Set<Class<?>> restrictedTypes);

}
