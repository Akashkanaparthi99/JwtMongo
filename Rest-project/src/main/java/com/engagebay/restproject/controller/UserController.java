package com.engagebay.restproject.controller;

import com.engagebay.restproject.model.Role;
import com.engagebay.restproject.model.User;
import com.engagebay.restproject.service.UserService;
import com.engagebay.restproject.utility.JWTToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/user-api")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserDetailsService userDetailsService;


//    @Autowired
//    private AuthService authService;

    @Autowired
    private JWTToken jwtTokenUtil;
    @Autowired
    private AuthenticationManager authenticationManager;


    @PostMapping("/user")
    public ResponseEntity<String> addUser(@RequestBody User user){
        userService.addUser(user);
        return ResponseEntity.ok("User Added...");
    }

    @GetMapping("/user")
    public ResponseEntity<User> getUser(@RequestParam("user_id") String userId){
        return ResponseEntity.ok(userService.getUser(userId));
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUser(){
        return ResponseEntity.ok(userService.getAll());
    }

    @GetMapping("/user-id")
    public ResponseEntity<User> getUserByNameAndPass(@RequestParam("uname") String uname, @RequestParam("pass") String pass){
        return ResponseEntity.ok(userService.getUserByNameAndPass(uname,pass));
    }

    @GetMapping("/users/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable("username") String uname){
        return ResponseEntity.ok(userService.getUserByUsername(uname));
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user){
        String username = user.getUsername();
        String password =  passwordEncoder.encode(user.getPassword());
        String firstName = user.getFirstName();
        String lastName = user.getLastName();
        Role role = user.getAssignedRole();

        User jwtUser = new User(firstName, lastName, username, password, role);
        //save in db
        userService.addUser(jwtUser);
        return ResponseEntity.ok("added");
    }


    @PostMapping("/authenticate")
    public ResponseEntity<String> authenticate(@RequestBody User user){
        // specify the type of authentication provider
        authenticate(user.getUsername(), user.getPassword());
        // check if username is in db
        UserDetails details = userDetailsService.loadUserByUsername(user.getUsername());

        // generate the token with the userdata
        String token = jwtTokenUtil.generateToken(details);
        return ResponseEntity.ok(token);
    }

    // to specify the type of authentication
    private void authenticate(String username,String password){
        try {
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
            authenticationManager.authenticate(authenticationToken);
        }catch (BadCredentialsException e){
            System.out.println("Invalid Credentials");
        }catch (DisabledException e ){
            System.out.println("Disabled");
        }
    }

//    @PostMapping("/authenticate")
//        public ResponseEntity<String> authenticateUser(@RequestParam("auth-key") String authHeader){
//
//                    if(authService.isValidBasicAuthReq(authHeader)){
//                    return ResponseEntity.ok("Authorized");
//                }
//
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED.value()).body("Invalid User Credentials");
//    }
}
