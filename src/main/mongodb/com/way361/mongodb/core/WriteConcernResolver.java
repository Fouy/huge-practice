package com.way361.mongodb.core;

import com.mongodb.WriteConcern;

/**
 * A strategy interface to determine the WriteConcern to use for a given
 * MongoAction. Return the passed in default WriteConcern (a property on
 * MongoAction) if no determination can be made.
 * 
 * @author xuefeihu
 *
 */
public interface WriteConcernResolver {

	/**
	 * Resolve the WriteConcern given the MongoAction.
	 * 
	 * @param action
	 *            describes the context of the Mongo action. Contains a default
	 *            WriteConcern to use if one should not be resolved.
	 * @return describes the context of the Mongo action. Contains a default
	 *         WriteConcern to use if one should not be resolved.
	 */
	WriteConcern resolve(MongoAction action);

}
