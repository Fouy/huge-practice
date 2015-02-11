package com.way361.mongodb.core;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.data.mapping.model.BasicPersistentEntity;
import org.springframework.data.util.TypeInformation;

import com.way361.mongodb.core.mapping.MongoPersistentEntity;
import com.way361.mongodb.core.mapping.MongoPersistentProperty;

/**
 * MongoDB specific MongoPersistentEntity implementation that adds Mongo
 * specific meta-data such as the collection name and the like.
 * 
 * @author xuefeihu
 *
 */
public class BasicMongoPersistentEntity<T> extends BasicPersistentEntity<T, MongoPersistentProperty> implements
	MongoPersistentEntity<T>, ApplicationContextAware {

	public BasicMongoPersistentEntity(TypeInformation<T> information) {
		super(information);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getCollection() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getLanguage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MongoPersistentProperty getTextScoreProperty() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasTextScoreProperty() {
		// TODO Auto-generated method stub
		return false;
	}

}
