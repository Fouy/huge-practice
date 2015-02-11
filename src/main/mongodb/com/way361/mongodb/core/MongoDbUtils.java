package com.way361.mongodb.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.authentication.UserCredentials;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import org.springframework.util.Assert;

import com.mongodb.DB;
import com.mongodb.Mongo;
import com.way361.mongodb.CannotGetMongoDbConnectionException;

/**
 * Helper class featuring helper methods for internal MongoDb classes. Mainly
 * intended for internal use within the framework.
 * 
 * @author xuefeihu
 *
 */
public class MongoDbUtils {

	private static final Logger LOGGER = LoggerFactory.getLogger(MongoDbUtils.class);

	/**
	 * Private constructor to prevent instantiation.
	 */
	private MongoDbUtils() {
	}
	
	/**
	 * Obtains a DB connection for the given Mongo instance and database name
	 * @param mongo
	 * @param databaseName
	 * @return
	 */
	public static DB getDB(Mongo mongo, String databaseName) {
		return doGetDB(mongo, databaseName, UserCredentials.NO_CREDENTIALS, true, databaseName);
	}
	
	public static DB getDB(Mongo mongo, String databaseName, UserCredentials credentials) {
		return getDB(mongo, databaseName, credentials, databaseName);
	}
	
	public static DB getDB(Mongo mongo, String databaseName, UserCredentials credentials,
			String authenticationDatabaseName) {

		Assert.notNull(mongo, "No Mongo instance specified!");
		Assert.hasText(databaseName, "Database name must be given!");
		Assert.notNull(credentials, "Credentials must not be null, use UserCredentials.NO_CREDENTIALS!");
		Assert.hasText(authenticationDatabaseName, "Authentication database name must not be null or empty!");

		return doGetDB(mongo, databaseName, credentials, true, authenticationDatabaseName);
	}
	
	private static DB doGetDB(Mongo mongo, String databaseName, UserCredentials credentials, boolean allowCreate,
			String authenticationDatabaseName) {

		DbHolder dbHolder = (DbHolder) TransactionSynchronizationManager.getResource(mongo);

		// Do we have a populated holder and TX sync active?
		if (dbHolder != null && !dbHolder.isEmpty() && TransactionSynchronizationManager.isSynchronizationActive()) {

			DB db = dbHolder.getDB(databaseName);

			// DB found but not yet synchronized
			if (db != null && !dbHolder.isSynchronizedWithTransaction()) {

				LOGGER.debug("Registering Spring transaction synchronization for existing MongoDB {}.", databaseName);

				TransactionSynchronizationManager.registerSynchronization(new MongoSynchronization(dbHolder, mongo));
				dbHolder.setSynchronizedWithTransaction(true);
			}

			if (db != null) {
				return db;
			}
		}

		// Lookup fresh database instance
		LOGGER.debug("Getting Mongo Database name=[{}]", databaseName);

		DB db = mongo.getDB(databaseName);
		boolean credentialsGiven = credentials.hasUsername() && credentials.hasPassword();

		DB authDb = databaseName.equals(authenticationDatabaseName) ? db : mongo.getDB(authenticationDatabaseName);

		synchronized (authDb) {

			if (credentialsGiven && !authDb.isAuthenticated()) {

				String username = credentials.getUsername();
				String password = credentials.hasPassword() ? credentials.getPassword() : null;

				if (!authDb.authenticate(username, password == null ? null : password.toCharArray())) {
					throw new CannotGetMongoDbConnectionException("Failed to authenticate to database [" + databaseName + "], "
							+ credentials.toString(), databaseName, credentials);
				}
			}
		}

		// TX sync active, bind new database to thread
		if (TransactionSynchronizationManager.isSynchronizationActive()) {

			LOGGER.debug("Registering Spring transaction synchronization for MongoDB instance {}.", databaseName);

			DbHolder holderToUse = dbHolder;

			if (holderToUse == null) {
				holderToUse = new DbHolder(databaseName, db);
			} else {
				holderToUse.addDB(databaseName, db);
			}

			// synchronize holder only if not yet synchronized
			if (!holderToUse.isSynchronizedWithTransaction()) {
				TransactionSynchronizationManager.registerSynchronization(new MongoSynchronization(holderToUse, mongo));
				holderToUse.setSynchronizedWithTransaction(true);
			}

			if (holderToUse != dbHolder) {
				TransactionSynchronizationManager.bindResource(mongo, holderToUse);
			}
		}

		// Check whether we are allowed to return the DB.
		if (!allowCreate && !isDBTransactional(db, mongo)) {
			throw new IllegalStateException("No Mongo DB bound to thread, "
					+ "and configuration does not allow creation of non-transactional one here");
		}

		return db;
	}
	
	/**
	 * Return whether the given DB instance is transactional, that is, bound to the current thread by Spring's transaction facilities.
	 * @param db
	 * @param mongo
	 * @return
	 */
	public static boolean isDBTransactional(DB db, Mongo mongo) {

		if (mongo == null) {
			return false;
		}
		DbHolder dbHolder = (DbHolder) TransactionSynchronizationManager.getResource(mongo);
		return dbHolder != null && dbHolder.containsDB(db);
	}
	
	/**
	 * Perform actual closing of the Mongo DB object, catching and logging any cleanup exceptions thrown.
	 * @param db
	 */
	public static void closeDB(DB db) {

		if (db != null) {
			LOGGER.debug("Closing Mongo DB object");
			try {
				db.requestDone();
			} catch (Throwable ex) {
				LOGGER.debug("Unexpected exception on closing Mongo DB object", ex);
			}
		}
	}
	
}
