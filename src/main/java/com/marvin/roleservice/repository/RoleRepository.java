package com.marvin.roleservice.repository;

import com.marvin.roleservice.domain.Role;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for the {@link Role} domain.
 */
@Repository
public interface RoleRepository extends MongoRepository<Role, String> {

}
