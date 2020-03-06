package com.trs.cc.discountcode.repository;

import com.trs.cc.discountcode.model.DiscountCodeAPI;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * User Repository interface
 * @author TRS
 *
 */
@Repository
public interface DiscountCodeAPIRepository extends MongoRepository<DiscountCodeAPI, String> {
    List<DiscountCodeAPI> findByNameAndRolesIn(String name, List<String> roles);
    boolean existsByName(String name);
}
