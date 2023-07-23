//package com.engagebay.restproject.AuthConfig;
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@Configuration
//public class AuthInterceptorConfig implements WebMvcConfigurer {
//    @Autowired
//    private AuthInterceptor authInterceptor;
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(authInterceptor)
//                 .excludePathPatterns("/contact-api/**")
//                 .excludePathPatterns("/company-api/**")
//                 .excludePathPatterns("/user-api/**");
//    }
//
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        System.out.println("inside cors ");
//        registry.addMapping("/**").allowedOrigins("*");
//    }
//}
