package com.way361.mongodb.core.convert;

import com.mongodb.DBRef;

/**
 * Allows direct interaction with the underlying LazyLoadingInterceptor.
 * @author xuefeihu
 *
 */
public interface LazyLoadingProxy {

	/**
	 * Initializes the proxy and returns the wrapped value.
	 * @return
	 */
	Object getTarget();
	
	/**
	 * Returns the DBRef represented by this LazyLoadingProxy, may be null.
	 * @return
	 */
	DBRef toDBRef();
	
}
