package com.engagebay.restproject.service;

import com.engagebay.restproject.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public interface UserService {
    void addUser(User user);
    User getUser(String userID);
    List<User> getAll();

    User getUserByNameAndPass(String userName, String password);

    User getUserByUsername(String username);

}
