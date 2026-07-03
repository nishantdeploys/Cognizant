package com.cognizant.jwt.controller;

import com.cognizant.jwt.model.JwtResponse;
import com.cognizant.jwt.util.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * Controller handling authentication requests.
 * Parses and decodes HTTP Basic Authentication headers, validates user credentials,
 * and issues JWT tokens upon successful login.
 */
@RestController
public class AuthenticationController {

    private static final Logger log = LoggerFactory.getLogger(AuthenticationController.class);

    @Autowired
    private JwtUtil jwtUtil;

    /**
     * Authenticates a user by parsing the HTTP Basic Auth Authorization header,
     * decoding the base64 credentials, and checking them against a static lookup (user/pwd).
     * If validation is successful, returns a JSON object containing the JWT token.
     */
    @GetMapping("/authenticate")
    public ResponseEntity<?> authenticate(@RequestHeader(value = "Authorization", required = false) String authHeader) {
        log.info("Start Log: Received authentication request");

        // 1. Read and validate Authorization header existence
        if (authHeader == null || !authHeader.startsWith("Basic ")) {
            log.error("End Log: Missing or invalid Authorization header");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ErrorResponse("Missing or invalid Authorization header. Please use HTTP Basic authentication."));
        }

        try {
            // 2. Decode Base64 credentials
            String base64Credentials = authHeader.substring(6).trim();
            byte[] decodedBytes = Base64.getDecoder().decode(base64Credentials);
            String credentials = new String(decodedBytes, StandardCharsets.UTF_8);

            // 3. Extract username and password
            String[] values = credentials.split(":", 2);
            if (values.length != 2) {
                log.error("End Log: Invalid credentials format");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new ErrorResponse("Invalid Basic Authentication format. Must be username:password."));
            }

            String username = values[0];
            String password = values[1];

            // 4. Validate credentials
            if ("user".equals(username) && "pwd".equals(password)) {
                // 5. Generate JWT Token
                String token = jwtUtil.generateToken(username);
                log.info("End Log: Authentication successful. Token generated.");
                
                // 6. Return JWT Response as JSON
                return ResponseEntity.ok(new JwtResponse(token));
            } else {
                log.error("End Log: Authentication failed. Invalid credentials for user: {}", username);
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(new ErrorResponse("Invalid username or password"));
            }

        } catch (IllegalArgumentException e) {
            log.error("End Log: Failed to decode Base64 credentials - {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse("Failed to decode Base64 credentials."));
        } catch (Exception e) {
            log.error("End Log: Unexpected error during authentication - {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse("An unexpected error occurred during authentication."));
        }
    }

    /**
     * Helper response model for structured JSON error messages.
     */
    public static class ErrorResponse {
        private final String error;

        public ErrorResponse(String error) {
            this.error = error;
        }

        public String getError() {
            return error;
        }
    }
}
