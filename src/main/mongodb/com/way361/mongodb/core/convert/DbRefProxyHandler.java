package com.way361.mongodb.core.convert;

import com.mongodb.DBRef;
import com.way361.mongodb.core.mapping.MongoPersistentProperty;

public interface DbRefProxyHandler {
	
	Object populateId(MongoPersistentProperty property, DBRef source, Object proxy);
	
}
