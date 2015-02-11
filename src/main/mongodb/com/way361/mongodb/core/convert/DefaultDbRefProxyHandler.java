package com.way361.mongodb.core.convert;

import org.springframework.data.mapping.context.MappingContext;
import org.springframework.data.mapping.model.BeanWrapper;
import org.springframework.data.mapping.model.DefaultSpELExpressionEvaluator;
import org.springframework.data.mapping.model.SpELContext;
import org.springframework.data.mapping.model.SpELExpressionEvaluator;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.DBRef;
import com.way361.mongodb.core.mapping.MongoPersistentEntity;
import com.way361.mongodb.core.mapping.MongoPersistentProperty;

class DefaultDbRefProxyHandler implements DbRefProxyHandler {

	private final SpELContext spELContext;
	private final MappingContext<? extends MongoPersistentEntity<?>, MongoPersistentProperty> mappingContext;
	private final ValueResolver resolver;

	/**
	 * @param spELContext must not be {@literal null}.
	 * @param conversionService must not be {@literal null}.
	 * @param mappingContext must not be {@literal null}.
	 */
	public DefaultDbRefProxyHandler(SpELContext spELContext,
			MappingContext<? extends MongoPersistentEntity<?>, MongoPersistentProperty> mappingContext, ValueResolver resolver) {

		this.spELContext = spELContext;
		this.mappingContext = mappingContext;
		this.resolver = resolver;
	}

	/* 
	 * (non-Javadoc)
	 * @see org.springframework.data.mongodb.core.convert.DbRefProxyHandler#populateId(com.mongodb.DBRef, java.lang.Object)
	 */
	@Override
	public Object populateId(MongoPersistentProperty property, DBRef source, Object proxy) {

		if (source == null) {
			return proxy;
		}

		MongoPersistentEntity<?> persistentEntity = mappingContext.getPersistentEntity(property);
		MongoPersistentProperty idProperty = persistentEntity.getIdProperty();
		
		if(idProperty.usePropertyAccess()) {
			return proxy;
		}
		
		SpELExpressionEvaluator evaluator = new DefaultSpELExpressionEvaluator(proxy, spELContext);
		BeanWrapper<Object> proxyWrapper = BeanWrapper.create(proxy, null);
		
		DBObject object = new BasicDBObject(idProperty.getFieldName(), source.getId());
		ObjectPath objectPath = ObjectPath.ROOT.push(proxy, persistentEntity, null);
		proxyWrapper.setProperty(idProperty, resolver.getValueInternal(idProperty, object, evaluator, objectPath));

		return proxy;
	}
}
