package com.cognizant.jwt.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Configuration for Spring Security.
 * Permits access to /authenticate endpoint and establishes stateless session management.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            // Disable CSRF since REST APIs use tokens rather than cookies
            .csrf(csrf -> csrf.disable())
            
            // Define access rules
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/authenticate").permitAll() // Allow everyone to request JWT
                .anyRequest().authenticated() // Authenticate other endpoints
            )
            
            // Enforce stateless session management (no HTTP session is created/used by Spring Security)
            .sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            );
            
        return http.build();
    }
}
