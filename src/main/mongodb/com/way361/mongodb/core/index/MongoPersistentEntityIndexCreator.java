package com.way361.mongodb.core.index;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.data.mapping.context.MappingContextEvent;
import org.springframework.util.Assert;

import com.way361.mongodb.MongoDbFactory;
import com.way361.mongodb.core.mapping.MongoMappingContext;
import com.way361.mongodb.core.mapping.MongoPersistentEntity;
import com.way361.mongodb.core.mapping.MongoPersistentProperty;

/**
 * Component that inspects MongoPersistentEntity instances contained in the
 * given MongoMappingContext for indexing metadata and ensures the indexes to be
 * available.
 * 
 * @author xuefeihu
 *
 */
public class MongoPersistentEntityIndexCreator
		implements
		ApplicationListener<MappingContextEvent<MongoPersistentEntity<?>, MongoPersistentProperty>> {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MongoPersistentEntityIndexCreator.class);
	
	private final Map<Class<?>, Boolean> classesSeen = new ConcurrentHashMap<Class<?>, Boolean>();
	private final MongoDbFactory mongoDbFactory;
	private final MongoMappingContext mappingContext;
	private final IndexResolver indexResolver;

	public MongoPersistentEntityIndexCreator(MongoMappingContext mappingContext, MongoDbFactory mongoDbFactory){
		this(mappingContext, mongoDbFactory, new MongoPersistentEntityIndexResolver(mappingContext));
	}
	
	public MongoPersistentEntityIndexCreator(MongoMappingContext mappingContext, MongoDbFactory mongoDbFactory,
			IndexResolver indexResolver){
		
		Assert.notNull(mongoDbFactory);
		Assert.notNull(mappingContext);
		Assert.notNull(indexResolver);
		
		this.mongoDbFactory = mongoDbFactory;
		this.mappingContext = mappingContext;
		this.indexResolver = indexResolver;
		
		for(MongoPersistentEntity<?> entity : mappingContext.getPersistentEntities()){
			checkForIndexes(entity);
		}
		
		
	}
	
	private void checkForIndexes(MongoPersistentEntity<?> entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onApplicationEvent(
			MappingContextEvent<MongoPersistentEntity<?>, MongoPersistentProperty> event) {
		// TODO Auto-generated method stub

	}

}
