# REST - Country Web Service (Spring Boot & XML Configuration Integration)

Welcome to the **REST - Country Web Service** project. This assignment is part of the Cognizant Digital Nurture (DN) 5.0 Deep Skilling journey. This guide serves as a comprehensive explanation and reference for understanding REST APIs, Spring MVC architecture, XML-based dependency injection in a Spring Boot environment, and how Java objects are automatically serialized to JSON.

---

## Table of Contents
1. [Project Overview](#project-overview)
2. [Objective](#objective)
3. [Problem Statement](#problem-statement)
4. [What is REST?](#what-is-rest)
5. [What is a REST API?](#what-is-a-rest-api)
6. [Why REST APIs are used? (Real-World Analogy)](#why-rest-apis-are-used-real-world-analogy)
7. [What is JSON?](#what-is-json)
8. [What is Spring MVC?](#what-is-spring-mvc)
9. [What is a Controller?](#what-is-a-controller)
10. [What is @RequestMapping?](#what-is-requestmapping)
11. [What happens inside the Controller?](#what-happens-inside-the-controller)
12. [What is ApplicationContext?](#what-is-applicationcontext)
13. [What is ClassPathXmlApplicationContext?](#what-is-classpathxmlapplicationcontext)
14. [What is Dependency Injection?](#what-is-dependency-injection)
15. [How Spring Converts Java Objects into JSON](#how-spring-converts-java-objects-into-json)
16. [Request Flow (ASCII Diagram)](#request-flow-ascii-diagram)
17. [HTTP Request and Response](#http-request-and-response)
18. [Developer Tools](#developer-tools)
19. [Postman](#postman)
20. [Project Structure](#project-structure)
21. [Code Explanation](#code-explanation)
22. [Expected Output](#expected-output)
23. [Common Errors and Solutions](#common-errors-and-solutions)
24. [Technical Interview Questions (25+ Detailed Q&A)](#technical-interview-questions-25-detailed-qa)
25. [Key Takeaways](#key-takeaways)

---

## Project Overview
This project, `CountyApiRest`, is a spring-boot based web service that exposes a REST endpoint `/country` on port `8083`. Instead of instantiating the data model programmatically or using Spring annotations like `@Component` directly, this service demonstrates how to bridge the gap between traditional Spring XML configuration files and modern Spring Boot applications. The application boots Tomcat, exposes a REST controller, and programmatically loads the Spring beans configured within `country.xml` to serve the request.

---

## Objective
The main objectives of this assignment are:
- Build a functional Spring Boot REST service.
- Retrieve a configured bean dynamically from an XML application context configuration file (`country.xml`).
- Return the retrieved bean from a controller method using `@RequestMapping`.
- Verify the automatic JSON serialization of custom Java objects using standard HTTP serializers.
- Implement production-standard logging using SLF4J at the start and end of the controller endpoints.

---

## Problem Statement
In real-world legacy system migrations, organizations often transition from heavy XML-configured monolithic Spring applications to lightweight Spring Boot microservices. Engineers must understand how to interact with existing XML configurations inside modern Spring Boot controllers without entirely rewriting the bean metadata immediately. This project addresses a micro-scale version of this architecture: loading an XML-defined `Country` bean representing India (`{ "code": "IN", "name": "India" }`) and serving it over an HTTP REST API.

---

## What is REST?
**REST** stands for **Representational State Transfer**. It is an architectural style designed for network-based applications. REST defines a set of constraints (rules) that systems should follow to communicate over the web. 
The key constraints of REST include:
- **Client-Server Architecture**: The client (frontend, browser, mobile app) and the server (backend database and logic) are completely separated.
- **Statelessness**: Every request sent from a client to a server must contain all the information necessary to understand and process that request. The server does not store session context about the client.
- **Cacheability**: Responses must define themselves as cacheable or non-cacheable to improve performance.
- **Uniform Interface**: A standard protocol (typically HTTP) is used, making communication between systems highly predictable and consistent.

---

## What is a REST API?
A **REST API** (Application Programming Interface) is a web interface that implements the REST architectural style using the HTTP protocol. It exposes resource identifiers (URIs) that clients can request to perform actions using standard HTTP verbs:
- **GET**: Retrieve a resource (e.g., `GET /countries/IN`).
- **POST**: Create a new resource.
- **PUT**: Update an existing resource.
- **DELETE**: Remove a resource.

### Example:
A weather service might expose a REST API. A client requests `GET /weather/london` and receives a response detailing London's temperature in a standardized text format like JSON.

---

## Why REST APIs are used? (Real-World Analogy)
Imagine going to a restaurant.
- You are the **Client** (the frontend web page or mobile app).
- The kitchen is the **Server** (holds the food, ingredients, database, and chef logic).
- The waiter is the **REST API**.
- The menu is the **API Documentation** containing list of endpoints.

You don't go into the kitchen yourself to fetch or cook food. You read the menu, tell the waiter (send an HTTP request like `GET /burger`), the waiter carries this structured request to the kitchen, gets the burger (JSON data), and returns it to your table.
Because of this waiter (REST API), the restaurant can change its kitchen layout, hire new chefs, or change database storage without you ever knowing or needing to change how you request food. This separation makes modern web applications modular, scalable, and cross-platform compatible.

---

## What is JSON?
**JSON** stands for **JavaScript Object Notation**. It is a lightweight, text-based, human-readable format for representing structured data. JSON is language-independent, meaning almost every programming language (Java, Python, C#, JavaScript) can read and generate it.

### Why is JSON used?
Before JSON, XML was widely used. However, XML is verbose, heavy, and complex to parse. JSON has a smaller payload size, makes parsing faster, and maps directly to native objects in JavaScript, making it the perfect data format for modern web APIs.

### How Java Objects become JSON:
Java represents data using runtime instances (objects) with fields and methods. These objects reside in JVM memory. To send this data over the network, we must convert it into a string format. This process of converting a Java object to a JSON string is called **Serialization** (or marshalling). The reverse process is called **Deserialization** (or unmarshalling).

---

## What is Spring MVC?
**Spring MVC** (Model-View-Controller) is a framework built on top of the Core Spring Container that helps developers build flexible, loosely coupled web applications.
- **Model**: Encapsulates the application data (e.g., the `Country` class).
- **View**: The presentation layer (HTML/JSP pages or JSON data).
- **Controller**: Processes HTTP requests, builds the appropriate model, and passes it to the view.

---

## What is a Controller?
In Spring, a **Controller** is a class marked with the `@Controller` or `@RestController` annotation. Its primary responsibility is to act as an entry point for user requests. It intercepts HTTP requests, extracts parameters, coordinates business service calls, and returns a response back to the client.

---

## What is @RequestMapping?
`@RequestMapping` is a powerful annotation used in Spring MVC to map incoming HTTP web requests to specific handler methods inside controller classes.

### Internal Workings:
During application startup, Spring scans all controllers and looks for `@RequestMapping` annotations. It registers these paths in a component called the `HandlerMapping`. When an HTTP request arrives, the `DispatcherServlet` queries the `HandlerMapping` to look up which controller and method matches the incoming request path and HTTP method type.

### Why is it used?
It is used to define routing configurations. It can be applied at:
- **Class level**: To define a base URI path for all endpoints in a controller.
- **Method level**: To define the specific sub-path and HTTP method handler.

### Alternatives:
To make mappings cleaner, Spring 4.3 introduced specialized annotations that act as shortcuts for `@RequestMapping` with specific HTTP methods:
- **`@GetMapping`**: Shorthand for `@RequestMapping(method = RequestMethod.GET)`.
- **`@PostMapping`**: Shorthand for `@RequestMapping(method = RequestMethod.POST)`.
- **`@PutMapping`**: Shorthand for `@RequestMapping(method = RequestMethod.PUT)`.
- **`@DeleteMapping`**: Shorthand for `@RequestMapping(method = RequestMethod.DELETE)`.

---

## What happens inside the Controller?
When a client requests `http://localhost:8083/country`:
1. The request enters `CountryController.getCountryIndia()`.
2. A startup log is recorded using SLF4J.
3. The method instantiates `ClassPathXmlApplicationContext` pointing to `"country.xml"`.
4. The application context loads, parses the XML, instantiates the `Country` class, injects the values `"IN"` and `"India"` into its setters, and registers it as a bean named `"country"`.
5. The controller retrieves this bean from the context using `context.getBean("country", Country.class)`.
6. The context is closed to release system resources.
7. An exit log is recorded.
8. The method returns the `Country` object.

---

## What is ApplicationContext?
The `ApplicationContext` is the heart of the Spring Framework. It represents the **Spring IoC (Inversion of Control) Container**. Its responsibilities include:
- **Bean Factory**: Instantiating, configuring, and managing the lifecycle of Spring beans.
- **Dependency Injection**: Resolving dependencies between beans automatically.
- **Configuration Loading**: Loading configurations from XML, Java classes, or properties.

---

## What is ClassPathXmlApplicationContext?
`ClassPathXmlApplicationContext` is a concrete implementation of `ApplicationContext` that loads configuration details from an XML file located in the application's classpath (such as `src/main/resources`). It parses the XML, builds bean definitions, and manages bean lifecycles.

---

## What is Dependency Injection?
**Dependency Injection (DI)** is a pattern where an object's dependencies are provided to it by an external container (Spring IoC), rather than the object creating them itself.

### Example in this Project:
Instead of writing:
```java
Country country = new Country();
country.setCode("IN");
country.setName("India");
```
We define these values in `country.xml`:
```xml
<bean id="country" class="com.cognizant.springlearn.Country">
    <property name="code" value="IN" />
    <property name="name" value="India" />
</bean>
```
During runtime, the Spring IoC container instantiates `Country` and injects `"IN"` and `"India"` using setter injection (via `setCode()` and `setName()`).

---

## How Spring Converts Java Objects into JSON
Modern web controllers use `@RestController`, which combines `@Controller` and `@ResponseBody`. Here is how the conversion happens step-by-step:

1. **DispatcherServlet**: Receives the returned `Country` object from the controller.
2. **HttpMessageConverter**: The servlet checks if `@ResponseBody` is present. It iterates through its registered list of HTTP message converters.
3. **MappingJackson2HttpMessageConverter**: Since the project has `spring-boot-starter-web` on its classpath, Spring Boot auto-configures the **Jackson Library**.
4. **Jackson Engine**: The converter delegates serialization to Jackson's `ObjectMapper`. The `ObjectMapper` uses reflection to invoke getter methods (`getCode()` and `getName()`) of the `Country` object, constructs a JSON-formatted string, writes it directly into the HTTP response body, and sets the `Content-Type` header to `application/json`.

---

## Request Flow (ASCII Diagram)

```text
  [ Client (Browser / Postman) ]
               │
               ▼   HTTP GET Request to http://localhost:8083/country
       [ Tomcat Server ] (Listens on port 8083)
               │
               ▼   Forwards request
      [ DispatcherServlet ] (Front Controller)
               │
               ▼   Asks HandlerMapping to route request
    [ CountryController.getCountryIndia() ] (Controller Method)
               │
               ├─► [ ClassPathXmlApplicationContext ] (Loads country.xml)
               │            │
               │            ▼
               │   [ Country Bean ] (Instantiated and injected via Setter Injection)
               │            │
               ├─◄──────────┘ (Bean retrieved by controller)
               │
               ▼   Returns Country object
      [ HttpMessageConverter ] (MappingJackson2HttpMessageConverter)
               │
               ▼   Serializes object using Jackson Library
      [ HTTP Response Body ] (JSON: {"code":"IN", "name":"India"} + Content-Type: application/json)
               │
               ▼   Delivers response
  [ Client (Browser / Postman) ]
```

---

## HTTP Request and Response
- **Request Headers**: Information sent by the client to configure the request (e.g., `Accept: application/json` tells the server the client prefers JSON).
- **Response Headers**: Metadata sent by the server describing the payload.
- **Status Code**: Indicates if the request succeeded or failed (e.g., `200 OK`, `404 Not Found`, `500 Internal Server Error`).
- **Content-Type**: Tells the client what type of data the body contains. Because Spring converts the object to a JSON string, the response contains `Content-Type: application/json`.

---

## Developer Tools
You can inspect the complete request and response cycle directly in your browser:
1. Open Google Chrome.
2. Press `F12` or right-click and choose **Inspect**.
3. Go to the **Network** tab.
4. Visit `http://localhost:8083/country` in the URL bar.
5. Click on the request name (`country`) in the list.
6. Inspect:
   - **Headers**: View Request Headers, Response Headers, and HTTP Status.
   - **Response**: View the raw JSON string body.

---

## Postman
Postman is a popular API development client used to test REST endpoints:
1. Open Postman.
2. Select HTTP method `GET`.
3. Enter URL: `http://localhost:8083/country`
4. Click **Send**.
5. Inspect:
   - **Body**: The formatted JSON response.
   - **Headers**: Verify `Content-Type` is `application/json`.
   - **Status Code**: Verify it reads `200 OK`.
   - **Response Time**: Displays the millisecond duration of the request execution.

---

## Project Structure
```text
CountyApiRest/
 │
 ├── pom.xml                                 # Maven dependencies and build settings
 └── src/
     └── main/
         ├── java/                           # Production source code
         │   └── com/cognizant/springlearn/
         │       ├── SpringLearnApplication.java # Spring Boot entry point
         │       ├── Country.java            # Model class representing Country
         │       └── controller/
         │           └── CountryController.java # REST Controller mapping /country
         └── resources/                      # Configuration files
             ├── application.properties      # Configures port 8083 and package logging
             ├── country.xml                 # Spring IoC XML Bean definitions
             └── logback.xml                 # Configures SLF4J/Logback appenders
```

### File Explanation
- **`pom.xml`**: Project Object Model. Holds metadata and dependencies (`spring-boot-starter-web`).
- **`SpringLearnApplication.java`**: Bootstraps the application, instantiates the root application context, and starts embedded Tomcat.
- **`Country.java`**: The domain class. Contains code, name, constructors, getters, setters, and logging methods.
- **`CountryController.java`**: Exposes the REST endpoint, loads the XML context, retrieves the country bean, and returns it.
- **`application.properties`**: Controls port selection and logging visibility levels.
- **`country.xml`**: Holds setter configurations to instantiate the `country` bean automatically.
- **`logback.xml`**: Dictates the console logging layout template.

---

## Code Explanation

### 1. `CountryController.java`
- **`@RestController`**: Combined annotation (`@Controller` + `@ResponseBody`). Flags this class to Spring Boot as a REST controller.
- **`@RequestMapping(value = "/country", method = RequestMethod.GET)`**: Maps HTTP `GET` requests for the `/country` endpoint to the `getCountryIndia()` method.
- **`ClassPathXmlApplicationContext`**: Loads bean definitions from the specified XML file on the classpath (`country.xml`).
- **`context.getBean("country", Country.class)`**: Retrieves the bean registered with ID `country` and checks if it conforms to the `Country` class.
- **`LOGGER.info(...)`**: Writes messages to the console log standard output at the start and end of the controller method.

### 2. `Country.java`
- **`LOGGER.debug(...)`**: Emitted when getters, setters, or constructors run, allowing developers to trace the initialization process.
- **`displayCountry()`**: Standard method that invokes `LOGGER.info(...)` to display the stringified object values.

### 3. `country.xml`
- **`<bean id="country" class="...">`**: Defines a new bean name `country`.
- **`<property name="code" value="IN" />`**: Invokes `setCode("IN")` on the country bean using Spring Setter Injection.

---

## Expected Output

### Browser Output
Visiting `http://localhost:8083/country` shows:
```json
{"code":"IN","name":"India"}
```

### Postman Output
- **Status**: `200 OK`
- **Content-Type**: `application/json`
- **Body**:
```json
{
    "code": "IN",
    "name": "India"
}
```

### Console Logs
```text
2026-07-02 15:30:12 [http-nio-8083-exec-1] INFO  com.cognizant.springlearn.controller.CountryController - Start log: Entering getCountryIndia() controller method.
2026-07-02 15:30:12 [http-nio-8083-exec-1] DEBUG com.cognizant.springlearn.Country - Inside Country constructor: Bean instantiation started.
2026-07-02 15:30:12 [http-nio-8083-exec-1] DEBUG com.cognizant.springlearn.Country - Inside setCode() method. Setting code to: IN
2026-07-02 15:30:12 [http-nio-8083-exec-1] DEBUG com.cognizant.springlearn.Country - Inside setName() method. Setting name to: India
2026-07-02 15:30:12 [http-nio-8083-exec-1] DEBUG com.cognizant.springlearn.Country - Inside getCode() method. Current code: IN
2026-07-02 15:30:12 [http-nio-8083-exec-1] DEBUG com.cognizant.springlearn.Country - Inside getName() method. Current name: India
2026-07-02 15:30:12 [http-nio-8083-exec-1] INFO  com.cognizant.springlearn.controller.CountryController - End log: Exiting getCountryIndia() controller method with country: Country [code=IN, name=India]
```

---

## Common Errors and Solutions

### 1. `404 Not Found`
- **Cause**: The requested URL path does not match the path configured in `@RequestMapping`, or the controller was not scanned by Spring Boot.
- **Solution**: Check that the package layout starts from the location of `SpringLearnApplication.java` so that component scanning is active. Check spelling in `@RequestMapping("/country")`.

### 2. `500 Internal Server Error`
- **Cause**: An unhandled exception occurred in the backend method (e.g. XML file not found).
- **Solution**: Read the console output logs to find the exact stack trace.

### 3. `Bean Not Found` / `NoSuchBeanDefinitionException`
- **Cause**: The application requested `context.getBean("country")` but `country.xml` does not define a bean with `id="country"`.
- **Solution**: Check spelling of bean IDs in `country.xml` and `getBean()`.

### 4. XML Configuration Errors
- **Cause**: Broken or malformed XML schemas, missing matching closing tags, or mapping property names that don't match getters/setters.
- **Solution**: Validate XML formatting and check that getter/setter property naming conventions align perfectly (e.g., `code` property maps to `setCode()`).

### 5. Port Already in Use
- **Cause**: Another service is running on port `8083`.
- **Solution**: Shutdown conflicting processes, or update `server.port` inside `application.properties` to a free port (e.g., `server.port=8084`).

### 6. JSON Conversion Errors
- **Cause**: No public getter methods exist in the returned class, preventing Jackson from serializing data fields.
- **Solution**: Ensure getters like `getCode()` and `getName()` exist in `Country.java`.

### 7. Dependency Errors
- **Cause**: Incorrect configurations in `pom.xml`, resulting in missing class libraries.
- **Solution**: Execute `mvn clean install` to update and download required repository artifacts.

---

## Technical Interview Questions (25+ Detailed Q&A)

### Q1: What is REST and what are its key constraints?
**Answer**: REST is Representational State Transfer, a web architectural standard. Its constraints include: Client-Server separation, Statelessness, Cacheability, Uniform Interface, Layered System, and Optional Code on Demand.

### Q2: What is the difference between a Controller and a RestController in Spring?
**Answer**: `@Controller` maps a class as a web controller and expects controller actions to return view paths (like HTML files). `@RestController` is a convenience annotation that combines `@Controller` and `@ResponseBody`, meaning all returned Java values are automatically written directly to the HTTP response body, typically as serialized JSON.

### Q3: Explain the role of the DispatcherServlet in Spring Boot.
**Answer**: The `DispatcherServlet` is the front-controller pattern hub of Spring MVC. It intercepts all incoming HTTP requests, consults handler mappings to find the appropriate controller, executes handler methods, delegates response transformations to message converters, and sends response payloads back to clients.

### Q4: How does Spring Boot auto-configure dependencies like Jackson?
**Answer**: Through `@EnableAutoConfiguration` (nested inside `@SpringBootApplication`), Spring Boot scans the application classpath. If it finds `spring-boot-starter-web` containing Jackson libraries, it automatically configures a default `ObjectMapper` and registers `MappingJackson2HttpMessageConverter` into the web configuration context.

### Q5: What is the difference between @RequestMapping and @GetMapping?
**Answer**: `@RequestMapping` is the generic router annotation that works on all HTTP methods (GET, POST, PUT, DELETE) and can be applied at class level. `@GetMapping` is a specialized composed annotation that acts as a shortcut for `@RequestMapping(method = RequestMethod.GET)` specifically for HTTP GET requests.

### Q6: What is the Spring IoC Container?
**Answer**: The Inversion of Control (IoC) Container manages the instantiation, configuration, wiring, assembly, and lifecycle of application objects (Spring Beans) from birth to death.

### Q7: What is the difference between BeanFactory and ApplicationContext?
**Answer**: `BeanFactory` is the basic IoC container providing lazy instantiation of beans. `ApplicationContext` extends `BeanFactory` and adds advanced features like internationalization support, event publication, resource loading, and eager instantiation of singleton beans.

### Q8: What is ClassPathXmlApplicationContext and when is it used?
**Answer**: It is a class that implements the `ApplicationContext` interface, configured to load bean configurations programmatically from XML resource files located in the project's classpath folder.

### Q9: Explain Setter Injection with reference to this project.
**Answer**: Setter injection is a form of Dependency Injection where the container calls setter methods on the bean after instantiating it with a no-argument constructor. In `country.xml`, the `<property>` tag tells Spring to call `setCode("IN")` and `setName("India")` on the initialized `Country` object.

### Q10: Why did we close the ClassPathXmlApplicationContext inside the controller method?
**Answer**: Closing the application context ensures that resource leaks are avoided, shut down hooks are triggered, and resources associated with the local configuration context are safely deallocated.

### Q11: What is the purpose of the pom.xml file in a Maven project?
**Answer**: `pom.xml` (Project Object Model) contains build configurations, plug-in configurations, Maven coordinates (groupId, artifactId, version), and dependencies used to build and package the application.

### Q12: What is the parent tag inside pom.xml used for?
**Answer**: The `<parent>` tag inherits configuration from a parent POM (e.g., `spring-boot-starter-parent`), which manages central dependency versions, compiler configurations, and default plug-ins to avoid version mismatches.

### Q13: What is the default port for Spring Boot and how do we change it?
**Answer**: The default port is `8080`. We can change it by overriding the `server.port` property inside `src/main/resources/application.properties` (e.g., `server.port=8083`).

### Q14: How does Spring Boot handle logging under the hood?
**Answer**: Spring Boot uses SLF4J as a logging abstraction facade, which delegates the actual log writing to Logback by default.

### Q15: What are logging levels and which levels did we use?
**Answer**: Logging levels prioritize logs by severity: TRACE, DEBUG, INFO, WARN, ERROR. We configured the root logger to `INFO` and our application package `com.cognizant` to `DEBUG` to see constructor and setter lifecycle logs.

### Q16: What is a "Fat JAR" or "Uber JAR"?
**Answer**: It is an executable archive format generated by the `spring-boot-maven-plugin`. It contains the compiled application code, resources, all external library dependency JARs, and an embedded servlet container (Tomcat) so it can run independently using `java -jar`.

### Q17: What does @SpringBootApplication represent?
**Answer**: It is a composite annotation that combines `@SpringBootConfiguration` (registers the class as a configuration source), `@EnableAutoConfiguration` (auto-configures beans based on classpath), and `@ComponentScan` (recursively scans packages for Spring Stereotypes).

### Q18: What is Content-Type in HTTP and why is it important?
**Answer**: `Content-Type` is an HTTP header that tells the receiver how to interpret the payload. For REST APIs returning JSON, the value `application/json` tells the client to parse the response body as a structured JSON object.

### Q19: What is the difference between path variables and request parameters?
**Answer**: Request parameters (`@RequestParam`) are query string key-value pairs appended after the `?` in the URL (e.g. `/info?name=value`). Path variables (`@PathVariable`) are dynamic segments embedded directly inside the URL path structure (e.g. `/country/{id}`).

### Q20: What is the role of the Jackson library in Spring Boot?
**Answer**: Jackson is the default JSON library in Spring Boot. It handles data binding between JSON text and Java objects (POJOs), serializing Java beans into JSON output streams.

### Q21: What is the difference between singleton and prototype bean scopes in Spring?
**Answer**: A **singleton** bean (default) is instantiated once per Spring container, and every request for the bean returns the same instance. A **prototype** bean is instantiated anew every time it is requested from the container.

### Q22: What happens if a class does not have getter methods when returning it from a @RestController?
**Answer**: Jackson will throw a serialization exception because it uses reflection on the getter methods to construct the JSON keys. Without getters, it cannot access the fields to construct the JSON string.

### Q23: Why is SLF4J preferred over Log4j directly?
**Answer**: SLF4J is a facade or wrapper, which decouples your code from the underlying logging framework (like Logback or Log4j). This allows you to switch logging frameworks at runtime through configuration without altering code.

### Q24: What is statelessness in REST?
**Answer**: Statelessness means the server does not store any client session state. Each incoming request from a client must contain all necessary data (like authentication tokens or query details) to complete the operation.

### Q25: How do you handle exceptions in Spring Boot REST APIs?
**Answer**: By using `@ControllerAdvice` and `@ExceptionHandler` annotations to define global handlers that catch exceptions and return custom, user-friendly JSON error responses and appropriate HTTP status codes.

### Q26: What is the standard status code for a successful GET request?
**Answer**: The HTTP Status Code is `200 OK`.

---

## Key Takeaways
- **XML-Spring Boot Integration**: Modern Boot apps can easily load classic XML contexts using `ClassPathXmlApplicationContext`.
- **Automatic JSON Marshalling**: By utilizing `@RestController`, you delegate the complex task of object serialization entirely to Jackson.
- **Port Customization**: Easily manage running microservices on dedicated ports via `application.properties`.
- **Lifecycle Auditing**: Configuring appropriate log packages ensures constructor, getter, and setter instantiation stages are recorded transparently.
