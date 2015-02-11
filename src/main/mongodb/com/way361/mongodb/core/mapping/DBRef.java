package com.way361.mongodb.core.mapping;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.data.annotation.Reference;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
@Reference
public @interface DBRef {
	
	/**
	 * The database the referred entity resides in.
	 * @return
	 */
	String db() default "";
	
	/**
	 * Controls whether the referenced entity should be loaded lazily. This defaults to false.
	 * @return
	 */
	boolean lazy() default false;
	
}
