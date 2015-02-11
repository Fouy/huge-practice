package com.way361.mongodb.core.index;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import com.way361.mongodb.core.mapping.MongoMappingContext;

/**
 * IndexResolver finds those IndexDefinitions to be created for a given class.
 * @author xuefeihu
 *
 */
public class MongoPersistentEntityIndexResolver implements IndexResolver {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MongoPersistentEntityIndexResolver.class);
	
	private final MongoMappingContext mappingContext;
	
	/**
	 * Create new MongoPersistentEntityIndexResolver.
	 * @param mappingContext
	 */
	public MongoPersistentEntityIndexResolver(MongoMappingContext mappingContext){
		Assert.notNull(mappingContext, "Mapping context must not be null in order to resolve index definitions");
		this.mappingContext = mappingContext;
	}

	@Override
	public Iterable<? extends IndexDefinitionHolder> resolveIndexForClass(
			Class<?> type) {
		// TODO Auto-generated method stub
		return null;
	}

}
