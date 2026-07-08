package com.cognizant.account.controller;

import com.cognizant.account.model.Account;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST Controller for Account operations.
 * Provides endpoint to fetch account details by account number.
 */
@RestController
public class AccountController {

    /**
     * Endpoint: GET /accounts/{number}
     * Returns dummy account details.
     *
     * @param number The account number passed in the URL path.
     * @return An Account object containing dummy details.
     */
    @GetMapping("/accounts/{number}")
    public Account getAccount(@PathVariable String number) {
        // Return dummy data using the provided account number
        return new Account(number, "Savings", 234343.0);
    }
}
