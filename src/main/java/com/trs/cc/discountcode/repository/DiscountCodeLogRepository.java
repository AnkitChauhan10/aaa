package com.trs.cc.discountcode.repository;

import com.trs.cc.discountcode.model.DiscountCodeLog;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DiscountCodeLogRepository extends MongoRepository<DiscountCodeLog, String> {
}
