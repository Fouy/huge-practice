package com.way361.mongodb.core;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import com.mongodb.CommandResult;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.WriteResult;
import com.way361.mongodb.core.aggregation.Aggregation;
import com.way361.mongodb.core.aggregation.AggregationResults;
import com.way361.mongodb.core.aggregation.TypedAggregation;
import com.way361.mongodb.core.convert.MongoConverter;
import com.way361.mongodb.core.mapreduce.GroupBy;
import com.way361.mongodb.core.mapreduce.GroupByResults;
import com.way361.mongodb.core.mapreduce.MapReduceOptions;
import com.way361.mongodb.core.mapreduce.MapReduceResults;
import com.way361.mongodb.core.query.Criteria;
import com.way361.mongodb.core.query.Query;
import com.way361.mongodb.core.query.Update;

/**
 * Interface that specifies a basic set of MongoDB operations. Implemented by {@link MongoTemplate}}. 
 * Not often used but a useful option for extensibility and testability (as it can be easily
 *  mocked, stubbed, or be the target of a JDK proxy).
 * @author xuefeihu
 *
 */
public interface MongoOperations {
	
	/**
	 * get the collection name for the class by this template.
	 * @param entityClass
	 */
	String getCollName(Class<?> entityClass);
	
	/**
	 * Execute the a MongoDB command expressed as a JSON string. 
	 * @param jsonCommand a MongoDB command
	 */
	CommandResult executeCommand(String jsonCommand);
	
	/**
	 * Execute a MongoDB command.
	 * @param command a MongoDB command
	 */
	CommandResult executeCommand(DBObject command);
	
	/**
	 * Execute a MongoDB command.
	 * @param command a MongoDB command.
	 * @param options query options to use
	 */
	CommandResult executeCommand(DBObject command, int options);
	
	/**
	 * Execute a MongoDB query and iterate over the query results on a per-document basis with a DocumentCallbackHandler.
	 * @param query the query class that specifies the criteria used to find a record and also an optional fields specification
	 * @param collName name of the collection to retrieve the objects from
	 * @param handler the handler that will extract results, one document at a time
	 */
	void executeQuery(Query query, String collName, DocumentCallbackHandler handler);
	
	/**
	 * Executes a DbCallback translating any exceptions as necessary. 
	 * @param action callback object that specifies the MongoDB actions to perform on the passed in DB instance.
	 */
	<T> T execute(DbCallback<T> action);
	
	/**
	 * Executes the given {@link CollectionCallback} on the entity collection of the specified class.
	 * @param entityClass class that determines the collection to use
	 * @param action callback object that specifies the MongoDB action
	 * @return a result object returned by the action or <tt>null</tt>
	 */
	<T> T execute(Class<?> entityClass, CollectionCallback<T> action);
	
	/**
	 * Executes the given CollectionCallback on the collection of the given name. 
	 * @param collName the name of the collection that specifies which DBCollection instance will be passed into
	 * @param action callback object that specifies the MongoDB action the callback action.
	 * @return a result object returned by the action or <tt>null</tt>
	 */
	<T> T execute(String collName, CollectionCallback<T> action);
	
	/**
	 * Executes the given DbCallback within the same connection to the database so as to ensure consistency in a write
	 *  heavy environment where you may read the data that you wrote.
	 * @param action
	 * @return
	 */
	<T> T executeInSession(DbCallback<T> action);
	
	/**
	 * Create an uncapped collection with a name based on the provided entity class.
	 * @param entityClass
	 * @return
	 */
	<T> DBCollection createCollection(Class<T> entityClass);
	
	/**
	 *  Create a collect with a name based on the provided entity class using the options.
	 * @param entityClass
	 * @param options
	 * @return
	 */
	<T> DBCollection createCollection(Class<T> entityClass, CollectionOptions options);
	
	/**
	 * Create an uncapped collection with the provided name.
	 * @param collName
	 * @return
	 */
	DBCollection createCollection(String collName);
	
	/**
	 * Create a collect with the provided name and options.
	 * @param collName
	 * @param options
	 * @return
	 */
	DBCollection createCollection(String collName, CollectionOptions options);
	
	/**
	 * A set of collection names.
	 * @return
	 */
	Set<String> getCollectionNames();
	
	/**
	 * Translate any exceptions as necessary.
	 * @param collName
	 * @return
	 */
	DBCollection getCollection(String collName);
	
	/**
	 * Check to see if a collection with a name indicated by the entity class exists.
	 * @param entityClass
	 * @return
	 */
	<T> boolean collectionExists(Class<T> entityClass);
	
	/**
	 * Check to see if a collection with a given name exists.
	 * @param collName
	 * @return
	 */
	boolean collectionExists(String collName);
	
	/**
	 * Drop the collection with the name indicated by the entity class.
	 * @param entityClass
	 */
	<T> void dropCollection(Class<T> entityClass);
	
	/**
	 * Drop the collection with the given name.
	 * @param collName
	 */
	void dropCollection(String collName);
	
	/**
	 * Returns the operations that can be performed on indexes
	 * @return
	 */
	IndexOperations indexOps(String collName);
	
	/**
	 * Returns the operations that can be performed on indexes
	 * @param entityClass
	 * @return
	 */
	IndexOperations indexOps(Class<?> entityClass);
	
	/**
	 * Query for a list of objects of type T from the collection used by the entity class.
	 * @param entityClass the parameterized type of the returned list
	 * @return the converted collection
	 */
	<T> List<T> findAll(Class<T> entityClass);
	
	/**
	 * Query for a list of objects of type T from the specified collection.
	 * @param entityClass
	 * @param collName
	 * @return
	 */
	<T> List<T> findAll(Class<T> entityClass, String collName);
	
	/**
	 * Execute a group operation over the entire collection. The group operation entity class should match the 'shape' of
	 * the returned object that takes int account the initial document structure as well as any finalize functions.
	 * @param inputCollName
	 * @param groupBy
	 * @param entityClass
	 * @return
	 */
	<T> GroupByResults<T> group(String inputCollName, GroupBy groupBy, Class<T> entityClass);
	
	/**
	 * Execute a group operation restricting the rows to those which match the provided Criteria. The group operation
	 * entity class should match the 'shape' of the returned object that takes int account the initial document structure
	 * as well as any finalize functions.
	 * @param criteria
	 * @param inputCollName
	 * @param groupBy
	 * @param entityClass
	 * @return
	 */
	<T> GroupByResults<T> group(Criteria criteria, String inputCollName, GroupBy groupBy, Class<T> entityClass);
	
	/**
	 * Execute an aggregation operation. The raw results will be mapped to the given entity class. The name of the
	 * inputCollection is derived from the inputType of the aggregation.
	 * @param aggregation
	 * @param collName
	 * @param outputType
	 * @return
	 */
	<O> AggregationResults<O> aggregate(TypedAggregation aggregation, String collName, Class<O> outputType);
	
	/**
	 * Execute an aggregation operation. The raw results will be mapped to the given entity class. The name of the
	 * inputCollection is derived from the inputType of the aggregation.
	 * @param aggregation
	 * @param outputType
	 * @return
	 */
	<O> AggregationResults<O> aggregate(TypedAggregation aggregation, Class<O> outputType);
	
	/**
	 * Execute an aggregation operation. The raw results will be mapped to the given entity class.
	 * @param aggregation
	 * @param inputType
	 * @param outputType
	 * @return
	 */
	<O> AggregationResults<O> aggregate(TypedAggregation aggregation, Class<?> inputType, Class<O> outputType);
	
	/**
	 * Execute an aggregation operation. The raw results will be mapped to the given entity class.
	 * @param aggregation
	 * @param collName
	 * @param outputType
	 * @return
	 */
	<O> AggregationResults<O> aggregate(Aggregation aggregation, String collName, Class<O> outputType);
	
	/**
	 * Execute a map-reduce operation. The map-reduce operation will be formed with an output type of INLINE
	 * @param inputCollName the collection where the map-reduce will read from
	 * @param mapFunction The JavaScript map function
	 * @param reduceFunction The JavaScript reduce function
	 * @param entityClass The parameterized type of the returned list
	 * @return The results of the map reduce operation
	 */
	<T> MapReduceResults<T> mapReduce(String inputCollName, String mapFunction, String reduceFunction, Class<T> entityClass);
	
	/**
	 * Execute a map-reduce operation that takes additional map-reduce options.
	 * @param inputCollName
	 * @param mapFunction
	 * @param reduceFunction
	 * @param options
	 * @param entityClass
	 * @return
	 */
	<T> MapReduceResults<T> mapReduce(String inputCollName, String mapFunction, String reduceFunction,
			MapReduceOptions options, Class<T> entityClass);
	
	/**
	 * Execute a map-reduce operation that takes a query. The map-reduce operation will be formed with an output type of
	 * INLINE
	 * @param query
	 * @param inputCollName
	 * @param mapFunction
	 * @param reduceFunction
	 * @param entityClass
	 * @return
	 */
	<T> MapReduceResults<T> mapReduce(Query query, String inputCollName, String mapFunction,  String reduceFunction,
			Class<T> entityClass);
	
	/**
	 * Execute a map-reduce operation that takes a query and additional map-reduce options
	 * @param query
	 * @param inputCollName
	 * @param mapFunction
	 * @param reduceFunction
	 * @param reduceOptions
	 * @param entityClass
	 * @return
	 */
	<T> MapReduceResults<T> mapReduce(Query query, String inputCollName, String mapFunction,  String reduceFunction,
			MapReduceOptions reduceOptions, Class<T> entityClass);
	
	
	/**
	 * Map the results of an ad-hoc query on the collection for the entity class to a single instance of an object of the
	 * specified type.
	 * @param query
	 * @param entityClass
	 * @return
	 */
	<T> T findOne(Query query, Class<T> entityClass);
	
	/**
	 * Map the results of an ad-hoc query on the specified collection to a single instance of an object of the specified
	 * type.
	 * @param query
	 * @param entityClass
	 * @param collName
	 * @return
	 */
	<T> T findOne(Query query, Class<T> entityClass, String collName);
	
	/**
	 * Determine result of given {@link Query} contains at least one element.
	 * @param query
	 * @param collName
	 * @return
	 */
	boolean exists(Query query, String collName);
	
	/**
	 * Determine result of given {@link Query} contains at least one element.
	 * @param query
	 * @param entityClass
	 * @return
	 */
	boolean exists(Query query, Class<?> entityClass);
	
	/**
	 * Determine result of given {@link Query} contains at least one element.
	 * @param query
	 * @param entityClass
	 * @param collName
	 * @return
	 */
	boolean exists(Query query, Class<?> entityClass, String collName);
	
	/**
	 * Map the results of an ad-hoc query on the collection for the entity class to a List of the specified type.
	 * @param query
	 * @param entityClass
	 * @return
	 */
	<T> List<T> find(Query query, Class<T> entityClass);
	
	/**
	 * Map the results of an ad-hoc query on the specified collection to a List of the specified type.
	 * @param query
	 * @param entityClass
	 * @param collName
	 * @return
	 */
	<T> List<T> find(Query query, Class<T> entityClass, String collName);
	
	/**
	 * Returns a document with the given id mapped onto the given class. The collection the query is ran against will be
	 * derived from the given target class as well.
	 * @param id
	 * @param entityClass
	 * @return
	 */
	<T> T findById(Object id, Class<T> entityClass);
	
	/**
	 * Returns the document with the given id from the given collection mapped onto the given target class.
	 * @param id
	 * @param entityClass
	 * @param collName
	 * @return
	 */
	<T> T findById(Object id, Class<T> entityClass, String collName);
	
	/**
	 * Triggers findAndModify to apply provided {@link Update} on documents matching {@link Criteria} of given {@link Query}.
	 * @param query
	 * @param update
	 * @param entityClass
	 * @return
	 */
	<T> T findAndModify(Query query, Update update, Class<T> entityClass);
	
	/**
	 * Triggers findAndModify to apply provided {@link Update} on documents matching {@link Criteria} of given {@link Query}.
	 * @param query
	 * @param update
	 * @param entityClass
	 * @param collName
	 * @return
	 */
	<T> T findAndModify(Query query, Update update, Class<T> entityClass, String collName);
	
	/**
	 * Triggers findAndModify to apply provided {@link Update} on documents matching {@link Criteria} of given {@link Query} taking
	 * {@link FindAndModifyOptions} into account.
	 * @param query
	 * @param update
	 * @param options
	 * @param entityClass
	 * @return
	 */
	<T> T findAndModify(Query query, Update update, FindAndModifyOptions options, Class<T> entityClass);
	
	/**
	 * Triggers findAndModify to apply provided {@link Update} on documents matching {@link Criteria} of given {@link Query} taking
	 * {@link FindAndModifyOptions} into account.
	 * @param query
	 * @param update
	 * @param options
	 * @param entityClass
	 * @param collName
	 * @return
	 */
	<T> T findAndModify(Query query, Update update, FindAndModifyOptions options, Class<T> entityClass,
			String collName);
	
	/**
	 * Map the results of an ad-hoc query on the collection for the entity type to a single instance of an object of the
	 * specified type. The first document that matches the query is returned and also removed from the collection in the
	 * database.
	 * @param query
	 * @param entityClass
	 * @return
	 */
	<T> T findAndRemove(Query query, Class<T> entityClass);
	
	/**
	 * Map the results of an ad-hoc query on the specified collection to a single instance of an object of the specified
	 * type. The first document that matches the query is returned and also removed from the collection in the database.
	 * @param query
	 * @param entityClass
	 * @param collName
	 * @return
	 */
	<T> T findAndRemove(Query query, Class<T> entityClass, String collName);
	
	/**
	 * Returns the number of documents for the given {@link Query} by querying the collection of the given entity class.
	 * @param query
	 * @param entityClass
	 * @return
	 */
	long count(Query query, Class<?> entityClass);
	
	/**
	 * Returns the number of documents for the given {@link Query} querying the given collection. The given {@link Query}
	 * must solely consist of document field references as we lack type information to map potential property references
	 * onto document fields. TO make sure the query gets mapped, use {@link #count(Query, Class, String)}.
	 * @param query
	 * @param collName
	 * @return
	 */
	long count(Query query, String collName);
	
	/**
	 * Returns the number of documents for the given {@link Query} by querying the given collection using the given entity
	 * class to map the given {@link Query}.
	 * @param query
	 * @param entityClass
	 * @param collName
	 * @return
	 */
	long count(Query query, Class<?> entityClass, String collName);
	
	/**
	 * Insert the object into the collection for the entity type of the object to save.
	 * @param objectToSave
	 */
	void insert(Object objectToSave);
	
	/**
	 * Insert the object into the specified collection.
	 * @param objectToSave
	 * @param collName
	 */
	void insert(Object objectToSave, String collName);
	
	/**
	 * Insert a Collection of objects into a collection in a single batch write to the database.
	 * @param batchTosave
	 * @param entityClass
	 */
	void insert(Collection<? extends Object> batchTosave, Class<?> entityClass);
	
	/**
	 * Insert a list of objects into the specified collection in a single batch write to the database.
	 * @param batchTosave
	 * @param collName
	 */
	void insert(Collection<? extends Object> batchTosave, String collName);
	
	/**
	 * Insert a mixed Collection of objects into a database collection determining the collection name to use based on the
	 * class.
	 * @param batchTosave
	 */
	void insertAll(Collection<? extends Object> batchTosave);
	
	/**
	 * Save the object to the collection for the entity type of the object to save. This will perform an insert if the
	 * object is not already present, that is an 'upsert'.
	 * @param objectToSave
	 */
	void save(Object objectToSave);
	
	/**
	 * Save the object to the specified collection. This will perform an insert if the object is not already present, that
	 * is an 'upsert'.
	 * @param objectToSave
	 * @param collName
	 */
	void save(Object objectToSave, String collName);
	
	/**
	 * Performs an upsert. If no document is found that matches the query, a new document is created and inserted by
	 * combining the query document and the update document.
	 * @param query
	 * @param update
	 * @param entityClass
	 * @return
	 */
	WriteResult upsert(Query query, Update update, Class<?> entityClass);
	
	/**
	 * Performs an upsert. If no document is found that matches the query, a new document is created and inserted by
	 * combining the query document and the update document.
	 * @param query
	 * @param update
	 * @param collName
	 * @return
	 */
	WriteResult upsert(Query query, Update update, String collName);
	
	/**
	 * Performs an upsert. If no document is found that matches the query, a new document is created and inserted by
	 * combining the query document and the update document.
	 * @param query
	 * @param update
	 * @param entityClass
	 * @param collName
	 * @return
	 */
	WriteResult upsert(Query query, Update update, Class<?> entityClass, String collName);
	
	/**
	 * Updates the first object that is found in the collection of the entity class that matches the query document with
	 * the provided update document.
	 * @param query
	 * @param update
	 * @param entityClass
	 * @return
	 */
	WriteResult updateFirst(Query query, Update update, Class<?> entityClass);
	
	/**
	 * Updates the first object that is found in the specified collection that matches the query document criteria with
	 * the provided updated document.
	 * @param query
	 * @param update
	 * @param collName
	 * @return
	 */
	WriteResult updateFirst(Query query, Update update, String collName);
	
	/**
	 * Updates the first object that is found in the specified collection that matches the query document criteria with
	 * the provided updated document.
	 * @param query
	 * @param update
	 * @param entityClass
	 * @param collName
	 * @return
	 */
	WriteResult updateFirst(Query query, Update update, Class<?> entityClass, String collName);
	
	/**
	 * Updates all objects that are found in the collection for the entity class that matches the query document criteria
	 * with the provided updated document.
	 * @param query
	 * @param update
	 * @param entityClass
	 * @return
	 */
	WriteResult updateMulti(Query query, Update update, Class<?> entityClass);
	
	/**
	 * Updates all objects that are found in the specified collection that matches the query document criteria with the
	 * provided updated document.
	 * @param query
	 * @param update
	 * @param collName
	 * @return
	 */
	WriteResult updateMulti(Query query, Update update, String collName);
	
	/**
	 * Updates all objects that are found in the collection for the entity class that matches the query document criteria
	 * with the provided updated document.
	 * @param query
	 * @param update
	 * @param entityClass
	 * @param collName
	 * @return
	 */
	WriteResult updateMulti(final Query query, final Update update, Class<?> entityClass, String collName);
	
	/**
	 * Remove the given object from the collection by id.
	 * @param object
	 * @return
	 */
	WriteResult remove(Object object);
	
	/**
	 * Removes the given object from the given collection.
	 * @param object
	 * @param collName
	 * @return
	 */
	WriteResult remove(Object object, String collName);
	
	/**
	 * Remove all documents that match the provided query document criteria from the the collection used to store the
	 * entityClass. The Class parameter is also used to help convert the Id of the object if it is present in the query.
	 * @param query
	 * @param entityClass
	 * @return
	 */
	WriteResult remove(Query query, Class<?> entityClass);
	
	/**
	 * Remove all documents that match the provided query document criteria from the the collection used to store the
	 * entityClass. The Class parameter is also used to help convert the Id of the object if it is present in the query.
	 * @param object
	 * @param entityClass
	 * @param collName
	 * @return
	 */
	WriteResult remove(Query query, Class<?> entityClass, String collName);
	
	/**
	 * Remove all documents from the specified collection that match the provided query document criteria. There is no
	 * conversion/mapping done for any criteria using the id field.
	 * @param query
	 * @param collName
	 * @return
	 */
	WriteResult remove(Query query, String collName);
	
	/**
	 * Returns and removes all documents form the specified collection that match the provided query.
	 * @param query
	 * @param collName
	 * @return
	 */
	<T> List<T> findAllAndRemove(Query query, String collName);
	
	/**
	 * Returns and removes all documents matching the given query form the collection used to store the entityClass.
	 * @param query
	 * @param entityClass
	 * @return
	 */
	<T> List<T> findAllAndRemove(Query query, Class<T> entityClass);

	/**
	 * Returns and removes all documents that match the provided query document criteria from the the collection used to
	 * store the entityClass. The Class parameter is also used to help convert the Id of the object if it is present in
	 * the query.
	 * @param query
	 * @param entityClass
	 * @param collName
	 * @return
	 */
	<T> List<T> findAllAndRemove(Query query, Class<T> entityClass, String collName);
	
	/**
	 * Returns the underlying {@link MongoConverter}.
	 * @return
	 */
	MongoConverter getConverter();
	
}

