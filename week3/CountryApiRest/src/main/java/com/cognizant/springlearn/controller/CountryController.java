package com.cognizant.springlearn.controller;

import com.cognizant.springlearn.Country;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST Controller for country services.
 */
@RestController
public class CountryController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CountryController.class);

    /**
     * Endpoint to retrieve the country details loaded from the XML configuration.
     * Accessible at: http://localhost:8083/country
     * 
     * @return Country object representing India
     */
    @RequestMapping(value = "/country", method = RequestMethod.GET)
    public Country getCountryIndia() {
        LOGGER.info("Start log: Entering getCountryIndia() controller method.");

        // Load the Spring XML Configuration from classpath
        ApplicationContext context = new ClassPathXmlApplicationContext("country.xml");

        // Retrieve the configured Country bean from the IoC Container
        Country country = context.getBean("country", Country.class);

        // Close the application context to release resources
        if (context instanceof ClassPathXmlApplicationContext) {
            ((ClassPathXmlApplicationContext) context).close();
        }

        LOGGER.info("End log: Exiting getCountryIndia() controller method with country: {}", country);
        return country;
    }
}
