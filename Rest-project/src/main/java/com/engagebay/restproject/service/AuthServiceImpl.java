package com.engagebay.restproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Service
public class AuthServiceImpl implements AuthService{

    @Autowired
    UserService userService;

    /**
     * validating the user
     * @param basicAuth
     * @return boolean
     */
    @Override
    public boolean isValidBasicAuthReq(String basicAuth) {

        if(StringUtils.hasText(basicAuth) && basicAuth.toLowerCase().startsWith("basic")) {

            String base64Credentials = basicAuth.substring("Basic".length()).trim();

            byte[] credDecoded = Base64.getDecoder().decode(base64Credentials);
            String credetials = new String(credDecoded, StandardCharsets.UTF_8);


            final String[] val = credetials.split(":", 2);

            if (val.length == 2) {
                String username = val[0];
                String password = val[1];
                if (userService.getUserByNameAndPass(username, password) != null) {
                    return true;
                }
            }

        }
        return false;
    }

    @Override
    public boolean validateUser(String username, String pass, String basicAuth) {

        String [] values = getDetailsFromHeader(basicAuth);
        if(values.length == 2) {
            String uname = values[0];
            String upass = values[1];
            if(username.equals(uname) && pass.equals(upass)){
                return true;
            }
        }
        return false;
    }

    @Override
    public String[] getDetailsFromHeader(String authHeader) {
        if(StringUtils.hasText(authHeader) && authHeader.toLowerCase().startsWith("basic")) {

            String base64Credentials = authHeader.substring("Basic".length()).trim();

            byte[] credDecoded = Base64.getDecoder().decode(base64Credentials);
            String credetials = new String(credDecoded, StandardCharsets.UTF_8);

            String arr[] = credetials.split(":", 2);
            return arr;
        }
        return null;
    }
}
