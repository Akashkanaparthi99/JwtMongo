package com.engagebay.restproject.service;

public interface AuthService {

    boolean isValidBasicAuthReq(String basicAuth);
    boolean validateUser(String username ,  String pass, String basicAuth);

    String[] getDetailsFromHeader(String authHeader);
}
