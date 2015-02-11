package com.way361.mongodb.core.query;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.core.convert.converter.Converter;

import com.mongodb.DBObject;
import com.mongodb.util.JSON;

/**
 * Utility methods for JSON serialization.
 * @author xuefeihu
 *
 */
public abstract class SerializationUtils {

	private SerializationUtils() {
	}

	/**
	 * Serializes the given object into pseudo-JSON meaning it's trying to create a JSON representation as far as possible
	 * but falling back to the given object's {@link Object#toString()} method if it's not serializable. Useful for
	 * printing raw {@link DBObject}s containing complex values before actually converting them into Mongo native types.
	 * 
	 * @param value
	 * @return
	 */
	public static String serializeToJsonSafely(Object value) {

		if (value == null) {
			return null;
		}

		try {
			return JSON.serialize(value);
		} catch (Exception e) {
			if (value instanceof Collection) {
				return toString((Collection<?>) value);
			} else if (value instanceof Map) {
				return toString((Map<?, ?>) value);
			} else if (value instanceof DBObject) {
				return toString(((DBObject) value).toMap());
			} else {
				return String.format("{ $java : %s }", value.toString());
			}
		}
	}

	private static String toString(Map<?, ?> source) {
		return iterableToDelimitedString(source.entrySet(), "{ ", " }", new Converter<Entry<?, ?>, Object>() {
			public Object convert(Entry<?, ?> source) {
				return String.format("\"%s\" : %s", source.getKey(), serializeToJsonSafely(source.getValue()));
			}
		});
	}

	private static String toString(Collection<?> source) {
		return iterableToDelimitedString(source, "[ ", " ]", new Converter<Object, Object>() {
			public Object convert(Object source) {
				return serializeToJsonSafely(source);
			}
		});
	}

	/**
	 * Creates a string representation from the given {@link Iterable} prepending the postfix, applying the given
	 * {@link Converter} to each element before adding it to the result {@link String}, concatenating each element with
	 * {@literal ,} and applying the postfix.
	 * 
	 * @param source
	 * @param prefix
	 * @param postfix
	 * @param transformer
	 * @return
	 */
	private static <T> String iterableToDelimitedString(Iterable<T> source, String prefix, String postfix,
			Converter<? super T, Object> transformer) {

		StringBuilder builder = new StringBuilder(prefix);
		Iterator<T> iterator = source.iterator();

		while (iterator.hasNext()) {
			builder.append(transformer.convert(iterator.next()));
			if (iterator.hasNext()) {
				builder.append(", ");
			}
		}

		return builder.append(postfix).toString();
	}
}
