package com.engagebay.restproject.service;

import com.engagebay.restproject.model.User;
import com.engagebay.restproject.repository.UserRepository;
import com.mongodb.MongoWriteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Service
public class UserServiceImpl implements UserService , UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public void addUser(User user) {
        try {
            userRepository.save(user);
        }
        catch (MongoWriteException e){
            System.out.println("Enter Unique Username");
        }
    }

    @Override
    public User getUser(String userID) {
        return userRepository.findById(userID).get();
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User getUserByNameAndPass(String userName, String password) {
        return userRepository.getUserByNameAndPass(userName,password);
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByUsername(username);
        if (user == null)
            throw new UsernameNotFoundException("Invalid Username");

        String nusername = user.getUsername();
        String password = user.getPassword();
        Collection<GrantedAuthority> grantedAuthority = Arrays.asList(new SimpleGrantedAuthority(user.getAssignedRole().toString()));
        UserDetails userDetails = new org.springframework.security.core.userdetails.User(nusername,password, grantedAuthority);
        return userDetails;
    }
}
