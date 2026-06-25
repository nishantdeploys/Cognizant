package com.library;

import com.library.service.BookService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Initializing Spring Application Context ===");
        
        // 1. Load the Spring application context from the XML configuration file
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        
        System.out.println("=== Spring Application Context Initialized Successfully ===");

        // 2. Retrieve the BookService bean from the container
        BookService bookService = (BookService) context.getBean("bookService");

        // 3. Test the configured bean by performing operations
        System.out.println("\n--- Performing Book Operations ---");
        bookService.registerBook("The Great Gatsby");
        bookService.registerBook("To Kill a Mockingbird");
        bookService.removeBook("The Great Gatsby");
        System.out.println("----------------------------------\n");

        System.out.println("=== Spring Application Configuration Tested and Working Correctly ===");
    }
}
