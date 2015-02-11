package com.way361.mongodb.core;

import org.springframework.dao.DataAccessException;

import com.mongodb.DBCollection;
import com.mongodb.MongoException;

public interface CollectionCallback<T> {

	T doInCollection(DBCollection collection) throws MongoException, DataAccessException;
	
}
