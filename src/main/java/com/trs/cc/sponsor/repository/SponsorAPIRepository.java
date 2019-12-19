package com.trs.cc.sponsor.repository;

import com.trs.cc.sponsor.model.SponsorAPI;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * User Repository interface
 * @author TRS
 *
 */
@Repository
public interface SponsorAPIRepository extends MongoRepository<SponsorAPI, String> {

	List<SponsorAPI> findByNameAndRolesIn(String name, List<String> roles);
	boolean existsByName(String name);



}
