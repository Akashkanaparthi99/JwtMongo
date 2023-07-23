package com.engagebay.restproject;

import com.engagebay.restproject.model.Role;
import com.engagebay.restproject.model.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RestProjectApplication {


  public static void main(String[] args) {
    SpringApplication.run(RestProjectApplication.class, args);
//User user = new User("Akash","kanaparthi","Akash_k102","1234",Role.ADMIN);
//    Role role = user.getAssignedRole();
//
//      System.out.println(role.name());


  }


}
