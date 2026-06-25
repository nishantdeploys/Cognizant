# Exercise 1: Configuring a Basic Spring Application

This project is a basic Spring-based console application demonstrating the core concept of **Dependency Injection (DI)** using Spring's XML-based Application Context configuration. It simulates a backend operation for managing a library.

## Project Structure
```text
LibraryManagement/
├── pom.xml
├── README.md
└── src
    └── main
        ├── java
        │   └── com
        │       └── library
        │           ├── Main.java
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
The [pom.xml](file:///c:/luffy/LPUU/Projects/Cognizant/week2/LibraryManagement/pom.xml) configures Java 17 and defines the `spring-context` dependency, which transitively downloads Spring Core, Beans, AOP, and Expression Language packages.

### 2. Spring XML Context Configuration (`applicationContext.xml`)
Located in the resources folder [applicationContext.xml](file:///c:/luffy/LPUU/Projects/Cognizant/week2/LibraryManagement/src/main/resources/applicationContext.xml), this file:
- Defines a bean for `BookRepository`.
- Defines a bean for `BookService` and injects `BookRepository` using **setter injection** via the `<property>` tag:
  ```xml
  <bean id="bookService" class="com.library.service.BookService">
      <property name="bookRepository" ref="bookRepository" />
  </bean>
  ```

### 3. BookRepository (`BookRepository.java`)
The repository layer class [BookRepository.java](file:///c:/luffy/LPUU/Projects/Cognizant/week2/LibraryManagement/src/main/java/com/library/repository/BookRepository.java) simulates operations on a database.
```java
public void addBook(String title) {
    System.out.println("[BookRepository] Successfully saved book: \"" + title + "\" to the database.");
}
```

### 4. BookService (`BookService.java`)
The service layer class [BookService.java](file:///c:/luffy/LPUU/Projects/Cognizant/week2/LibraryManagement/src/main/java/com/library/service/BookService.java) implements business workflows and delegates actual persistence calls to the injected `BookRepository`.
```java
public void setBookRepository(BookRepository bookRepository) {
    this.bookRepository = bookRepository;
}
```

### 5. Application Runner (`Main.java`)
The main entry point class [Main.java](file:///c:/luffy/LPUU/Projects/Cognizant/week2/LibraryManagement/src/main/java/com/library/Main.java) loads the Spring context, retrieves the service bean, and tests the configuration.
```java
ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
BookService bookService = (BookService) context.getBean("bookService");
bookService.registerBook("The Great Gatsby");
```

---

## How to Run the Application

### Option A: Running inside an IDE (IntelliJ IDEA, Eclipse, VS Code)
1. Open the `LibraryManagement` directory as a Maven project in your IDE.
2. The IDE will automatically download the Spring dependency.
3. Open `src/main/java/com/library/Main.java`.
4. Right-click and choose **Run 'Main.main()'**.

### Option B: Running from Command Line (If Maven is installed and on PATH)
Run the following commands in the directory:
```bash
mvn clean compile
mvn exec:java -Dexec.mainClass="com.library.Main"
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
