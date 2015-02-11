package com.way361.mongodb.core;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.support.PersistenceExceptionTranslator;
import org.springframework.data.authentication.UserCredentials;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import com.mongodb.DB;
import com.mongodb.Mongo;
import com.mongodb.WriteConcern;
import com.way361.mongodb.MongoDbFactory;

/**
 * Factory to create DB instances from a Mongo instance.
 * @author xuefeihu
 *
 */
public class SimpleMongoDbFactory implements DisposableBean, MongoDbFactory {
	
	private final Mongo mongo;
	private final String databaseName;
	private final boolean mongoInstanceCreated;
	private final UserCredentials credentials;
	private final PersistenceExceptionTranslator exceptionTranslator;
	private final String authenticationDatabaseName;
	
	private WriteConcern writeConcern;
	
	public SimpleMongoDbFactory(Mongo mongo, String databaseName) {
		this(mongo, databaseName, null);
	}
	
	public SimpleMongoDbFactory(Mongo mongo, String databaseName, UserCredentials credentials) {
		this(mongo, databaseName, credentials, false, null);
	}
	
	public SimpleMongoDbFactory(Mongo mongo, String databaseName, UserCredentials credentials,
			String authenticationDatabaseName) {
		this(mongo, databaseName, credentials, false, authenticationDatabaseName);
	}
	
	private SimpleMongoDbFactory(Mongo mongo, String databaseName, UserCredentials credentials,
			boolean mongoInstanceCreated, String authenticationDatabaseName) {
		Assert.notNull(mongo, "Mongo must not be null");
		Assert.hasText(databaseName, "Database name must not be empty");
		Assert.isTrue(databaseName.matches("[\\w-]+"), 
				"Database name must only contain letters, numbers, underscores and dashes!");
		
		this.mongo = mongo;
		this.databaseName = databaseName;
		this.mongoInstanceCreated = mongoInstanceCreated;
		this.credentials = credentials == null ? UserCredentials.NO_CREDENTIALS : credentials;
		this.exceptionTranslator = new MongoExceptionTranslator();
		this.authenticationDatabaseName = StringUtils.hasText(authenticationDatabaseName) ? authenticationDatabaseName
				: databaseName;

		Assert.isTrue(this.authenticationDatabaseName.matches("[\\w-]+"),
				"Authentication database name must only contain letters, numbers, underscores and dashes!");
	}
	
	/**
	 * Configures the WriteConcern to be used on the DB instance being created.
	 * @param writeConcern
	 */
	public void setWriteConcern(WriteConcern writeConcern) {
		this.writeConcern = writeConcern;
	}

	@Override
	public DB getDb() throws DataAccessException {
		return getDb(databaseName);
	}

	@Override
	public DB getDb(String dbName) throws DataAccessException {
		
		Assert.hasText(dbName, "Database name must not be empty.");
		
		DB db = MongoDbUtils.getDB(mongo, dbName, credentials, authenticationDatabaseName);
		
		if (writeConcern != null) {
			db.setWriteConcern(writeConcern);
		}
		
		return db;
	}
	
	private static String parseChars(char[] chars) {
		return chars == null ? null : String.valueOf(chars);
	}

	@Override
	public PersistenceExceptionTranslator getExceptionTranslator() {
		return this.exceptionTranslator;
	}

	@Override
	public void destroy() throws Exception {
		if (mongoInstanceCreated) {
			mongo.close();
		}
	}

}
