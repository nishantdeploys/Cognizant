package com.cognizant.springlearn;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Model class representing a Country with code and name.
 * Demonstrates Spring setter injection and lifecycle debug logging.
 */
public class Country {
    private static final Logger LOGGER = LoggerFactory.getLogger(Country.class);

    private String code;
    private String name;

    /**
     * Empty constructor with debug logging.
     * Invoked when the Spring container instantiates the bean.
     */
    public Country() {
        LOGGER.debug("Inside Country constructor: Bean instantiation started.");
    }

    /**
     * Getter for country code.
     */
    public String getCode() {
        LOGGER.debug("Inside getCode() method. Current code: {}", code);
        return code;
    }

    /**
     * Setter for country code.
     * Invoked by Spring container during setter injection.
     */
    public void setCode(String code) {
        LOGGER.debug("Inside setCode() method. Setting code to: {}", code);
        this.code = code;
    }

    /**
     * Getter for country name.
     */
    public String getName() {
        LOGGER.debug("Inside getName() method. Current name: {}", name);
        return name;
    }

    /**
     * Setter for country name.
     * Invoked by Spring container during setter injection.
     */
    public void setName(String name) {
        LOGGER.debug("Inside setName() method. Setting name to: {}", name);
        this.name = name;
    }

    /**
     * Overridden toString method.
     */
    @Override
    public String toString() {
        return "Country [code=" + code + ", name=" + name + "]";
    }

    /**
     * Method to display the country details using logger info.
     */
    public void displayCountry() {
        LOGGER.info("displayCountry() invoked - {}", this.toString());
    }
}
