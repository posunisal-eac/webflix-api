package com.jetherrodrigues.webflix.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.jetherrodrigues.webflix.domain.User;

@Repository
public interface UserRepository extends MongoRepository<User, String>{

}
