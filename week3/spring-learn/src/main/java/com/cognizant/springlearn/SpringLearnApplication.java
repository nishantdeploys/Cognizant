package com.cognizant.springlearn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The main entry point of the Spring Boot application.
 */
@SpringBootApplication
public class SpringLearnApplication {

    public static void main(String[] args) {
        // Boostrap the Spring ApplicationContext and start the embedded Tomcat server
        SpringApplication.run(SpringLearnApplication.class, args);
    }
}
