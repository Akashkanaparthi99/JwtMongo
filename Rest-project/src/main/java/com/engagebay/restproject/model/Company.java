package com.engagebay.restproject.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "company")
public class Company {
    @Id
    private String id;
    private Integer companyId;
    private String companyName;
    private String websiteUrl;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getWebsiteUrl() {
        return websiteUrl;
    }

    public void setWebsiteUrl(String websiteUrl) {
        this.websiteUrl = websiteUrl;
    }

    public Company() {
    }

    public Company(String id, Integer companyId, String companyName, String websiteUrl) {
        this.id = id;
        this.companyId = companyId;
        this.companyName = companyName;
        this.websiteUrl = websiteUrl;
    }

    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", companyId=" + companyId +
                ", companyName='" + companyName + '\'' +
                ", websiteUrl='" + websiteUrl + '\'' +
                '}';
    }
}
