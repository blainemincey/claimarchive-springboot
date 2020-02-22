package com.mongodb.claimarchive.rest;

import com.mongodb.claimarchive.rest.db.MyMongoOperations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class RestApplication {

    // Logger component
    private static final Logger log = LoggerFactory.getLogger(RestApplication.class);

    @Value("${cors.allowed-origins.1}")
    private String allowedOrigin1;

    @Value("${cors.allowed-origins.2}")
    private String allowedOrigin2;

    public static void main(String[] args) {
        log.info("=======Starting App=======");
        SpringApplication.run(RestApplication.class, args);
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        log.info("Allowed Origin 1: " + allowedOrigin1);
        log.info("Allowed Origin 2: " + allowedOrigin2);


        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/**")
                .allowedOrigins(allowedOrigin1,allowedOrigin2)
                .allowedMethods("GET", "POST");
            }
        };
    }
}
