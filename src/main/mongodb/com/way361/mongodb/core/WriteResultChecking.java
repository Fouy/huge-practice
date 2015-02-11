package com.way361.mongodb.core;

/**
 * Enum to represent how strict the check of {@link com.mongodb.WriteResult}
 * shall be. It can either be skipped entirely (use {@link #NONE}), or errors
 * can be logged ({@link #LOG}) or cause an exception to be thrown
 * {@link #EXCEPTION}.
 * 
 * @author xuefeihu
 *
 */
public enum WriteResultChecking {

	NONE, LOG, EXCEPTION
	
}
