package com.way361.mongodb.core.index;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.util.Assert;


/**
 * An implementation of ApplicationEventPublisher that will only fire
 * MappingContextEvents for use by the index creator when MongoTemplate is used
 * 'stand-alone', that is not declared inside a Spring ApplicationContext.
 * Declare MongoTemplate inside an ApplicationContext to enable the publishing
 * of all persistence events such as AfterLoadEvent, AfterSaveEvent, etc.
 * 
 * @author xuefeihu
 *
 */
public class MongoMappingEventPublisher implements ApplicationEventPublisher {
	
	private final MongoPersistentEntityIndexCreator indexCreator;

	/**
	 * Creates a new MongoMappingEventPublisher for the given MongoPersistentEntityIndexCreator.
	 * @param indexCreator
	 */
	public MongoMappingEventPublisher(MongoPersistentEntityIndexCreator indexCreator){
		
		Assert.notNull(indexCreator);
		this.indexCreator = indexCreator;
	}
	
	@Override
	public void publishEvent(ApplicationEvent event) {
		// TODO Auto-generated method stub
		
	}

}
