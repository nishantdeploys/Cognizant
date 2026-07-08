# Account Microservice - Cognizant DN 5.0 Deep Skilling

## Project Overview
The **Account Microservice** is a lightweight, independent Spring Boot application developed as part of the Cognizant Digital Nurture (DN) 5.0 Deep Skilling program. It focuses on handling user account details and exposing them via a RESTful API.

## Objective
The primary objective of this project is to understand the core concept of **Microservice Architecture** by developing two independent applications (Account and Loan) that run on different ports and act as single-responsibility systems.

## What is a Microservice?
In simple words, a **Microservice** is an architectural style where a single large application is broken down into a collection of smaller, independent, and loosely coupled services. Each service runs its own process, manages its own domain (business logic), and communicates with other services using simple protocols (like HTTP/REST).

### Real-World Example
Think of a physical **Bank**. A bank has different departments to keep operations efficient:
*   **Accounts Department**: Only manages opening, closing, and updating accounts.
*   **Loans Department**: Only handles loan applications, approvals, and interest calculations.

Instead of putting all staff in one massive room handling everything, the bank splits them into specialized teams. Similarly, in Microservices:
1.  The [Account Microservice](file:///c:/luffy/LPUU/Projects/Cognizant/week4/AccountLoanMicroservice/account) handles all account-related tasks.
2.  The [Loan Microservice](file:///c:/luffy/LPUU/Projects/Cognizant/week4/AccountLoanMicroservice/loan) handles all loan-related tasks.

This is called **Microservices** because each application is "micro" (focused on one specific business capability) and runs as an independent "service".

## Why Not One Big Application?
Traditionally, applications were built as a single, unified unit called a **Monolith**. While simpler to start with, monolithic architectures become difficult to scale and maintain as they grow.

*   **Monolithic Architecture**: All features (Accounts, Loans, Credit Cards, NetBanking) are packaged in a single codebase and run as a single process on a single server. If the Loan module crashes, the entire bank application goes down.
*   **Microservices**: Features are split. If the Loan service crashes, the Account service continues to run perfectly, keeping the system resilient.

### Comparison Table

| Feature | Monolithic Architecture | Microservices Architecture |
| :--- | :--- | :--- |
| **Development** | Single codebase; easy to start but gets messy. | Separate codebases; clean and modular. |
| **Deployment** | Entire application must be redeployed for any change. | Each microservice can be deployed independently. |
| **Scaling** | Must scale the entire app, wasting resources. | Only scale the service experiencing high traffic. |
| **Fault Tolerance** | Single point of failure. If one component fails, the app crashes. | Highly fault-tolerant. One service failure does not crash the others. |
| **Technology Stack** | Forced to use the same technology for the whole app. | Different services can use different languages/frameworks. |

## Project Structure
Below is the directory structure of the Account Microservice project:

```
account/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── cognizant/
│   │   │           └── account/
│   │   │               ├── AccountApplication.java (Main boot class)
│   │   │               ├── controller/
│   │   │               │   └── AccountController.java (REST Controller)
│   │   │               └── model/
│   │   │                   └── Account.java (Data Model POJO)
│   │   └── resources/
│   │       └── application.properties (Configuration file)
│   └── test/ (Unit testing folder)
└── pom.xml (Maven Dependency and build file)
```

### Files Explained:
*   [pom.xml](file:///c:/luffy/LPUU/Projects/Cognizant/week4/AccountLoanMicroservice/account/pom.xml): Declares all dependencies needed for Spring Boot, Web, and DevTools.
*   [application.properties](file:///c:/luffy/LPUU/Projects/Cognizant/week4/AccountLoanMicroservice/account/src/main/resources/application.properties): Configures port configuration (`server.port=8080`) and application metadata.
*   [AccountApplication.java](file:///c:/luffy/LPUU/Projects/Cognizant/week4/AccountLoanMicroservice/account/src/main/java/com/cognizant/account/AccountApplication.java): Main entry point to bootstrap and launch the Spring Boot application.
*   [Account.java](file:///c:/luffy/LPUU/Projects/Cognizant/week4/AccountLoanMicroservice/account/src/main/java/com/cognizant/account/model/Account.java): Data carrier representing accounts.
*   [AccountController.java](file:///c:/luffy/LPUU/Projects/Cognizant/week4/AccountLoanMicroservice/account/src/main/java/com/cognizant/account/controller/AccountController.java): REST Controller that handles incoming HTTP requests and serves account details.

## API
*   **Method**: `GET`
*   **Endpoint**: `/accounts/{number}`
*   **Request URL Example**: `http://localhost:8080/accounts/00987987973432`
*   **Response Payload**:
    ```json
    {
        "number": "00987987973432",
        "type": "Savings",
        "balance": 234343.0
    }
    ```

## How to Run

### Step 1: Clone Repository
```bash
git clone <repository-url>
```

### Step 2: Open in IDE
*   Open IntelliJ IDEA or VS Code.
*   Select **Open** and import the `account` project folder as a **Maven Project**.

### Step 3: Clean and Install
Open a terminal in the root directory of the account project and run:
```bash
mvn clean install
```

### Step 4: Run Application
*   Option A: Find [AccountApplication.java](file:///c:/luffy/LPUU/Projects/Cognizant/week4/AccountLoanMicroservice/account/src/main/java/com/cognizant/account/AccountApplication.java) in your IDE, right-click, and select **Run**.
*   Option B: Run from the terminal:
    ```bash
    mvn spring-boot:run
    ```

### Step 5: Test the Endpoint
*   **Browser**: Open `http://localhost:8080/accounts/00987987973432`
*   **Postman**: Send a `GET` request to `http://localhost:8080/accounts/00987987973432`
*   **curl**: Run the following command in terminal:
    ```bash
    curl http://localhost:8080/accounts/00987987973432
    ```

## Code Explanation

### 1. Model Class: [Account.java](file:///c:/luffy/LPUU/Projects/Cognizant/week4/AccountLoanMicroservice/account/src/main/java/com/cognizant/account/model/Account.java)
It contains three simple fields representing the account structure: `number` (String), `type` (String), and `balance` (double). It includes a default constructor, a parameterized constructor to easily instantiate account objects, and getters/setters for encapsulation.

### 2. REST Controller: [AccountController.java](file:///c:/luffy/LPUU/Projects/Cognizant/week4/AccountLoanMicroservice/account/src/main/java/com/cognizant/account/controller/AccountController.java)
*   **`@RestController`**: Combines `@Controller` and `@ResponseBody`. Tells Spring Boot that this class is a controller and the return values of methods should be written directly to the HTTP response body as JSON.
*   **`@GetMapping("/accounts/{number}")`**: Maps HTTP GET requests on `/accounts/{number}` to this method.
*   **`@PathVariable String number`**: Extracts the value of `{number}` from the URL path and binds it to the method parameter `number`.
*   **`getAccount(String number)`**: Instantiates a new `Account` object passing the number from the path and dummy static values (`"Savings"`, `234343.0`), returning it directly.

### 3. Application Main: [AccountApplication.java](file:///c:/luffy/LPUU/Projects/Cognizant/week4/AccountLoanMicroservice/account/src/main/java/com/cognizant/account/AccountApplication.java)
*   **`@SpringBootApplication`**: A convenience annotation that enables component scanning, auto-configuration, and configuration properties.
*   **`SpringApplication.run(AccountApplication.class, args)`**: Launches the internal embedded Tomcat server on port `8080` to run the application.

## Why server.port=8081 for the Loan service?
Every network-enabled application requires a **port** to listen to incoming connections. 
*   By default, Spring Boot runs on port `8080`.
*   Two applications **cannot** run on the same port simultaneously on the same system; trying to do so results in a `PortAlreadyInUseException`.
*   Since the Account service runs on port `8080`, we configure the Loan service to run on port `8081` to allow both microservices to run side-by-side without network conflicts.

## Common Errors

1.  **Port Already in Use (`BindException`)**:
    *   *Cause*: Another application (e.g. Oracle DB, IIS, or another instance of the account app) is already running on port `8080`.
    *   *Fix*: Stop the other application, or edit `server.port` in `application.properties` to a free port.
2.  **404 Not Found**:
    *   *Cause*: Entering the wrong URL path, e.g., `/account` instead of `/accounts`.
    *   *Fix*: Double-check spelling and pluralization in both the controller mapping and browser address bar.
3.  **500 Internal Server Error**:
    *   *Cause*: Java code threw an unhandled exception during request execution.
    *   *Fix*: Review logs/stack trace in the IDE terminal to find where the error occurred.
4.  **Maven Dependency Issues**:
    *   *Cause*: Outdated local repository or connection issues downloading Spring libraries.
    *   *Fix*: Right-click on project -> Maven -> Reload Project, or run `mvn clean install -U`.

## Interview Questions & Answers

### 1. What is Microservices Architecture?
**Answer**: It is an architectural pattern where an application is composed of small, independent services that run as separate processes and communicate via lightweight protocols (e.g., HTTP/REST).

### 2. How does Microservices compare to Monolithic Architecture?
**Answer**: A monolith has all functions bundled together in one codebase and process. Microservices break this down into dedicated, single-purpose applications which are independently deployable and scalable.

### 3. What is Spring Boot and why is it used for Microservices?
**Answer**: Spring Boot is an extension of the Spring framework that simplifies Java application development using auto-configuration, starter POMs, and embedded servers (like Tomcat), making it ideal for packaging self-contained services quickly.

### 4. What is the role of the `@RestController` annotation?
**Answer**: It marks a class as a RESTful web controller, combining `@Controller` and `@ResponseBody`. This ensures that handler methods automatically serialize Java return types into JSON responses.

### 5. What does the `@PathVariable` annotation do?
**Answer**: It is used to bind placeholder parameters from the URL path template (e.g., `{number}`) to method arguments in your controller.

### 6. Why did we run the loan service on port 8081?
**Answer**: Because a single OS cannot bind multiple active processes to the same port. To run both microservices concurrently, they must listen on separate ports (Account on 8080 and Loan on 8081).

### 7. How do you configure a custom port in a Spring Boot application?
**Answer**: By defining the property `server.port=8081` in the [application.properties](file:///c:/luffy/LPUU/Projects/Cognizant/week4/AccountLoanMicroservice/account/src/main/resources/application.properties) or `application.yml` file.

### 8. What is the HTTP GET method used for?
**Answer**: It is used to request or retrieve data from a specified resource. It should not modify data and must be idempotent.

### 9. What is a REST API?
**Answer**: Representational State Transfer (REST) is an architectural style for design APIs over HTTP using standard methods (GET, POST, PUT, DELETE) and standard media formats (JSON, XML).

### 10. Why is there no database or JPA in this assignment?
**Answer**: To keep the implementation simple, focused on demonstrating the core microservices boundary concepts and lightweight HTTP communications without persistence overhead.

### 11. What is an embedded server in Spring Boot?
**Answer**: It is a server (like Tomcat, Jetty, or Undertow) packaged inside the executable JAR, allowing you to run web applications using `java -jar` without installing external application servers.

### 12. How do you start a Spring Boot application from the command line?
**Answer**: By executing `mvn spring-boot:run` in the project root directory containing the `pom.xml`.

### 13. What is the purpose of Spring Boot DevTools?
**Answer**: It provides development-time helper features such as automatic application restarts upon code modification, live reloading of resources, and caching disablement.

### 14. What does `@SpringBootApplication` do?
**Answer**: It is a core configuration annotation that combines `@Configuration`, `@EnableAutoConfiguration`, and `@ComponentScan` to set up classpath scanning and Spring context auto-wiring.

### 15. What are Maven Starter dependencies?
**Answer**: They are standard descriptors containing dependency lists that simplify importing related modules (like `spring-boot-starter-web` for Web MVC APIs).

### 16. What is Client-Server Architecture?
**Answer**: A model where a client (browser, Postman) requests resources or services from a server (Spring Boot application), which processes the request and returns a response.

### 17. How does JSON serialization work in Spring Boot?
**Answer**: Spring Boot uses the Jackson library by default to convert Java POJOs returned by controllers into JSON strings.

### 18. What is the difference between `@GetMapping` and `@RequestMapping`?
**Answer**: `@GetMapping` is a composed annotation that acts as a shortcut for `@RequestMapping(method = RequestMethod.GET)`.

### 19. What is loose coupling in Microservices?
**Answer**: It means that individual services have little to no direct dependencies on each other's internals, communicating solely through well-defined APIs, allowing independent modifications.

### 20. What is a POJO?
**Answer**: Plain Old Java Object. A simple Java class containing only fields, constructors, getters, setters, and basic utility methods without framework constraints.

## Key Takeaways
*   **Microservices** allow modularization of backend apps into small, specialized units.
*   We created two independent projects: `account` and `loan`.
*   The services are completely decoupled, run on different ports (`8080` and `8081`), and serve structured JSON responses over HTTP.
