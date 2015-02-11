package com.way361.mongodb.core.convert;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;

import org.springframework.util.Assert;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.way361.mongodb.core.mapping.MongoPersistentProperty;

/**
 * Wrapper value object for a {@link BasicDBObject} to be able to access raw values by {@link MongoPersistentProperty}
 * references. The accessors will transparently resolve nested document values that a {@link MongoPersistentProperty}
 * might refer to through a path expression in field names.
 * @author xuefeihu
 *
 */
class DBObjectAccessor {
	
	private final BasicDBObject dbObject;
	
	/**
	 * Creates a new {@link DBObjectAccessor} for the given {@link DBObject}.
	 * 
	 * @param dbObject must be a {@link BasicDBObject} effectively, must not be {@literal null}.
	 */
	public DBObjectAccessor(DBObject dbObject) {

		Assert.notNull(dbObject, "DBObject must not be null!");
		Assert.isInstanceOf(BasicDBObject.class, dbObject, "Given DBObject must be a BasicDBObject!");

		this.dbObject = (BasicDBObject) dbObject;
	}

	/**
	 * Puts the given value into the backing {@link DBObject} based on the coordinates defined through the given
	 * {@link MongoPersistentProperty}. By default this will be the plain field name. But field names might also consist
	 * of path traversals so we might need to create intermediate {@link BasicDBObject}s.
	 * 
	 * @param prop must not be {@literal null}.
	 * @param value
	 */
	public void put(MongoPersistentProperty prop, Object value) {

		Assert.notNull(prop, "MongoPersistentProperty must not be null!");
		String fieldName = prop.getFieldName();

		if (!fieldName.contains(".")) {
			dbObject.put(fieldName, value);
			return;
		}

		Iterator<String> parts = Arrays.asList(fieldName.split("\\.")).iterator();
		DBObject dbObject = this.dbObject;

		while (parts.hasNext()) {

			String part = parts.next();

			if (parts.hasNext()) {
				BasicDBObject nestedDbObject = new BasicDBObject();
				dbObject.put(part, nestedDbObject);
				dbObject = nestedDbObject;
			} else {
				dbObject.put(part, value);
			}
		}
	}

	/**
	 * Returns the value the given {@link MongoPersistentProperty} refers to. By default this will be a direct field but
	 * the method will also transparently resolve nested values the {@link MongoPersistentProperty} might refer to through
	 * a path expression in the field name metadata.
	 * 
	 * @param property must not be {@literal null}.
	 * @return
	 */
	public Object get(MongoPersistentProperty property) {

		String fieldName = property.getFieldName();

		if (!fieldName.contains(".")) {
			return this.dbObject.get(fieldName);
		}

		Iterator<String> parts = Arrays.asList(fieldName.split("\\.")).iterator();
		Map<String, Object> source = this.dbObject;
		Object result = null;

		while (source != null && parts.hasNext()) {

			result = source.get(parts.next());

			if (parts.hasNext()) {
				source = getAsMap(result);
			}
		}

		return result;
	}

	@SuppressWarnings("unchecked")
	private Map<String, Object> getAsMap(Object source) {

		if (source instanceof BasicDBObject) {
			return (BasicDBObject) source;
		}

		if (source instanceof Map) {
			return (Map<String, Object>) source;
		}

		return null;
	}
}
