package com.way361.mongodb.core.convert;

import com.way361.mongodb.core.mapping.MongoPersistentProperty;

/**
 * Callback interface to be used in conjunction with DbRefResolver.
 * @author xuefeihu
 *
 */
public interface DbRefResolverCallback {

	/**
	 * Resolve the final object for the given MongoPersistentProperty.
	 * @param property
	 * @return
	 */
	Object resolve(MongoPersistentProperty property);
	
}
