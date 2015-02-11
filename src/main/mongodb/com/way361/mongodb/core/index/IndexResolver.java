package com.way361.mongodb.core.index;

/**
 * IndexResolver finds those IndexDefinitions to be created for a given class.
 * 
 * @author xuefeihu
 *
 */
interface IndexResolver {

	/**
	 * Find and create IndexDefinitions for properties of given type.
	 * IndexDefinitions are created for properties and types with Indexed,
	 * CompoundIndexes or GeoSpatialIndexed.
	 * 
	 * @param type
	 * @return
	 */
	Iterable<? extends IndexDefinitionHolder> resolveIndexForClass(Class<?> type);

}
