package com.engagebay.restproject.AuthConfig;


import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {


    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Bean
    public AuthenticationManager customAuthenticationManager(HttpSecurity httpSecurity) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = httpSecurity.getSharedObject
                (AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(userDetailsService)
                .passwordEncoder(bCryptPasswordEncoder());
        return authenticationManagerBuilder.build();
    }


    @Bean
    public PasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

       http.csrf().disable();

       http.cors();

       http.sessionManagement()
           .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
           .and();

       http.exceptionHandling()
           .authenticationEntryPoint( (request, response, authException) -> {
               response.sendError(
                   HttpServletResponse.SC_UNAUTHORIZED,
                   authException.getMessage()
               );
           } ).and();

       http.authorizeHttpRequests(auth -> auth
           .requestMatchers("/user-api/authenticate","/user-api/register").permitAll()
           .anyRequest()
           .authenticated());

       http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);



//        http.csrf().disable()
//            .authorizeRequests()
//            .requestMatchers("/contact-api/**").hasRole("USER")
//            .and()
//            .authorizeRequests()
//            .requestMatchers("/user-api/authenticate", "/user-api/register").permitAll()
//            .anyRequest()
//            .authenticated()
//            .and()
//            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);


        return http.build();
    }
}


