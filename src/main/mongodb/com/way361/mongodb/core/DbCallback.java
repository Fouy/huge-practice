package com.way361.mongodb.core;

import org.springframework.dao.DataAccessException;

import com.mongodb.DB;
import com.mongodb.MongoException;

public interface DbCallback<T> {

	T doInDB(DB db) throws MongoException, DataAccessException;
	
}
