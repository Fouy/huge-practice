package com.way361.mongodb.core.convert;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;

import org.bson.types.ObjectId;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.util.StringUtils;

import com.mongodb.DBObject;
import com.way361.mongodb.core.query.Term;

/**
 * Wrapper class to contain useful converters for the usage with Mongo.
 * @author xuefeihu
 *
 */
abstract class MongoConverters {

	/**
	 * Private constructor to prevent instantiation.
	 */
	private MongoConverters() {}
	
	/**
	 * Simple singleton to convert ObjectIds to their String representation.
	 * @author xuefeihu
	 *
	 */
	public static enum ObjectIdToStringConverter implements Converter<ObjectId, String> {
		INSTANCE;

		public String convert(ObjectId id) {
			return id == null ? null : id.toString();
		}
	}
	
	/**
	 * Simple singleton to convert Strings to their ObjectId representation.
	 * @author xuefeihu
	 *
	 */
	public static enum StringToObjectIdConverter implements Converter<String, ObjectId> {
		INSTANCE;

		public ObjectId convert(String source) {
			return StringUtils.hasText(source) ? new ObjectId(source) : null;
		}
	}
	
	/**
	 * Simple singleton to convert ObjectIds to their java.math.BigInteger representation.
	 * @author xuefeihu
	 *
	 */
	public static enum ObjectIdToBigIntegerConverter implements Converter<ObjectId, BigInteger> {
		INSTANCE;

		public BigInteger convert(ObjectId source) {
			return source == null ? null : new BigInteger(source.toString(), 16);
		}
	}
	
	/**
	 * Simple singleton to convert BigIntegers to their ObjectId representation.
	 * @author xuefeihu
	 *
	 */
	public static enum BigIntegerToObjectIdConverter implements Converter<BigInteger, ObjectId> {
		INSTANCE;

		public ObjectId convert(BigInteger source) {
			return source == null ? null : new ObjectId(source.toString(16));
		}
	}
	
	public static enum BigDecimalToStringConverter implements Converter<BigDecimal, String> {
		INSTANCE;

		public String convert(BigDecimal source) {
			return source == null ? null : source.toString();
		}
	}
	
	public static enum StringToBigDecimalConverter implements Converter<String, BigDecimal> {
		INSTANCE;

		public BigDecimal convert(String source) {
			return StringUtils.hasText(source) ? new BigDecimal(source) : null;
		}
	}

	public static enum BigIntegerToStringConverter implements Converter<BigInteger, String> {
		INSTANCE;

		public String convert(BigInteger source) {
			return source == null ? null : source.toString();
		}
	}
	
	public static enum StringToBigIntegerConverter implements Converter<String, BigInteger> {
		INSTANCE;

		public BigInteger convert(String source) {
			return StringUtils.hasText(source) ? new BigInteger(source) : null;
		}
	}

	public static enum URLToStringConverter implements Converter<URL, String> {
		INSTANCE;

		public String convert(URL source) {
			return source == null ? null : source.toString();
		}
	}
	
	public static enum StringToURLConverter implements Converter<String, URL> {
		INSTANCE;

		private static final TypeDescriptor SOURCE = TypeDescriptor.valueOf(String.class);
		private static final TypeDescriptor TARGET = TypeDescriptor.valueOf(URL.class);

		public URL convert(String source) {

			try {
				return source == null ? null : new URL(source);
			} catch (MalformedURLException e) {
				throw new ConversionFailedException(SOURCE, TARGET, source, e);
			}
		}
	}
	
	@ReadingConverter
	public static enum DBObjectToStringConverter implements Converter<DBObject, String> {

		INSTANCE;

		@Override
		public String convert(DBObject source) {
			return source == null ? null : source.toString();
		}
	}
	
	@WritingConverter
	public static enum TermToStringConverter implements Converter<Term, String> {

		INSTANCE;

		@Override
		public String convert(Term source) {
			return source == null ? null : source.getFormatted();
		}
	}
}
