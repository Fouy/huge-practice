package com.way361.mongodb.core.convert;

import com.mongodb.DBRef;
import com.way361.mongodb.core.mapping.MongoPersistentEntity;
import com.way361.mongodb.core.mapping.MongoPersistentProperty;

/**
 * Used to resolve associations annotated with
 * com.way361.mongodb.core.mapping.DBRef.
 * 
 * @author xuefeihu
 *
 */
public interface DbRefResolver {

	/**
	 * Resolves the given DBRef into an object of the given
	 * MongoPersistentProperty's type. The method might return a proxy object
	 * for the DBRef or resolve it immediately. In both cases the
	 * DbRefResolverCallback will be used to obtain the actual backing object.
	 * 
	 * @param property
	 * @param callback
	 * @param proxyHandler
	 * @return
	 */
	Object resolveDbRef(MongoPersistentProperty property, DBRef dbRef,
			DbRefResolverCallback callback, DbRefProxyHandler proxyHandler);

	/**
	 * Creates a DBRef instance for the given
	 * com.way361.mongodb.core.mapping.DBRef annotation,
	 * MongoPersistentEntity and id.
	 * 
	 * @param annotation
	 * @param entity
	 * @param id
	 * @return
	 */
	DBRef createDbRef(com.way361.mongodb.core.mapping.DBRef annotation,
			MongoPersistentEntity<?> entity, Object id);

}
