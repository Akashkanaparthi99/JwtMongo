package com.engagebay.restproject.service;

import com.engagebay.restproject.model.Company;
import com.engagebay.restproject.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService{

    @Autowired
    private CompanyRepository companyRepository;

    @Override
    public void addCompany(Company company) {
        companyRepository.save(company);
    }

    @Override
    public void updateCompany(Company company) {
        companyRepository.save(company);
    }

    @Override
    public void deleteCompany(String companyId) {
        companyRepository.deleteById(companyId);
    }

    @Override
    public Company getByID(String companyId) {
        return companyRepository.findById(companyId).get();
    }

    @Override
    public List<Company> getAllCompanys() {
        return companyRepository.findAll();
    }

    @Override
    public List<Company> getByCompanyNameContainsIgnoreCase(String search) {
        return companyRepository.findByCompanyNameContainsIgnoreCase(search);
    }
}
