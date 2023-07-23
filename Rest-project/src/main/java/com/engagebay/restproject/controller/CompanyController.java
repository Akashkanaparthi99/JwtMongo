package com.engagebay.restproject.controller;

import com.engagebay.restproject.model.Company;
import com.engagebay.restproject.service.CompanyService;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/company-api")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @PostMapping("/companys")
    public ResponseEntity<String> insertCompany(@RequestBody Company company){
        companyService.addCompany(company);
        return ResponseEntity.status(HttpStatus.CREATED).body("Company Inserted Successfully");
    }

    @PutMapping("/companys")
    public ResponseEntity<String> updateCompany(@RequestBody Company company){
        companyService.updateCompany(company);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Company updated Successfully");
    }

    @DeleteMapping("/companys/{id}")
    public ResponseEntity<String> updateCompany(@PathVariable("id") String companyId){
        companyService.deleteCompany(companyId);
        return ResponseEntity.status(HttpStatus.OK).body("Company deleted Successfully");
    }

    @GetMapping("/companys/{id}")
    public ResponseEntity<Company> getById(@PathVariable("id") String companyId){
        return ResponseEntity.status(HttpStatus.OK).body(companyService.getByID(companyId));
    }

    @GetMapping("/companys")
    public ResponseEntity<List<Company>> getAllCompanys(){
        return ResponseEntity.ok().body(companyService.getAllCompanys());
    }

    @GetMapping("/companys/search")
    @RolesAllowed("USER")
    public ResponseEntity<List<Company>> getCompanysBySearch(@RequestParam("text") String text){
        return ResponseEntity.ok(companyService.getByCompanyNameContainsIgnoreCase(text));
    }
}
