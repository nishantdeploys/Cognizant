# JUnit 4 Calculator Project

A minimal, beginner-friendly Maven-based Java project designed for learning JUnit 4 in VS Code or any other IDE.

## 📁 Project Structure

```text
JUnitTest/
├── pom.xml
├── README.md
└── src/
    ├── main/
    │   └── java/
    │       └── com/
    │           └── example/
    │               └── Calculator.java      # Source class
    └── test/
        └── java/
            └── com/
                └── example/
                    └── CalculatorTest.java  # Test class
```

---

## 🛠️ Prerequisites

To run this project, make sure you have the following installed:
- **Java Development Kit (JDK 8 or higher)**
- **Apache Maven** (optional, if running via command line)
- **VS Code** with the **Extension Pack for Java** (recommended)

---

## 🚀 How to Run the Project

### 1. In VS Code (Recommended)
1. Open the folder `JUnitTest` in VS Code.
2. Open `src/test/java/com/example/CalculatorTest.java`.
3. Wait for the Java extension to load. Click **"Run Test"** above the `testAdd()` method or open the **Testing** panel on the left sidebar to execute.

### 2. Using Maven (Command Line)
Navigate to the `JUnitTest` directory in your terminal and execute:
```bash
mvn test
```
This downloads the JUnit 4.13.2 dependency automatically, builds the project, and runs the test suite.

---

## 📝 Code Overview

### `Calculator.java`
A simple class implementing basic mathematical operations.
```java
package com.example;

public class Calculator {
    public int add(int a, int b) {
        return a + b;
    }
}
```

### `CalculatorTest.java`
A JUnit 4 test case validating the `Calculator` functionality.
```java
package com.example;

import org.junit.Test;
import static org.junit.Assert.*;

public class CalculatorTest {
    @Test
    public void testAdd() {
        Calculator calculator = new Calculator();
        assertEquals(8, calculator.add(5, 3));
    }
}
```
