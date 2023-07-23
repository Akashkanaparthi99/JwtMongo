package com.engagebay.restproject.service;

import com.engagebay.restproject.model.Company;

import java.util.List;

public interface CompanyService {

    void addCompany(Company company);
    void updateCompany(Company company);
    void deleteCompany(String companyId);
    Company getByID(String companyId);

    List<Company> getAllCompanys();

    List<Company> getByCompanyNameContainsIgnoreCase(String search);
}
