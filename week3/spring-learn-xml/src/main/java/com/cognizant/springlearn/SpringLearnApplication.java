package com.cognizant.springlearn;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Main application class to bootstrap the Spring ApplicationContext using XML configuration,
 * retrieve the Country bean, and display its details.
 */
public class SpringLearnApplication {
    private static final Logger LOGGER = LoggerFactory.getLogger(SpringLearnApplication.class);

    public static void main(String[] args) {
        LOGGER.info("Starting SpringLearnApplication (XML-based context)...");

        // 1. Load the Spring XML Configuration from classpath
        ApplicationContext context = new ClassPathXmlApplicationContext("country.xml");

        // 2. Retrieve the configured Country bean from the IoC Container
        Country country = context.getBean("country", Country.class);

        // 3. Display the country details using logger
        LOGGER.info("Retrieved Country bean: {}", country);

        // 4. Invoke displayCountry() from main
        country.displayCountry();

        // 5. Close the application context to release resources
        if (context instanceof ClassPathXmlApplicationContext) {
            ((ClassPathXmlApplicationContext) context).close();
        }

        LOGGER.info("SpringLearnApplication execution completed.");
    }
}
