package com.trs.cc.discountcode.repository;

import com.trs.cc.discountcode.model.DiscountCode;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface DiscountCodeRepository extends MongoRepository<DiscountCode, String> {
    boolean existsByDiscountCodeAndSoftDeleteIsFalse(String discountCode);
    boolean existsByNameAndSoftDeleteIsFalse(String name);
    Optional<DiscountCode> findByIdAndSoftDeleteIsFalse(String discountCodeId);
    Optional<DiscountCode> findByDiscountCodeAndSoftDeleteIsFalse(String discountCode);
    List<DiscountCode> findAllBySoftDeleteIsFalse();
}
