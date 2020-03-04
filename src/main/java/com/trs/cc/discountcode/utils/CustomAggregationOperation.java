package com.trs.cc.discountcode.utils;

import org.bson.Document;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperationContext;

public class CustomAggregationOperation implements AggregationOperation {

	private Document document;

    public CustomAggregationOperation(Document operation) {
        this.document = operation;
    }


	@Override
	public Document toDocument(AggregationOperationContext context) {
		return context.getMappedObject(document);
	}

}
