package com.way361.mongodb.core.convert;

import static org.springframework.util.ReflectionUtils.isObjectMethod;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Method;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.cglib.proxy.Callback;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.Factory;
import org.springframework.cglib.proxy.MethodProxy;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.support.PersistenceExceptionTranslator;
import org.springframework.objenesis.ObjenesisStd;
import org.springframework.util.Assert;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

import com.mongodb.DB;
import com.mongodb.DBRef;
import com.way361.mongodb.LazyLoadingException;
import com.way361.mongodb.MongoDbFactory;
import com.way361.mongodb.core.mapping.MongoPersistentEntity;
import com.way361.mongodb.core.mapping.MongoPersistentProperty;

/**
 * A DbRefResolver that resolves com.way361.mongodb.core.mapping.DBRefs by
 * delegating to a DbRefResolverCallback than is able to generate lazy loading
 * proxies.
 * 
 * @author xuefeihu
 *
 */
public class DefaultDbRefResolver implements DbRefResolver {

	private final MongoDbFactory mongoDbFactory;
	private final PersistenceExceptionTranslator exceptionTranslator;
	private final ObjenesisStd objenesis;

	public DefaultDbRefResolver(MongoDbFactory mongoDbFactory) {

		Assert.notNull(mongoDbFactory,
				"MongoDbFactory translator must not be null!");

		this.mongoDbFactory = mongoDbFactory;
		this.exceptionTranslator = mongoDbFactory.getExceptionTranslator();
		this.objenesis = new ObjenesisStd(true);
	}

	@Override
	public Object resolveDbRef(MongoPersistentProperty property, DBRef dbRef,
			DbRefResolverCallback callback, DbRefProxyHandler proxyHandler) {

		Assert.notNull(property, "Property must not be null!");
		Assert.notNull(callback, "Callback must not be null!");

		if (isLazyDbRef(property)) {
			return createLazyLoadingProxy(property, dbRef, callback,
					proxyHandler);
		}

		return callback.resolve(property);
	}

	@Override
	public DBRef createDbRef(com.way361.mongodb.core.mapping.DBRef annotation,
			MongoPersistentEntity<?> entity, Object id) {

		DB db = mongoDbFactory.getDb();
		db = annotation != null && StringUtils.hasText(annotation.db()) ? mongoDbFactory
				.getDb(annotation.db()) : db;

		return new DBRef(db, entity.getCollection(), id);
	}

	/**
	 * Creates a proxy for the given MongoPersistentProperty using the given
	 * DbRefResolverCallback to eventually resolve the value of the property.
	 * 
	 * @param property
	 * @param dbref
	 * @param callback
	 * @param handler
	 * @return
	 */
	private Object createLazyLoadingProxy(MongoPersistentProperty property,
			DBRef dbref, DbRefResolverCallback callback,
			DbRefProxyHandler handler) {

		Class<?> propertyType = property.getType();
		LazyLoadingInterceptor interceptor = new LazyLoadingInterceptor(
				property, dbref, exceptionTranslator, callback);

		if (!propertyType.isInterface()) {

			Factory factory = (Factory) objenesis
					.newInstance(getEnhancedTypeFor(propertyType));
			factory.setCallbacks(new Callback[] { interceptor });

			return handler.populateId(property, dbref, factory);
		}

		ProxyFactory proxyFactory = new ProxyFactory();

		for (Class<?> type : propertyType.getInterfaces()) {
			proxyFactory.addInterface(type);
		}

		proxyFactory.addInterface(LazyLoadingProxy.class);
		proxyFactory.addInterface(propertyType);
		proxyFactory.addAdvice(interceptor);

		return handler.populateId(property, dbref, proxyFactory.getProxy());
	}

	/**
	 * Returns the CGLib enhanced type for the given source type.
	 * 
	 * @param type
	 * @return
	 */
	private Class<?> getEnhancedTypeFor(Class<?> type) {

		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(type);
		enhancer.setCallbackType(org.springframework.cglib.proxy.MethodInterceptor.class);
		enhancer.setInterfaces(new Class[] { LazyLoadingProxy.class });

		return enhancer.createClass();
	}

	/**
	 * Returns whether the property shall be resolved lazily.
	 * 
	 * @param property
	 *            must not be {@literal null}.
	 * @return
	 */
	private boolean isLazyDbRef(MongoPersistentProperty property) {
		return property.getDBRef() != null && property.getDBRef().lazy();
	}

	/**
	 * A MethodInterceptor that is used within a lazy loading proxy. The
	 * property resolving is delegated to a DbRefResolverCallback. The resolving
	 * process is triggered by a method invocation on the proxy and is
	 * guaranteed to be performed only once.
	 * 
	 * @author xuefeihu
	 *
	 */
	static class LazyLoadingInterceptor implements MethodInterceptor,
			org.springframework.cglib.proxy.MethodInterceptor, Serializable {

		private static final Method INITIALIZE_METHOD, TO_DBREF_METHOD,
				FINALIZE_METHOD;

		private final DbRefResolverCallback callback;
		private final MongoPersistentProperty property;
		private final PersistenceExceptionTranslator exceptionTranslator;

		private volatile boolean resolved;
		private Object result;
		private DBRef dbRef;

		static {
			try {
				INITIALIZE_METHOD = LazyLoadingProxy.class
						.getMethod("getTarget");
				TO_DBREF_METHOD = LazyLoadingProxy.class.getMethod("toDBRef");
				FINALIZE_METHOD = Object.class.getDeclaredMethod("finalize");
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}

		/**
		 * Creates a new LazyLoadingInterceptor for the given
		 * MongoPersistentProperty, PersistenceExceptionTranslator and
		 * DbRefResolverCallback.
		 * 
		 * @param property
		 * @param dbRef
		 * @param exceptionTranslator
		 * @param callback
		 */
		public LazyLoadingInterceptor(MongoPersistentProperty property,
				DBRef dbRef,
				PersistenceExceptionTranslator exceptionTranslator,
				DbRefResolverCallback callback) {

			Assert.notNull(property, "Property must not be null!");
			Assert.notNull(exceptionTranslator,
					"Exception translator must not be null!");
			Assert.notNull(callback, "Callback must not be null!");

			this.dbRef = dbRef;
			this.callback = callback;
			this.exceptionTranslator = exceptionTranslator;
			this.property = property;
		}

		public Object invoke(MethodInvocation invocation) throws Throwable {
			return intercept(invocation.getThis(), invocation.getMethod(),
					invocation.getArguments(), null);
		}

		public Object intercept(Object obj, Method method, Object[] args,
				MethodProxy proxy) throws Throwable {

			if (INITIALIZE_METHOD.equals(method)) {
				return ensureResolved();
			}

			if (TO_DBREF_METHOD.equals(method)) {
				return this.dbRef;
			}

			if (isObjectMethod(method)
					&& Object.class.equals(method.getDeclaringClass())) {

				if (ReflectionUtils.isToStringMethod(method)) {
					return proxyToString(proxy);
				}

				if (ReflectionUtils.isEqualsMethod(method)) {
					return proxyEquals(proxy, args[0]);
				}

				if (ReflectionUtils.isHashCodeMethod(method)) {
					return proxyHashCode(proxy);
				}

				// DATAMONGO-1076 - finalize methods should not trigger proxy
				// initialization
				if (FINALIZE_METHOD.equals(method)) {
					return null;
				}
			}

			Object target = ensureResolved();

			if (target == null) {
				return null;
			}

			return method.invoke(target, args);
		}

		/**
		 * Returns a to string representation for the given proxy.
		 * 
		 * @param proxy
		 * @return
		 */
		private String proxyToString(Object proxy) {

			StringBuilder description = new StringBuilder();
			if (dbRef != null) {
				description.append(dbRef.getRef());
				description.append(":");
				description.append(dbRef.getId());
			} else {
				description.append(System.identityHashCode(proxy));
			}
			description.append("$").append(
					LazyLoadingProxy.class.getSimpleName());

			return description.toString();
		}

		/**
		 * Returns the hashcode for the given {@code proxy}.
		 * 
		 * @param proxy
		 * @return
		 */
		private int proxyHashCode(Object proxy) {
			return proxyToString(proxy).hashCode();
		}

		/**
		 * Performs an equality check for the given {@code proxy}.
		 * 
		 * @param proxy
		 * @param that
		 * @return
		 */
		private boolean proxyEquals(Object proxy, Object that) {

			if (!(that instanceof LazyLoadingProxy)) {
				return false;
			}

			if (that == proxy) {
				return true;
			}

			return proxyToString(proxy).equals(that.toString());
		}

		/**
		 * Will trigger the resolution if the proxy is not resolved already or
		 * return a previously resolved result.
		 * 
		 * @return
		 */
		private Object ensureResolved() {

			if (!resolved) {
				this.result = resolve();
				this.resolved = true;
			}

			return this.result;
		}

		/**
		 * Callback method for serialization.
		 * 
		 * @param out
		 * @throws IOException
		 */
		private void writeObject(ObjectOutputStream out) throws IOException {

			ensureResolved();
			out.writeObject(this.result);
		}

		/**
		 * Callback method for deserialization.
		 * 
		 * @param in
		 * @throws IOException
		 */
		private void readObject(ObjectInputStream in) throws IOException {

			try {
				this.resolved = true;
				this.result = in.readObject();
			} catch (ClassNotFoundException e) {
				throw new LazyLoadingException("Could not deserialize result",
						e);
			}
		}

		/**
		 * Resolves the proxy into its backing object.
		 * 
		 * @return
		 */
		private synchronized Object resolve() {

			if (!resolved) {

				try {

					return callback.resolve(property);

				} catch (RuntimeException ex) {

					DataAccessException translatedException = this.exceptionTranslator
							.translateExceptionIfPossible(ex);
					throw new LazyLoadingException(
							"Unable to lazily resolve DBRef!",
							translatedException);
				}
			}

			return result;
		}
	}
}
