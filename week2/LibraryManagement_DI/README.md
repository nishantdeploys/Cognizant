# Exercise 2: Implementing Dependency Injection

This project is a basic Spring-based console application demonstrating the core concept of **Dependency Injection (DI)** using Spring's XML-based Application Context configuration. It manages the dependencies between the `BookService` and `BookRepository` classes.

## Project Structure
```text
LibraryManagement_DI/
├── pom.xml
├── README.md
└── src
    └── main
        ├── java
        │   └── com
        │       └── library
        │           ├── LibraryManagementApplication.java
        │           ├── repository
        │           │   └── BookRepository.java
        │           └── service
        │               └── BookService.java
        └── resources
            └── applicationContext.xml
```

---

## Code Components

### 1. Maven Project Configuration (`pom.xml`)
The [pom.xml](file:///c:/luffy/LPUU/Projects/Cognizant/week2/LibraryManagement_DI/pom.xml) configures Java 17 and defines the `spring-context` dependency.

### 2. Spring XML Context Configuration (`applicationContext.xml`)
Located in the resources folder [applicationContext.xml](file:///c:/luffy/LPUU/Projects/Cognizant/week2/LibraryManagement_DI/src/main/resources/applicationContext.xml), this file:
- Defines a bean for `BookRepository`.
- Defines a bean for `BookService` and injects `BookRepository` using **setter injection** via the `<property>` tag:
  ```xml
  <bean id="bookService" class="com.library.service.BookService">
      <property name="bookRepository" ref="bookRepository" />
  </bean>
  ```

### 3. BookRepository (`BookRepository.java`)
The repository layer class [BookRepository.java](file:///c:/luffy/LPUU/Projects/Cognizant/week2/LibraryManagement_DI/src/main/java/com/library/repository/BookRepository.java) simulates operations on a database.
```java
public void addBook(String title) {
    System.out.println("[BookRepository] Successfully saved book: \"" + title + "\" to the database.");
}
```

### 4. BookService (`BookService.java`)
The service layer class [BookService.java](file:///c:/luffy/LPUU/Projects/Cognizant/week2/LibraryManagement_DI/src/main/java/com/library/service/BookService.java) implements business workflows and delegates actual persistence calls to the injected `BookRepository`. It exposes a setter method for dependency injection:
```java
public void setBookRepository(BookRepository bookRepository) {
    this.bookRepository = bookRepository;
}
```

### 5. Application Runner (`LibraryManagementApplication.java`)
The main entry point class [LibraryManagementApplication.java](file:///c:/luffy/LPUU/Projects/Cognizant/week2/LibraryManagement_DI/src/main/java/com/library/LibraryManagementApplication.java) loads the Spring context, retrieves the service bean, and tests the configuration.

---

## How to Run the Application

### Option A: Running in VS Code (Recommended)
1. Open the `LibraryManagement_DI` folder in VS Code.
2. Wait for the Java extension to load.
3. Open `src/main/java/com/library/LibraryManagementApplication.java`.
4. Click **Run** above the `main` method.

### Option B: Running from Command Line (If Maven is NOT installed/on PATH)
Since the Maven dependencies are already downloaded and cached locally, you can compile and run using Java CLI directly from the `week2/LibraryManagement_DI` directory:

```powershell
# 1. Create target/classes directory
New-Item -ItemType Directory -Force -Path target/classes

# 2. Compile source files using local maven cache dependencies
javac -cp "C:\Users\nisha\.m2\repository\org\springframework\spring-aop\6.1.10\spring-aop-6.1.10.jar;C:\Users\nisha\.m2\repository\org\springframework\spring-beans\6.1.10\spring-beans-6.1.10.jar;C:\Users\nisha\.m2\repository\org\springframework\spring-context\6.1.10\spring-context-6.1.10.jar;C:\Users\nisha\.m2\repository\org\springframework\spring-core\6.1.10\spring-core-6.1.10.jar;C:\Users\nisha\.m2\repository\org\springframework\spring-expression\6.1.10\spring-expression-6.1.10.jar;C:\Users\nisha\.m2\repository\org\springframework\spring-jcl\6.1.10\spring-jcl-6.1.10.jar" -d target/classes src/main/java/com/library/repository/BookRepository.java src/main/java/com/library/service/BookService.java src/main/java/com/library/LibraryManagementApplication.java

# 3. Run the application
java -cp "target/classes;src/main/resources;C:\Users\nisha\.m2\repository\org\springframework\spring-aop\6.1.10\spring-aop-6.1.10.jar;C:\Users\nisha\.m2\repository\org\springframework\spring-beans\6.1.10\spring-beans-6.1.10.jar;C:\Users\nisha\.m2\repository\org\springframework\spring-context\6.1.10\spring-context-6.1.10.jar;C:\Users\nisha\.m2\repository\org\springframework\spring-core\6.1.10\spring-core-6.1.10.jar;C:\Users\nisha\.m2\repository\org\springframework\spring-expression\6.1.10\spring-expression-6.1.10.jar;C:\Users\nisha\.m2\repository\org\springframework\spring-jcl\6.1.10\spring-jcl-6.1.10.jar" com.library.LibraryManagementApplication
```

### Option C: Running with Maven (If Maven is on your system PATH)
```bash
mvn clean compile
mvn exec:java -Dexec.mainClass="com.library.LibraryManagementApplication"
```


### Expected Output
When run successfully, you will see the following output in the console:
```text
=== Initializing Spring Application Context ===
... [Spring framework logs] ...
=== Spring Application Context Initialized Successfully ===

--- Performing Book Operations ---
[BookService] Processing registration for book: "The Great Gatsby"
[BookRepository] Successfully saved book: "The Great Gatsby" to the database.
[BookService] Processing registration for book: "To Kill a Mockingbird"
[BookRepository] Successfully saved book: "To Kill a Mockingbird" to the database.
[BookService] Processing removal for book: "The Great Gatsby"
[BookRepository] Successfully deleted book: "The Great Gatsby" from the database.
----------------------------------

=== Spring Application Configuration Tested and Working Correctly ===
```
