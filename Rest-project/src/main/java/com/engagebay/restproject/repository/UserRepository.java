package com.engagebay.restproject.repository;

import com.engagebay.restproject.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User,String> {


    @Query("{username : ?0 , password : ?1 }")
    User getUserByNameAndPass(String userName, String password);

    User findUserByUsername(String username);
}
