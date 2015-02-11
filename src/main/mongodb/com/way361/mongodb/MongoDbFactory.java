package com.way361.mongodb;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.support.PersistenceExceptionTranslator;

import com.mongodb.DB;

/**
 * Interface for factories creating DB instances.
 * 
 * @author xuefeihu
 *
 */
public interface MongoDbFactory {

	/**
	 * Creates a default DB instance.
	 * @return
	 * @throws DataAccessException
	 */
	DB getDb() throws DataAccessException;

	/**
	 * Creates a DB instance to access the database with the given name.
	 * @param dbName
	 * @return
	 * @throws DataAccessException
	 */
	DB getDb(String dbName) throws DataAccessException;

	/**
	 * Exposes a shared MongoExceptionTranslator.
	 * @return
	 */
	PersistenceExceptionTranslator getExceptionTranslator();

}
