package com.way361.mongodb.core.mapping;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.data.mapping.context.AbstractMappingContext;
import org.springframework.data.mapping.model.FieldNamingStrategy;
import org.springframework.data.mapping.model.PropertyNameFieldNamingStrategy;
import org.springframework.data.mapping.model.SimpleTypeHolder;
import org.springframework.data.util.TypeInformation;

import com.way361.mongodb.core.BasicMongoPersistentEntity;

/**
 * Default implementation of a MappingContext for MongoDB using
 * BasicMongoPersistentEntity and BasicMongoPersistentProperty as primary
 * abstractions.
 * 
 * @author xuefeihu
 *
 */
public class MongoMappingContext
		extends
		AbstractMappingContext<BasicMongoPersistentEntity<?>, MongoPersistentProperty>
		implements ApplicationContextAware {

	private static final FieldNamingStrategy DEFAULT_NAMING_STRATEGY = PropertyNameFieldNamingStrategy.INSTANCE;

	private FieldNamingStrategy fieldNamingStrategy = DEFAULT_NAMING_STRATEGY;
	private ApplicationContext context;

	/**
	 * Creates a new MongoMappingContext.
	 */
	public MongoMappingContext() {
		setSimpleTypeHolder(MongoSimpleTypes.HOLDER);
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected <T> BasicMongoPersistentEntity<?> createPersistentEntity(
			TypeInformation<T> typeInformation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected MongoPersistentProperty createPersistentProperty(Field field,
			PropertyDescriptor descriptor, BasicMongoPersistentEntity<?> owner,
			SimpleTypeHolder simpleTypeHolder) {
		// TODO Auto-generated method stub
		return null;
	}


}
