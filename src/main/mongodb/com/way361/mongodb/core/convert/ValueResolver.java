package com.way361.mongodb.core.convert;

import org.springframework.data.mapping.model.SpELExpressionEvaluator;

import com.mongodb.DBObject;
import com.way361.mongodb.core.mapping.MongoPersistentProperty;

/**
 * Internal API to trigger the resolution of properties.
 * @author xuefeihu
 *
 */
interface ValueResolver {
	
	/**
	 * Resolves the value for the given {@link MongoPersistentProperty} within the given {@link DBObject} using the given
	 * {@link SpELExpressionEvaluator} and {@link ObjectPath}.
	 * 
	 * @param prop
	 * @param dbo
	 * @param evaluator
	 * @param parent
	 * @return
	 */
	Object getValueInternal(MongoPersistentProperty prop, DBObject dbo, SpELExpressionEvaluator evaluator,
			ObjectPath parent);
}
