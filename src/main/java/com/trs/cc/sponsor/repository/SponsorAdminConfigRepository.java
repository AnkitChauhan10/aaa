package com.trs.cc.sponsor.repository;

import com.trs.cc.sponsor.model.SponsorAdminConfiguration;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Authentication Configurations available to Tech Admin and Admin
 * @author TRS
 *
 */
@Repository
public interface SponsorAdminConfigRepository extends MongoRepository<SponsorAdminConfiguration, String>{


}
