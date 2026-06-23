package com.example;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;

public class CalculatorAAATest {

    private Calculator calculator;

    @Before
    public void setUp() {
        // Initialize the Calculator object before each test
        calculator = new Calculator();
    }

    @After
    public void tearDown() {
        // Set the Calculator object to null after each test to release resources
        calculator = null;
    }

    @Test
    public void testAddUsingAAA() {
        // Arrange: Prepare the data
        int number1 = 2;
        int number2 = 3;
        int expectedResult = 5;

        // Act: Call the method under test
        int actualResult = calculator.add(number1, number2);

        // Assert: Verify the result using assertEquals
        assertEquals(expectedResult, actualResult);
    }
}
