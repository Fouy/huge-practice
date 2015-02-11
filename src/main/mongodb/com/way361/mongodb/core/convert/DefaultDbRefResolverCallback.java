package com.way361.mongodb.core.convert;

import org.springframework.data.mapping.model.SpELExpressionEvaluator;

import com.mongodb.DBObject;
import com.way361.mongodb.core.mapping.MongoPersistentProperty;

class DefaultDbRefResolverCallback implements DbRefResolverCallback {
	
	private final DBObject surroundingObject;
	private final ObjectPath path;
	private final ValueResolver resolver;
	private final SpELExpressionEvaluator evaluator;
	
	/**
	 * Creates a new {@link DefaultDbRefResolverCallback} using the given {@link DBObject}, {@link ObjectPath},
	 * {@link ValueResolver} and {@link SpELExpressionEvaluator}.
	 * 
	 * @param surroundingObject must not be {@literal null}.
	 * @param path must not be {@literal null}.
	 * @param evaluator must not be {@literal null}.
	 * @param resolver must not be {@literal null}.
	 */
	public DefaultDbRefResolverCallback(DBObject surroundingObject, ObjectPath path, SpELExpressionEvaluator evaluator,
			ValueResolver resolver) {

		this.surroundingObject = surroundingObject;
		this.path = path;
		this.resolver = resolver;
		this.evaluator = evaluator;
	}

	/* 
	 * (non-Javadoc)
	 * @see org.springframework.data.mongodb.core.convert.DbRefResolverCallback#resolve(org.springframework.data.mongodb.core.mapping.MongoPersistentProperty)
	 */
	@Override
	public Object resolve(MongoPersistentProperty property) {
		return resolver.getValueInternal(property, surroundingObject, evaluator, path);
	}

}
