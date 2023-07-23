package com.engagebay.restproject.repository;

import com.engagebay.restproject.model.Company;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CompanyRepository extends MongoRepository<Company, String> {

    List<Company> findByCompanyNameContainsIgnoreCase(String search);
}
