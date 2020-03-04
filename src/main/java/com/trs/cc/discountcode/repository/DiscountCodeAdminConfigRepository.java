package com.trs.cc.discountcode.repository;

import com.trs.cc.discountcode.model.DiscountCodeAdminConfiguration;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Authentication Configurations available to Tech Admin and Admin
 * @author TRS
 *
 */
@Repository
public interface DiscountCodeAdminConfigRepository extends MongoRepository<DiscountCodeAdminConfiguration, String>{
}
