package com.way361.mongodb.core;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.core.io.ResourceLoader;
import org.springframework.dao.support.PersistenceExceptionTranslator;
import org.springframework.data.authentication.UserCredentials;
import org.springframework.data.mapping.context.MappingContext;
import org.springframework.util.Assert;

import com.mongodb.CommandResult;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.ReadPreference;
import com.mongodb.WriteConcern;
import com.mongodb.WriteResult;
import com.way361.mongodb.MongoDbFactory;
import com.way361.mongodb.core.aggregation.Aggregation;
import com.way361.mongodb.core.aggregation.AggregationResults;
import com.way361.mongodb.core.aggregation.TypedAggregation;
import com.way361.mongodb.core.convert.DbRefResolver;
import com.way361.mongodb.core.convert.DefaultDbRefResolver;
import com.way361.mongodb.core.convert.MappingMongoConverter;
import com.way361.mongodb.core.convert.MongoConverter;
import com.way361.mongodb.core.convert.QueryMapper;
import com.way361.mongodb.core.convert.UpdateMapper;
import com.way361.mongodb.core.index.MongoMappingEventPublisher;
import com.way361.mongodb.core.index.MongoPersistentEntityIndexCreator;
import com.way361.mongodb.core.mapping.MongoMappingContext;
import com.way361.mongodb.core.mapping.MongoPersistentEntity;
import com.way361.mongodb.core.mapping.MongoPersistentProperty;
import com.way361.mongodb.core.mapreduce.GroupBy;
import com.way361.mongodb.core.mapreduce.GroupByResults;
import com.way361.mongodb.core.mapreduce.MapReduceOptions;
import com.way361.mongodb.core.mapreduce.MapReduceResults;
import com.way361.mongodb.core.query.Criteria;
import com.way361.mongodb.core.query.Query;
import com.way361.mongodb.core.query.Update;

/**
 * Primary implementation of {@link MongoOperations}.
 * @author xuefeihu
 *
 */
public class MongoTemplate implements MongoOperations {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MongoTemplate.class);
	private static final String ID_FIELD = "_id";
	private static final WriteResultChecking DEFAULT_WRITE_RESULT_CHECKING = WriteResultChecking.NONE;
	private static final Collection<String> ITERABLE_CLASSES;
	
	static{
		Set<String> iterableClasses = new HashSet<String>();
		iterableClasses.add(List.class.getName());
		iterableClasses.add(Collection.class.getName());
		iterableClasses.add(Iterator.class.getName());
		
		ITERABLE_CLASSES = Collections.unmodifiableCollection(iterableClasses);//change to "read only"
	}
	
	private final MongoConverter mongoConverter;
	private final MappingContext<? extends MongoPersistentEntity<?>, MongoPersistentProperty> mappingContext;
	private final MongoDbFactory mongoDbFactory;
	private final PersistenceExceptionTranslator exceptionTranslator;
	private final QueryMapper queryMapper;
	private final UpdateMapper updateMapper;
	
	private WriteConcern writeConcern;
	private WriteConcernResolver writeConcernResolver = DefaultWriteConcernResolver.INSTANCE;
	private WriteResultChecking writeConcernChecking = WriteResultChecking.NONE;
	private ReadPreference readPreference;
	private ApplicationEventPublisher eventPublisher;
	private ResourceLoader resourceLoader;
	private MongoPersistentEntityIndexCreator indexCreator;
	
	
	public MongoTemplate(Mongo mongo, String databaseName){
		this(new SimpleMongoDbFactory(mongo, databaseName), null);
	}
	
	public MongoTemplate(Mongo mongo, String databaseName, UserCredentials userCredentials){
		this(new SimpleMongoDbFactory(mongo, databaseName, userCredentials));
	}
	
	public MongoTemplate(MongoDbFactory mongoDbFactory){
		this(mongoDbFactory, null);
	}
	
	public MongoTemplate(MongoDbFactory mongoDbFactory, MongoConverter mongoConverter){
		Assert.notNull(mongoDbFactory);
		
		this.mongoDbFactory = mongoDbFactory;
		this.exceptionTranslator = mongoDbFactory.getExceptionTranslator();
		this.mongoConverter = mongoConverter == null ? getDefaultMongoConverter(mongoDbFactory) : mongoConverter;
		this.queryMapper = new QueryMapper(this.mongoConverter);
		this.updateMapper = new UpdateMapper(this.mongoConverter);
		
		// We always have a mapping context in the converter, whether it's a simple one or not
		mappingContext = this.mongoConverter.getMappingContext();
		// We create indexes based on mapping events
		if (null != mappingContext && mappingContext instanceof MongoMappingContext){
			indexCreator = new MongoPersistentEntityIndexCreator((MongoMappingContext) mappingContext, mongoDbFactory);
			eventPublisher = new MongoMappingEventPublisher(indexCreator);
			if(mappingContext instanceof ApplicationEventPublisherAware) {
				((ApplicationEventPublisherAware) mappingContext).setApplicationEventPublisher(eventPublisher);
			}
		}
	}
	

	@Override
	public String getCollName(Class<?> entityClass) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommandResult executeCommand(String jsonCommand) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommandResult executeCommand(DBObject command) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommandResult executeCommand(DBObject command, int options) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void executeQuery(Query query, String collName,
			DocumentCallbackHandler handler) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <T> T execute(DbCallback<T> action) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T execute(Class<?> entityClass, CollectionCallback<T> action) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T execute(String collName, CollectionCallback<T> action) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T executeInSession(DbCallback<T> action) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> DBCollection createCollection(Class<T> entityClass) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> DBCollection createCollection(Class<T> entityClass,
			CollectionOptions options) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DBCollection createCollection(String collName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DBCollection createCollection(String collName,
			CollectionOptions options) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<String> getCollectionNames() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DBCollection getCollection(String collName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> boolean collectionExists(Class<T> entityClass) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean collectionExists(String collName) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public <T> void dropCollection(Class<T> entityClass) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dropCollection(String collName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public IndexOperations indexOps(String collName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IndexOperations indexOps(Class<?> entityClass) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> List<T> findAll(Class<T> entityClass) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> List<T> findAll(Class<T> entityClass, String collName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> GroupByResults<T> group(String inputCollName, GroupBy groupBy,
			Class<T> entityClass) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> GroupByResults<T> group(Criteria criteria, String inputCollName,
			GroupBy groupBy, Class<T> entityClass) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <O> AggregationResults<O> aggregate(TypedAggregation aggregation,
			String collName, Class<O> outputType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <O> AggregationResults<O> aggregate(TypedAggregation aggregation,
			Class<O> outputType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <O> AggregationResults<O> aggregate(TypedAggregation aggregation,
			Class<?> inputType, Class<O> outputType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <O> AggregationResults<O> aggregate(Aggregation aggregation,
			String collName, Class<O> outputType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> MapReduceResults<T> mapReduce(String inputCollName,
			String mapFunction, String reduceFunction, Class<T> entityClass) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> MapReduceResults<T> mapReduce(String inputCollName,
			String mapFunction, String reduceFunction,
			MapReduceOptions options, Class<T> entityClass) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> MapReduceResults<T> mapReduce(Query query, String inputCollName,
			String mapFunction, String reduceFunction, Class<T> entityClass) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> MapReduceResults<T> mapReduce(Query query, String inputCollName,
			String mapFunction, String reduceFunction,
			MapReduceOptions reduceOptions, Class<T> entityClass) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T findOne(Query query, Class<T> entityClass) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T findOne(Query query, Class<T> entityClass, String collName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean exists(Query query, String collName) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean exists(Query query, Class<?> entityClass) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean exists(Query query, Class<?> entityClass, String collName) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public <T> List<T> find(Query query, Class<T> entityClass) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> List<T> find(Query query, Class<T> entityClass, String collName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T findById(Object id, Class<T> entityClass) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T findById(Object id, Class<T> entityClass, String collName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T findAndModify(Query query, Update update, Class<T> entityClass) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T findAndModify(Query query, Update update,
			Class<T> entityClass, String collName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T findAndModify(Query query, Update update,
			FindAndModifyOptions options, Class<T> entityClass) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T findAndModify(Query query, Update update,
			FindAndModifyOptions options, Class<T> entityClass, String collName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T findAndRemove(Query query, Class<T> entityClass) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T findAndRemove(Query query, Class<T> entityClass,
			String collName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long count(Query query, Class<?> entityClass) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long count(Query query, String collName) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long count(Query query, Class<?> entityClass, String collName) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void insert(Object objectToSave) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insert(Object objectToSave, String collName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insert(Collection<? extends Object> batchTosave,
			Class<?> entityClass) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insert(Collection<? extends Object> batchTosave, String collName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insertAll(Collection<? extends Object> batchTosave) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void save(Object objectToSave) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void save(Object objectToSave, String collName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public WriteResult upsert(Query query, Update update, Class<?> entityClass) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public WriteResult upsert(Query query, Update update, String collName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public WriteResult upsert(Query query, Update update, Class<?> entityClass,
			String collName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public WriteResult updateFirst(Query query, Update update,
			Class<?> entityClass) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public WriteResult updateFirst(Query query, Update update, String collName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public WriteResult updateFirst(Query query, Update update,
			Class<?> entityClass, String collName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public WriteResult updateMulti(Query query, Update update,
			Class<?> entityClass) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public WriteResult updateMulti(Query query, Update update, String collName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public WriteResult updateMulti(Query query, Update update,
			Class<?> entityClass, String collName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public WriteResult remove(Object object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public WriteResult remove(Object object, String collName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public WriteResult remove(Query query, Class<?> entityClass) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public WriteResult remove(Query query, Class<?> entityClass, String collName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public WriteResult remove(Query query, String collName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> List<T> findAllAndRemove(Query query, String collName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> List<T> findAllAndRemove(Query query, Class<T> entityClass) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> List<T> findAllAndRemove(Query query, Class<T> entityClass,
			String collName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MongoConverter getConverter() {
		// TODO Auto-generated method stub
		return null;
	}

	
	private enum DefaultWriteConcernResolver implements WriteConcernResolver {
		
		INSTANCE;
		
		public WriteConcern resolve(MongoAction action) {
			return action.getDefaultWriteConcern();
		}
	}
	
	private static final MongoConverter getDefaultMongoConverter(MongoDbFactory factory){
		
		DbRefResolver dbRefResolver = new DefaultDbRefResolver(factory);
		MappingMongoConverter converter = new MappingMongoConverter(dbRefResolver, new MongoMappingContext());
		converter.afterPropertiesSet();
		return converter;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
