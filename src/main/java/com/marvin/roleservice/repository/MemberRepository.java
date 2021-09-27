package com.marvin.roleservice.repository;

import com.marvin.roleservice.domain.Member;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for the {@link Member} domain.
 */
@Repository
public interface MemberRepository extends MongoRepository<Member, String> {

}
