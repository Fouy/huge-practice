package com.way361.mongodb.core.query;

import com.mongodb.DBObject;

public interface CriteriaDefinition {

	/**
	 * Get {@link DBObject} representation.
	 * @return
	 */
	DBObject getCriteriaObject();
	
	/**
	 * Get the identifying {@literal key}.
	 * @return
	 */
	String getKey();
	
}
