package com.way361.mongodb.core;

/**
 * Enumeration for operations on a collection. Used with MongoAction to help
 * determine the WriteConcern to use for a given mutating operation
 * @author xuefeihu
 *
 */
public enum MongoActionOperation {

	REMOVE, UPDATE, INSERT, INSERT_LIST, SAVE

}
