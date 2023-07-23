package com.engagebay.restproject.AuthConfig;

import com.engagebay.restproject.utility.JWTToken;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

  @Autowired
  private JWTToken jwtTokenUtil;
  @Autowired
  private UserDetailsService userDetailsService;
  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
    String headerValue = request.getHeader("Authorization");
    String jwtToken = null;
    String username = null;
    System.out.println(headerValue+"token in header value");
    // check if the header is not null and starts with the Bearer
    if (headerValue != null && headerValue.startsWith("Bearer")){

      // substring() takes the length of the bearer+" "
      // strip the jwt token from the header
      jwtToken = headerValue.substring(7);

      // get the username from the token
      username = jwtTokenUtil.getUsernameFromToken(jwtToken);
      System.out.println(username +" username from token");

    }else
      logger.warn("Invalid Header");

    // if the username is not null and the security context is not null
    // to specify the type of authentication
    if (username != null && SecurityContextHolder.getContext() != null){

      // check is the username is available in the database
      UserDetails details = userDetailsService.loadUserByUsername(username);

      if(jwtTokenUtil.validateToken(jwtToken,details)) {

        // configure the authentication                                                                                                 credentials - we don't want to show the password, so we keep it as null
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(details.getUsername(),null, Arrays.asList(new SimpleGrantedAuthority("ADMIN"),new SimpleGrantedAuthority("USER")));

        // set the authentication type
        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        // set the authentication for the security context
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
      }
      else
        logger.warn("Validation Failed..");

    }

    // no header means move to the next filter and then to DS
    filterChain.doFilter(request,response);
  }
}
