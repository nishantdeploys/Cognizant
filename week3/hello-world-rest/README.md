# Hello World RESTful Web Service

## Project Overview
This project is a beginner-friendly introduction to building RESTful Web Services using Spring Boot and Maven. It implements a simple `/hello` endpoint that returns a `"Hello World!!"` message on port `8083`. This project is part of the Cognizant Digital Nurture (DN) 5.0 Deep Skilling Program week 3 curriculum.

---

## Objective
* Learn how to set up a Spring Boot project from scratch using Maven.
* Understand REST principles and how RESTful APIs work.
* Learn the roles of key annotations such as `@RestController` and `@GetMapping`.
* Implement a controller with structured logging using SLF4J (Logger) to trace the execution flow (start and end logs).
* Deploy the web service on a customized port (`8083`) and verify its functionality.

---

## Problem Statement
In traditional enterprise applications, systems are often tightly coupled, making it hard to share data or functionality between a web frontend, a mobile app, and third-party systems. 
A RESTful API provides a standardized, lightweight, platform-independent way for different applications to communicate over standard HTTP. Building a basic "Hello World" endpoint demonstrates the boilerplate setup needed to start serving dynamic HTTP requests.

---

## What is REST?
**REST** stands for **Representational State Transfer**. It is an architectural style designed for network-based applications. REST defines a set of constraints (like client-server, statelessness, cacheability, layered system, and uniform interface) that systems must follow to interact over the web.

Key principles of REST:
1. **Statelessness**: Every request from client to server must contain all the information necessary to understand and complete the request. The server doesn't store client session state.
2. **Client-Server**: Separation of concerns. The client handles the user interface and user experience, while the server handles data storage and business logic.
3. **Uniform Interface**: Resources are identified by URIs, and standard HTTP methods are used to manipulate them.

---

## What is RESTful API?
* **REST (Representational State Transfer)** is the *architectural style* or the set of design principles.
* **RESTful API** is the *actual implementation* of an API that conforms to the REST architectural constraints.

Think of REST as the blueprint/guidelines, and a RESTful API as the physical building constructed according to those guidelines.

---

## Why REST APIs are used?
REST APIs are widely used because they are:
1. **Platform-Independent**: A REST API written in Java can be consumed by a React frontend (JavaScript), an iOS app (Swift), an Android app (Kotlin), or a Python backend.
2. **Scalable**: Due to statelessness, any server in a cluster can handle any request, making horizontal scaling simple.
3. **Lightweight**: REST standardizes communication using light payloads (usually JSON or plain text), reducing network overhead compared to older XML-based SOAP protocols.

**Real-World Example**: When you check the weather on your phone, the weather app sends an HTTP GET request to a weather server (e.g., `https://api.weather.com/v1/forecast`), which returns the weather data as JSON. The app then parses and displays it to you.

---

## HTTP Methods
HTTP methods indicate the desired action to be performed on a given resource:

* **GET**: Retrieve data from the server. (e.g., `GET /users` returns a list of users). It is safe and idempotent (calling it multiple times doesn't change server state).
* **POST**: Submit data to the server to create a new resource. (e.g., `POST /users` creates a new user). It is neither safe nor idempotent.
* **PUT**: Replace an existing resource entirely or create it if it doesn't exist. (e.g., `PUT /users/1` updates all fields of user 1). It is idempotent.
* **DELETE**: Remove a specific resource. (e.g., `DELETE /users/1` deletes user 1). It is idempotent.
* **PATCH**: Apply partial modifications to a resource. (e.g., `PATCH /users/1` updates only the user's email address). It is not idempotent.

---

## What is a Controller?
In the Model-View-Controller (MVC) design pattern, the **Controller** acts as an intermediary between the View (user interface/client request) and the Model (business logic/data). 
Controllers intercept incoming client requests, process them (often by calling service classes), and return the appropriate response back to the client. They keep business logic separated from HTTP-related details.

---

## What is @RestController?
`@RestController` is a convenience annotation in Spring MVC. It is a combination of two annotations:
1. `@Controller`: Marks the class as a Spring Bean that handles web requests.
2. `@ResponseBody`: Automatically serializes the return value of controller methods directly into the HTTP response body (typically in JSON, XML, or plain text format) instead of looking for a HTML view template.

---

## What is @GetMapping?
`@GetMapping` is a composed annotation that acts as a shortcut for `@RequestMapping(method = RequestMethod.GET)`. It maps incoming HTTP GET requests for a specific URI endpoint to a handler method in the controller.

---

## Request Flow
Here is the step-by-step workflow of a request:

```text
  [ Client Request ] (e.g., Browser / Postman / curl GET http://localhost:8083/hello)
          │
          ▼
    [ Tomcat Server ] (Embedded web container listening on port 8083)
          │
          ▼
[ DispatcherServlet ] (Front Controller pattern; intercepts & routes all requests)
          │
          ▼
   [ HelloController ] (Spring MVC controller matching the route "/hello")
          │
          ▼
   [ sayHello() Method ] (Executes business logic & prints start/end logs)
          │
          ▼
[ HTTP Response Body ] (Returns raw string "Hello World!!" to client)
```

---

## What happens internally?
1. You type `http://localhost:8083/hello` in your browser.
2. The browser performs a DNS lookup (resolves `localhost` to `127.0.0.1`) and opens a TCP connection to port `8083`.
3. The browser sends an HTTP request: `GET /hello HTTP/1.1`.
4. Embedded Tomcat receives the request and wraps it in an `HttpServletRequest` object.
5. Tomcat passes the request to Spring's `DispatcherServlet`.
6. `DispatcherServlet` queries `HandlerMapping` to find which controller handles `/hello`.
7. `HandlerMapping` identifies `HelloController` and its method `sayHello()`.
8. `DispatcherServlet` calls `HelloController.sayHello()` via reflection.
9. `HelloController.sayHello()` writes the start log, assigns `"Hello World!!"`, writes the end log, and returns the string.
10. Since `HelloController` is annotated with `@RestController`, Spring uses a `HttpMessageConverter` (specifically `StringHttpMessageConverter`) to write `"Hello World!!"` directly into the HTTP response body.
11. `DispatcherServlet` returns the HTTP response back to Tomcat.
12. Tomcat sends the HTTP response over the TCP connection to the browser.
13. The browser displays the plain text: `Hello World!!`.

---

## Logging
Logging is essential for monitoring, debugging, and diagnosing issues in production environments. Unlike `System.out.println()`, professional logging frameworks allow level configuration, format customization, file rotation, and asynchronous writing.

* **Start/End Logs**: Demarcating the start and end of a method execution helps developers trace the exact flow of execution, calculate method duration, and isolate failure points.
* **Logger**: The object interface used to record messages.
* **SLF4J (Simple Logging Facade for Java)**: A facade/abstraction layer for logging frameworks (like Logback, Log4j2). It decouples the codebase from the underlying logging library, allowing easy configuration changes.

---

## Project Structure
```text
hello-world-rest/
├── pom.xml                               # Maven project configuration file
└── src/
    └── main/
        ├── java/
        │   └── com/
        │       └── cognizant/
        │           └── springlearn/
        │               ├── SpringLearnApplication.java    # Application Main Class
        │               └── controller/
        │                   └── HelloController.java       # REST Controller Class
        └── resources/
            └── application.properties    # Configuration file (Port & Logging)
```

### Files Explained:
* **pom.xml**: Defines dependencies (Spring Boot Starter Web, Starter Test), parent version, and packaging plugins.
* **SpringLearnApplication.java**: Standard entry point annotated with `@SpringBootApplication` to bootstrap and auto-configure Spring.
* **HelloController.java**: Exposes the `/hello` endpoint and implements SLF4J logging.
* **application.properties**: Customizes Spring Boot parameters, e.g., setting the port to `8083`.

---

## Code Explanation

### 1. `SpringLearnApplication.java`
```java
package com.cognizant.springlearn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringLearnApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringLearnApplication.class, args);
    }
}
```
* **`@SpringBootApplication`**: Combines `@SpringBootConfiguration` (registers class as a source of bean definitions), `@EnableAutoConfiguration` (tells Spring Boot to start adding beans based on classpath settings), and `@ComponentScan` (tells Spring to scan packages for controllers, services, repositories).

### 2. `HelloController.java`
```java
package com.cognizant.springlearn.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    private static final Logger LOGGER = LoggerFactory.getLogger(HelloController.class);

    @GetMapping("/hello")
    public String sayHello() {
        LOGGER.info("Start sayHello");
        String response = "Hello World!!";
        LOGGER.info("End sayHello");
        return response;
    }
}
```
* **`import org.slf4j.Logger` & `import org.slf4j.LoggerFactory`**: Imports SLF4J interfaces.
* **`@RestController`**: Declares this class as a REST Controller.
* **`private static final Logger LOGGER = LoggerFactory.getLogger(HelloController.class)`**: Initializes the SLF4J logger instance for the class.
* **`@GetMapping("/hello")`**: Maps HTTP GET requests on `/hello` path to `sayHello()`.
* **`LOGGER.info("Start sayHello")` / `LOGGER.info("End sayHello")`**: Logs start/end states at the `INFO` level.

---

## Testing

### A. Run the Application
From the `hello-world-rest` project directory, run:
```bash
mvn spring-boot:run
```

### B. Accessing the Endpoint

#### 1. Browser
Open your browser and navigate to:
`http://localhost:8083/hello`

#### 2. Postman
1. Select **GET** method.
2. Enter URL: `http://localhost:8083/hello`.
3. Click **Send**.
4. Status code will be `200 OK` and Response Body will display `Hello World!!`.

#### 3. curl
Run the following in command prompt or terminal:
```bash
curl http://localhost:8083/hello
```

---

## Expected Output

### Console Logs
During startup:
```text
2026-07-01 21:30:00.123  INFO 12345 --- [           main] c.c.springlearn.SpringLearnApplication   : Starting SpringLearnApplication using Java 21 on Localhost
2026-07-01 21:30:02.456  INFO 12345 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat initialized with port 8083 (http)
2026-07-01 21:30:03.789  INFO 12345 --- [           main] c.c.springlearn.SpringLearnApplication   : Started SpringLearnApplication in 4.12 seconds
```

When hitting `http://localhost:8083/hello`:
```text
2026-07-01 21:31:10.010  INFO 12345 --- [nio-8083-exec-1] c.c.s.controller.HelloController        : Start sayHello
2026-07-01 21:31:10.012  INFO 12345 --- [nio-8083-exec-1] c.c.s.controller.HelloController        : End sayHello
```

### Browser / Client Response
```text
Hello World!!
```

---

## Common Errors
1. **`Port 8083 already in use`**:
   * **Cause**: Another process or an active instance of the app is already listening on port 8083.
   * **Solution**: Kill the process running on port 8083, or change `server.port` in `application.properties` to a different number (e.g., `8084`).
2. **`404 Not Found`**:
   * **Cause**: The endpoint was typed incorrectly (e.g., `/sayhello` instead of `/hello`), or the controller package is not configured under the main package hierarchy, preventing scan.
   * **Solution**: Ensure `HelloController` is under a sub-package of `com.cognizant.springlearn`, e.g., `com.cognizant.springlearn.controller`.
3. **`Circular view path`**:
   * **Cause**: Using `@Controller` instead of `@RestController` without specifying `@ResponseBody`. Spring tries to locate a template named `Hello World!!` which does not exist.
   * **Solution**: Change `@Controller` to `@RestController`.

---

## Interview Questions & Answers

### 1. What is Spring Boot?
Spring Boot is an extension of the Spring Framework that simplifies bootstrapping and development of Spring applications. It eliminates most boilerplate XML/Java configurations by offering starter templates, auto-configuration, and an embedded web server (Tomcat).

### 2. How does Spring Boot auto-configure an application?
It uses the `@EnableAutoConfiguration` annotation (part of `@SpringBootApplication`). It scans the classpath for dependencies (e.g., finding `spring-boot-starter-web` enables Tomcat and Spring MVC configuration automatically).

### 3. What is the difference between `@Controller` and `@RestController`?
`@RestController` is a specialized controller that combines `@Controller` and `@ResponseBody`. With `@RestController`, every handler method automatically serializes the returned object into the HTTP response body directly instead of returning a view/template name.

### 4. What is the role of `DispatcherServlet`?
`DispatcherServlet` is the front-controller of Spring MVC. It intercepts all incoming HTTP requests and coordinates their processing by routing them to the correct controller handler methods using handler mappings.

### 5. What are Spring Boot Starter Dependencies?
They are Maven/Gradle dependency descriptors that group related libraries together. For example, `spring-boot-starter-web` includes everything needed for web applications (Spring MVC, Jackson, Tomcat, SLF4J, etc.).

### 6. What is the default embedded server in Spring Boot, and how do you change it?
The default embedded server is Apache Tomcat. You can change it to Jetty or Undertow by excluding Tomcat in the Maven dependency and including the respective starter dependency.

### 7. How can you change the default port of a Spring Boot application?
By defining the `server.port` property in `src/main/resources/application.properties` or `application.yml` (e.g., `server.port=8083`).

### 8. What is the difference between GET and POST requests?
GET retrieves resource data from the server, is safe, idempotent, and appends parameter data in the URL path. POST sends data to the server to create a new resource, is not safe or idempotent, and transmits data in the request body.

### 9. What makes an HTTP method "idempotent"?
An HTTP method is idempotent if executing it multiple times yields the same state on the server. For example, executing `GET /hello` multiple times returns the same greeting and changes nothing on the server.

### 10. Why is SLF4J preferred over Logback or Log4j directly?
SLF4J is a facade (abstraction layer). By writing logging statements against SLF4J, the application code remains decoupled from the specific logger implementation, allowing developers to switch from Logback to another logger easily.

### 11. What is the difference between `@RequestMapping` and `@GetMapping`?
`@RequestMapping` is a generic mapping annotation that can match any HTTP method (GET, POST, etc.) using its `method` attribute. `@GetMapping` is a shortcut specialized for mapping HTTP GET requests only.

### 12. Where does Spring Boot start scanning for components?
By default, `@SpringBootApplication` enables component scanning starting from the package containing the main application class (and all its sub-packages).

### 13. What is Maven and how does Spring Boot use it?
Maven is a build automation tool. Spring Boot projects use it to compile Java code, manage dependencies (using `pom.xml`), run tests, and package the application into a JAR or WAR file.

### 14. What are the different logging levels supported by Spring Boot?
The standard logging levels (in ascending order of severity) are: `TRACE`, `DEBUG`, `INFO`, `WARN`, and `ERROR`. By default, Spring Boot logs at the `INFO` level.

### 15. What is the purpose of `@ResponseBody`?
It indicates that the return value of a method should be serialized and written directly to the HTTP response body instead of being resolved as a template (HTML page) path.

### 16. What is dependency injection (DI) in Spring?
Dependency Injection is a pattern where the Spring container supplies the objects (dependencies) that a class needs rather than the class creating them itself. This makes the code decoupled and testable.

### 17. How does Tomcat serve request threads in Spring Boot?
Tomcat maintains a pool of worker threads. When a request arrives, Tomcat assigns one thread from the pool to process the entire request cycle (through DispatcherServlet and the Controller) and release it back to the pool once done.

### 18. What is the default path mapping of DispatcherServlet?
By default, the `DispatcherServlet` is mapped to the root path (`/`). It handles all incoming paths.

### 19. Why should you write start and end logs in endpoints?
They serve as markers to trace the flow of requests in production. If a request crashes or hangs, reviewing the logs will show exactly which controller was entered and whether it exited successfully.

### 20. How do you run a Spring Boot application using Maven CLI?
Use the command `mvn spring-boot:run` in the project root directory where `pom.xml` is located.

---

## Key Takeaways
1. **Spring Boot starter web** simplifies build management by importing web, tomcat, and validation dependencies with a single dependency definition.
2. **`@RestController`** simplifies writing API controllers by implicitly applying `@ResponseBody`.
3. **`server.port`** allows developers to avoid port conflicts by customizing where Tomcat listens.
4. **SLF4J** logging decouples the application from concrete logging engines, facilitating cleaner, more maintainable code.
