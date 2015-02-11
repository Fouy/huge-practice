package com.way361.mongodb.core.convert;

/**
 * A subclass of QueryMapper that retains type information on the mongo types.
 * @author xuefeihu
 *
 */
public class UpdateMapper extends QueryMapper {
	
	private final MongoConverter converter;

	/**
	 * Creates a new UpdateMapper using the given MongoConverter.
	 * @param converter
	 */
	public UpdateMapper(MongoConverter converter) {
		super(converter);
		this.converter = converter;
	}

}
