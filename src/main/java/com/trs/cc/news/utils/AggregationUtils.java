package com.trs.cc.news.utils;

import org.bson.Document;


/**
 * 
 * @author TechRover Solutions
 *
 */
public class AggregationUtils {

	
	public static CustomAggregationOperation unWindAggrBuilder(String unwindField, boolean preserveNull){
		CustomAggregationOperation aggregationOperation = new CustomAggregationOperation(
		        new Document("$unwind",  new Document("path", unwindField).append("preserveNullAndEmptyArrays", preserveNull))
		    );		
		return aggregationOperation;
	}
	
	
	public static CustomAggregationOperation sortIntervalDateAggregationBuilder(String sortField){
		CustomAggregationOperation aggregationOperation = new CustomAggregationOperation(
		        new Document("$orderBy", new Document(sortField,-1))
		    );				
		return aggregationOperation;
	}
	
	
	public static CustomAggregationOperation lookupBuilder(String fromField, String locaField, String foreignField, String asField) {
		return new CustomAggregationOperation(
		        new Document(
		            "$lookup",
		            new Document("from", fromField)
		                .append("localField", locaField)
		                .append("foreignField", foreignField)
		                .append("as", asField)
		        )
		    );
	}
}
