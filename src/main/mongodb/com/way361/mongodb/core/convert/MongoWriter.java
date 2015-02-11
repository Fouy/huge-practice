package com.way361.mongodb.core.convert;

import org.springframework.data.convert.EntityWriter;
import org.springframework.data.util.TypeInformation;

import com.mongodb.DBObject;
import com.mongodb.DBRef;
import com.way361.mongodb.core.mapping.MongoPersistentProperty;

/**
 * A MongoWriter is responsible for converting an object of type T to the native
 * MongoDB representation DBObject.
 * 
 * @author xuefeihu
 *
 * @param <T>
 */
public interface MongoWriter<T> extends EntityWriter<T, DBObject> {

	/**
	 * Converts the given object into one Mongo will be able to store natively.
	 * If the given object can already be stored as is, no conversion will
	 * happen.
	 * 
	 * @param obj
	 * @return
	 */
	Object convertToMongoType(Object obj);

	/**
	 * Converts the given object into one Mongo will be able to store natively
	 * but retains the type information in case the given TypeInformation
	 * differs from the given object type.
	 * @param obj
	 * @param typeInformation
	 * @return
	 */
	Object convertToMongoType(Object obj, TypeInformation<?> typeInformation);

	/**
	 * Creates a DBRef to refer to the given object.
	 * @param object
	 * @param referingProperty
	 * @return
	 */
	DBRef toDBRef(Object object, MongoPersistentProperty referingProperty);

}
