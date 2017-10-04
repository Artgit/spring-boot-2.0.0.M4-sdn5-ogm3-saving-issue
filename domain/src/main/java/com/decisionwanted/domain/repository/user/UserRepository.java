package com.decisionwanted.domain.repository.user;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.decisionwanted.domain.model.user.User;
import com.decisionwanted.domain.repository.BaseRepository;

@Repository
public interface UserRepository extends BaseRepository<User> {

	@Query("MATCH (u:User) WHERE u.id = {userId} RETURN u")
	User getById(@Param("userId") Long userId);

}