# JUnit 4 Arrange-Act-Assert (AAA) Project

A minimal, beginner-friendly Maven-based Java project designed for learning the **Arrange-Act-Assert (AAA)** pattern and JUnit 4 **Test Fixtures** (`@Before` and `@After`).

## 📁 Project Structure

```text
JUnitAAA/
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
                    └── CalculatorAAATest.java  # Test class with fixture & AAA pattern
```

---

## 🛠️ Concepts Learned

### 1. Test Fixtures (`@Before` and `@After`)
* **`@Before`**: Run before each test method. Used to set up initial state (e.g., initializing a `Calculator` instance).
* **`@After`**: Run after each test method. Used to tear down/clean up resources (e.g., setting the reference to `null`).

### 2. Arrange-Act-Assert (AAA) Pattern
* **Arrange**: Set up the test inputs and preconditions.
* **Act**: Invoke the method under test.
* **Assert**: Verify that the actual outcome matches the expected outcome.

---

## 🚀 How to Run the Project

### 1. In VS Code (Recommended)
1. Open the folder `JUnitAAA` in VS Code.
2. Open `src/test/java/com/example/CalculatorAAATest.java`.
3. Wait for the Java extension to load. Click **"Run Test"** above the `testAddUsingAAA()` method or use the Testing side panel.

### 2. Using Command Line (Java CLI)
Since JUnit is managed via Maven, you can compile and run the tests directly by referencing the cached dependencies from your shell:

```powershell
# Create classes directory
New-Item -ItemType Directory -Force -Path target/classes

# Compile source and test files
javac -cp "C:\Users\nisha\.m2\repository\junit\junit\4.13.2\junit-4.13.2.jar;C:\Users\nisha\.m2\repository\org\hamcrest\hamcrest-core\1.3\hamcrest-core-1.3.jar" -d target/classes src/main/java/com/example/Calculator.java src/test/java/com/example/CalculatorAAATest.java

# Run the JUnit test suite
java -cp "target/classes;C:\Users\nisha\.m2\repository\junit\junit\4.13.2\junit-4.13.2.jar;C:\Users\nisha\.m2\repository\org\hamcrest\hamcrest-core\1.3\hamcrest-core-1.3.jar" org.junit.runner.JUnitCore com.example.CalculatorAAATest
```

---

## 📊 Expected Testing Outcome

```text
JUnit version 4.13.2
.
Time: 0.026

OK (1 test)
```
