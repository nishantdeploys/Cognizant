# JUnit 4 Assertions Learning Project

A minimal, beginner-friendly Maven-based Java project designed for learning JUnit 4 assertions.

## 📁 Project Structure

```text
JUnitAssertions/
├── pom.xml
├── README.md
└── src/
    ├── main/
    │   └── java/             # Source files (empty for this exercise)
    └── test/
        └── java/
            └── com/
                └── example/
                    └── AssertionsTest.java  # Test class containing assertions
```

---

## 🛠️ Prerequisites

To run this project, make sure you have the following installed:
- **Java Development Kit (JDK 8 or higher)**
- **VS Code** with the **Extension Pack for Java** (recommended)

---

## 🚀 How to Run the Project

### In VS Code (Recommended)
1. Open the folder `JUnitAssertions` in VS Code.
2. Open `src/test/java/com/example/AssertionsTest.java`.
3. Wait for the Java extension to load. Click **"Run Test"** above the class or the `testAssertions()` method, or open the **Testing** panel on the left sidebar to execute.

---

## 📝 Code Overview

### `AssertionsTest.java`
A JUnit 4 test case validating various core assertion methods.
```java
package com.example;

import org.junit.Test;
import static org.junit.Assert.*;

public class AssertionsTest {

    @Test
    public void testAssertions() {
        assertEquals(5, 2 + 3);       // Verifies that expected and actual are equal
        assertTrue(5 > 3);            // Verifies that a condition is true
        assertFalse(5 < 3);           // Verifies that a condition is false
        assertNull(null);             // Verifies that an object reference is null
        assertNotNull(new Object());  // Verifies that an object reference is not null
    }
}
```
