package com.way361.mongodb;

import org.springframework.dao.InvalidDataAccessApiUsageException;

public class InvalidMongoDbApiUsageException extends InvalidDataAccessApiUsageException {

	private static final long serialVersionUID = 879409627917862707L;

	public InvalidMongoDbApiUsageException (String msg) {
		super(msg);
	}
	
	public InvalidMongoDbApiUsageException (String msg, Throwable cause) {
		super(msg, cause);
	}
}
