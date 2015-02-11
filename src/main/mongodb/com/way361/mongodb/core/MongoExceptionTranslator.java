package com.way361.mongodb.core;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.dao.support.PersistenceExceptionTranslator;
import org.springframework.data.mongodb.UncategorizedMongoDbException;

import com.mongodb.MongoCursorNotFoundException;
import com.mongodb.MongoException;
import com.mongodb.MongoInternalException;
import com.mongodb.MongoServerSelectionException;
import com.mongodb.MongoSocketException;
import com.mongodb.MongoTimeoutException;
import com.mongodb.MongoException.CursorNotFound;
import com.mongodb.MongoException.DuplicateKey;
import com.mongodb.MongoException.Network;

/**
 * Simple PersistenceExceptionTranslator for Mongo. Convert the given runtime
 * exception to an appropriate exception from the org.springframework.dao
 * hierarchy. Return null if no translation is appropriate: any other exception
 * may have resulted from user code, and should not be translated.
 * 
 * @author xuefeihu
 *
 */
public class MongoExceptionTranslator implements PersistenceExceptionTranslator {

	@Override
	public DataAccessException translateExceptionIfPossible(RuntimeException ex) {

		// Check for well-known MongoException subclasses.

		if (ex instanceof DuplicateKey || ex instanceof DuplicateKeyException) {
			return new DuplicateKeyException(ex.getMessage(), ex);
		}

		if (ex instanceof Network || ex instanceof MongoSocketException) {
			return new DataAccessResourceFailureException(ex.getMessage(), ex);
		}

		if (ex instanceof CursorNotFound
				|| ex instanceof MongoCursorNotFoundException) {
			return new DataAccessResourceFailureException(ex.getMessage(), ex);
		}

		if (ex instanceof MongoServerSelectionException) {
			return new DataAccessResourceFailureException(ex.getMessage(), ex);
		}

		if (ex instanceof MongoTimeoutException) {
			return new DataAccessResourceFailureException(ex.getMessage(), ex);
		}

		if (ex instanceof MongoInternalException) {
			return new InvalidDataAccessResourceUsageException(ex.getMessage(),
					ex);
		}

		// All other MongoExceptions
		if (ex instanceof MongoException) {

			int code = ((MongoException) ex).getCode();

			if (code == 11000 || code == 11001) {
				throw new DuplicateKeyException(ex.getMessage(), ex);
			} else if (code == 12000 || code == 13440) {
				throw new DataAccessResourceFailureException(ex.getMessage(),
						ex);
			} else if (code == 10003 || code == 12001 || code == 12010
					|| code == 12011 || code == 12012) {
				throw new InvalidDataAccessApiUsageException(ex.getMessage(),
						ex);
			}
			return new UncategorizedMongoDbException(ex.getMessage(), ex);
		}

		// If we get here, we have an exception that resulted from user code,
		// rather than the persistence provider, so we return null to indicate
		// that translation should not occur.
		return null;
	}

}
