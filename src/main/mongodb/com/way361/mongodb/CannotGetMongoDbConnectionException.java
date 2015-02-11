package com.way361.mongodb;

import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.data.authentication.UserCredentials;

/**
 * Exception being thrown in case we cannot connect to a MongoDB instance.
 * @author xuefeihu
 *
 */
public class CannotGetMongoDbConnectionException extends
		DataAccessResourceFailureException {

	private static final long serialVersionUID = -2678354098205512792L;

	private final UserCredentials credentials;
	private final String database;

	public CannotGetMongoDbConnectionException(String msg, Throwable cause) {
		super(msg, cause);
		this.database = null;
		this.credentials = UserCredentials.NO_CREDENTIALS;
	}

	public CannotGetMongoDbConnectionException(String msg) {
		this(msg, null, UserCredentials.NO_CREDENTIALS);
	}

	public CannotGetMongoDbConnectionException(String msg, String database,
			UserCredentials credentials) {
		super(msg);
		this.database = database;
		this.credentials = credentials;
	}

	/**
	 * Returns the UserCredentials that were used when trying to connect to the
	 * MongoDB instance.
	 * 
	 * @return
	 */
	public UserCredentials getCredentials() {
		return this.credentials;
	}

	/**
	 * Returns the name of the database trying to be accessed.
	 * 
	 * @return
	 */
	public String getDatabase() {
		return database;
	}

}
