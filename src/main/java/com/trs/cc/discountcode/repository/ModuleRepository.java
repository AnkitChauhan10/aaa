package com.trs.cc.discountcode.repository;

import com.trs.cc.discountcode.model.Module;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ModuleRepository extends MongoRepository<Module, String> {
    boolean existsByNameAndSoftDeleteIsFalse(String name);
    Optional<Module> findByIdAndSoftDeleteIsFalse(String id);
}
