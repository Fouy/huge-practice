package com.way361.mongodb.core;

import org.springframework.util.Assert;

import com.mongodb.DBObject;
import com.mongodb.WriteConcern;
/**
 * Represents an action taken against the collection. Used by {@link WriteConcernResolver} to determine a custom
 * {@link WriteConcern} based on this information.
 * <ul>
 * <li>INSERT, SAVE have null query</li>
 * <li>REMOVE has null document</li>
 * <li>INSERT_LIST has null entityType, document, and query</li>
 * </ul>
 * @author xuefeihu
 *
 */
public class MongoAction {

	private final String collName;
	private final WriteConcern defaultWriteConcern;
	private final Class<?> entityType;
	private final MongoActionOperation mongoActionOperation;
	private final DBObject query;
	private final DBObject document;
	
	/**
	 * Create an instance of a MongoAction.
	 * @param defaultWriteConcern the default write concern.
	 * @param mongoActionOperation action being taken against the collection
	 * @param collName the collection name, must not be {@literal null} or empty.
	 * @param entityType the POJO that is being operated against
	 * @param document the converted DBObject from the POJO or Spring Update object
	 * @param query the converted DBOjbect from the Spring Query object
	 */
	public MongoAction(WriteConcern defaultWriteConcern, MongoActionOperation mongoActionOperation,
			String collName, Class<?> entityType, DBObject document, DBObject query){
		
		Assert.hasText(collName, "Collection name must not be null or empty!");
		
		this.defaultWriteConcern = defaultWriteConcern;
		this.mongoActionOperation = mongoActionOperation;
		this.collName = collName;
		this.entityType = entityType;
		this.query = query;
		this.document = document;
	}

	public String getCollName() {
		return collName;
	}

	public WriteConcern getDefaultWriteConcern() {
		return defaultWriteConcern;
	}

	public Class<?> getEntityType() {
		return entityType;
	}

	public MongoActionOperation getMongoActionOperation() {
		return mongoActionOperation;
	}

	public DBObject getQuery() {
		return query;
	}

	public DBObject getDocument() {
		return document;
	}
	
}
