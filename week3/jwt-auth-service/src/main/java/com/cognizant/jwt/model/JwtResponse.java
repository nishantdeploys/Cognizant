package com.cognizant.jwt.model;

import java.io.Serializable;

/**
 * Model class representing the JWT response payload.
 */
public class JwtResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private final String token;

    public JwtResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return this.token;
    }
}
