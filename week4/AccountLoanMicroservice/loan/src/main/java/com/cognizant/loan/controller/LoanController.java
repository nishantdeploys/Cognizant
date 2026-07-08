package com.cognizant.loan.controller;

import com.cognizant.loan.model.Loan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST Controller for Loan operations.
 * Provides endpoint to fetch loan details by loan number.
 */
@RestController
public class LoanController {

    /**
     * Endpoint: GET /loans/{number}
     * Returns dummy loan details.
     *
     * @param number The loan number passed in the URL path.
     * @return A Loan object containing dummy details.
     */
    @GetMapping("/loans/{number}")
    public Loan getLoan(@PathVariable String number) {
        // Return dummy data using the provided loan number
        return new Loan(number, "Car", 400000.0, 3258.0, 18);
    }
}
