package com.way361.mongodb;

import org.springframework.dao.UncategorizedDataAccessException;

public class LazyLoadingException extends UncategorizedDataAccessException {

	private static final long serialVersionUID = -1368144462717373689L;

	/**
	 * @param msg
	 * @param cause
	 */
	public LazyLoadingException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
