package com.way361.mongodb.core.mapping;

import org.springframework.data.mapping.PersistentEntity;

/**
 * MongoDB specific PersistentEntity abstraction.
 * @author xuefeihu
 *
 * @param <T>
 */
public interface MongoPersistentEntity<T> extends PersistentEntity<T, MongoPersistentProperty> {

	/**
	 * Returns the collection the entity shall be persisted to.
	 * @return
	 */
	String getCollection();
	
	/**
	 * Returns the default language to be used for this entity.
	 * @return
	 */
	String getLanguage();
	
	/**
	 * Returns the property holding text score value.
	 * @return
	 */
	MongoPersistentProperty getTextScoreProperty();
	
	/**
	 * Returns whether the entity has a TextScore property.
	 * @return
	 */
	boolean hasTextScoreProperty();
	
}
