package com.way361.mongodb.core.convert;

import java.math.BigInteger;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.support.ConversionServiceFactory;
import org.springframework.core.convert.support.GenericConversionService;
import org.springframework.data.convert.EntityInstantiators;

import com.way361.mongodb.core.convert.MongoConverters.BigIntegerToObjectIdConverter;
import com.way361.mongodb.core.convert.MongoConverters.ObjectIdToBigIntegerConverter;
import com.way361.mongodb.core.convert.MongoConverters.ObjectIdToStringConverter;
import com.way361.mongodb.core.convert.MongoConverters.StringToObjectIdConverter;

/**
 * Base class for MongoConverter implementations. Sets up a
 * GenericConversionService and populates basic converters. Allows registering
 * CustomConversions.
 * 
 * @author xuefeihu
 *
 */
public abstract class AbstractMongoConverter implements MongoConverter, InitializingBean {
	
	protected final GenericConversionService conversionService;
	protected CustomConversions conversions = new CustomConversions();
	protected EntityInstantiators instantiators = new EntityInstantiators();
	
	/**
	 * Creates a new AbstractMongoConverter using the given GenericConversionService.
	 * @param conversionService
	 */
	@SuppressWarnings("deprecation")
	public AbstractMongoConverter(GenericConversionService conversionService) {
		this.conversionService = conversionService == null ? ConversionServiceFactory.createDefaultConversionService()
				: conversionService;
	}
	
	/**
	 * Registers the given custom conversions with the converter.
	 * 
	 * @param conversions
	 */
	public void setCustomConversions(CustomConversions conversions) {
		this.conversions = conversions;
	}
	
	/**
	 * Registers {@link EntityInstantiators} to customize entity instantiation.
	 * 
	 * @param instantiators
	 */
	public void setInstantiators(EntityInstantiators instantiators) {
		this.instantiators = instantiators == null ? new EntityInstantiators() : instantiators;
	}
	
	/**
	 * Registers additional converters that will be available when using the {@link ConversionService} directly (e.g. for
	 * id conversion). These converters are not custom conversions as they'd introduce unwanted conversions (e.g.
	 * ObjectId-to-String).
	 */
	private void initializeConverters() {

		if (!conversionService.canConvert(ObjectId.class, String.class)) {
			conversionService.addConverter(ObjectIdToStringConverter.INSTANCE);
		}
		if (!conversionService.canConvert(String.class, ObjectId.class)) {
			conversionService.addConverter(StringToObjectIdConverter.INSTANCE);
		}
		if (!conversionService.canConvert(ObjectId.class, BigInteger.class)) {
			conversionService.addConverter(ObjectIdToBigIntegerConverter.INSTANCE);
		}
		if (!conversionService.canConvert(BigInteger.class, ObjectId.class)) {
			conversionService.addConverter(BigIntegerToObjectIdConverter.INSTANCE);
		}

		conversions.registerConvertersIn(conversionService);
	}
	
	public Object convertToMongoType(Object obj) {
		return convertToMongoType(obj, null);
	}
	
	public ConversionService getConversionService() {
		return conversionService;
	}
	
	public void afterPropertiesSet() {
		initializeConverters();
	}
}
