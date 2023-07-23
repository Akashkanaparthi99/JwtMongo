//package com.engagebay.restproject.AuthConfig;
//
//import com.engagebay.restproject.service.AuthService;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.stereotype.Component;
//import org.springframework.web.servlet.HandlerInterceptor;
//import org.springframework.web.servlet.ModelAndView;
//
//import java.util.Enumeration;
//
//@Component
//public class AuthInterceptor implements HandlerInterceptor {
//
//    @Autowired
//    AuthService authService;
//
//    private static Logger log = LoggerFactory.getLogger(AuthInterceptor.class);
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        System.out.println("Pre Handle is loaded..");
//        response.setHeader("Access-Control-Allow-Origin", "*");
//        response.setHeader("Access-Control-Allow-Methods", "GET,POST,DELETE,PUT,OPTIONS");
//        response.setHeader("Access-Control-Allow-Headers", "*");
//        response.setHeader("Access-Control-Allow-Credentials", "true");
//        response.setHeader("Access-Control-Max-Age", "180");
//        String basicAuthHeader = request.getHeader("authorization");
//        System.out.println(basicAuthHeader);
//
//        boolean isValid = authService.isValidBasicAuthReq(basicAuthHeader);
//        if(!isValid){
//            response.setStatus(HttpStatus.UNAUTHORIZED.value());
//        }
//        return isValid;
////        return true;
//    }
//
//    @Override
//    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
//        System.out.println("Post Handle is loaded..");
//        request.getHeader("auth-key");
//
//        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
//    }
//
//    @Override
//    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//        System.out.println("After Completion is loaded..");
//        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
//    }
//
//}
