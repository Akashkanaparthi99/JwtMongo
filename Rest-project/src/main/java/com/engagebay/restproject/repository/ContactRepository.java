package com.engagebay.restproject.repository;

import com.engagebay.restproject.model.Contact;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface ContactRepository extends MongoRepository<Contact, String> {

    @Query("{ $or : [{firstName: { $regex : ?0 , $options : 'i' }},{lastName: { $regex : ?0  , $options : 'i' }},{company: { $regex : ?0  , $options : 'i' }} , {phoneNumber: { $regex : ?0  , $options : 'i' }}]}")
    List<Contact> findBySearchText(String searchtext);

//    List<Contact> findByFirstNameIsLikeIgnoreCaseOrLastNameIsLikeIgnoreCase(String searchtext , String text);
}
