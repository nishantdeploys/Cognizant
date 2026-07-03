# JWT Authentication Service

## Overview
The **JWT Authentication Service** is a Spring Boot application designed to secure APIs by issuing JSON Web Tokens (JWT) to users after validating their credentials. It uses HTTP Basic Authentication for the initial validation and then generates a compact, secure JWT token which the client can use for subsequent requests without sending credentials again.

---

## Objective
The primary objective of this project is to build an independent, lightweight **Authentication Service**. It validates username and password transmitted via standard HTTP Basic Authentication, issues a signed JWT token if the credentials are correct, and returns the token in a clean JSON format.

---

## Problem Statement
In modern microservices and web architectures, transmitting credentials (username and password) on every request exposes them to potential theft. Additionally, database lookups to verify credentials for every single HTTP request create significant performance bottlenecks. 

An **Authentication Service** solves these problems by:
1. Validating credentials only once during login.
2. Issuing a short-lived **JWT token**.
3. Letting other services verify the token cryptographically without checking the database, enabling stateless and fast authorization.

---

## Technologies Used
- **Java 21**: The programming language version used.
- **Spring Boot 3.3.1**: The framework for building production-ready Spring applications.
- **Spring Security**: For securing endpoints and handling web security configurations.
- **JJWT (Java JWT - io.jsonwebtoken) 0.12.5**: A library for creating, signing, and parsing JWTs.
- **Maven**: Dependency management and build tool.
- **SLF4J**: Simple Logging Facade for Java (using standard Logback).

---

## Project Structure
Below is the directory structure showing the key files:

```text
jwt-auth-service/
│
├── pom.xml                                      # Maven dependencies and build configuration
├── README.md                                    # This project documentation
│
└── src/
    └── main/
        ├── java/
        │   └── com/
        │       └── cognizant/
        │           └── jwt/
        │               ├── Application.java     # App main class
        │               ├── config/
        │               │   └── SecurityConfig.java  # Spring Security rules
        │               ├── controller/
        │               │   └── AuthenticationController.java # Endpoints & Basic Auth parsing
        │               ├── model/
        │               │   └── JwtResponse.java # Standard token response payload
        │               └── util/
        │                   └── JwtUtil.java     # JWT creation & signing utility
        │
        └── resources/
            └── application.properties           # Port (8090) and secret key setup
```

---

## JWT Basics

### What is Authentication?
**Authentication** is the process of verifying **who** you are. For example, logging in with a username and password.

### What is Authorization?
**Authorization** is the process of verifying **what** you have permission to do. For example, checking if you are allowed to delete a record.

### What is JWT?
A **JSON Web Token (JWT)** is an open standard (RFC 7519) that defines a compact and self-contained way for securely transmitting information between parties as a JSON object. This information can be verified and trusted because it is digitally signed.

### Why JWT is used?
1. **Stateless**: The server does not need to store session records in memory/database.
2. **Compact**: Can be sent via HTTP headers (Bearer token) or URLs.
3. **Decentralized**: Any server with the secret key can decrypt/verify the token.

### Structure of JWT
A JWT is represented as three Base64URL-encoded parts separated by dots (`.`):
```text
Header.Payload.Signature
```

```text
+-----------------------+      +-----------------------+      +-----------------------+
|        HEADER         |      |        PAYLOAD        |      |       SIGNATURE       |
|                       |  .   |                       |  .   |                       |
|   Algorithm & Type    |      |  Claims (Data, Sub)   |      | Cryptographic Secret  |
|  e.g., {"alg": "HS256"|      | e.g., {"sub": "user"} |      | Verified by Server    |
+-----------------------+      +-----------------------+      +-----------------------+
```

1. **Header**: Contains metadata about the token (e.g., signing algorithm like HMAC-SHA256).
2. **Payload**: Contains the claims (e.g., username, expiration time, creation time).
3. **Signature**: Formed by combining the encoded header, encoded payload, and the secret key using the specified algorithm to prevent tampering.

---

## How Authentication Works

Here is the step-by-step workflow:

1. **Client** requests authentication by sending credentials via the `Authorization` header.
2. **Spring Security** receives the request, checks its configuration, and routes the request to `/authenticate` (which is configured as a public endpoint).
3. **Authentication Controller** extracts the `Authorization` header, decodes the Base64 credentials, and validates the username (`user`) and password (`pwd`).
4. **JWT Utility** builds a token using the verified username, sets expiration, signs it using HMAC-SHA256 with the secret key, and outputs the JWT string.
5. **Client** receives the generated JWT token in the JSON response payload.

---

## Request Flow

```text
 +--------+                   +-----------------+                 +-----------------------+
 | Client |                   | Spring Security |                 | AuthController/JwtUtil|
 +----+---+                   +--------+--------+                 +-----------+-----------+
      |                                |                                      |
      | 1. GET /authenticate           |                                      |
      |    (Authorization: Basic Base64)                                      |
      |------------------------------->|                                      |
      |                                | 2. Permit request to proceed         |
      |                                |------------------------------------->|
      |                                |                                      |
      |                                |                                      | 3. Read Header & Decode
      |                                |                                      | 4. Validate Credentials
      |                                |                                      | 5. Generate JWT Token
      |                                |                                      |
      |                                | 6. Return JWT (200 OK)               |
      |                                |<-------------------------------------|
      | 7. JSON response with Token    |                                      |
      |<-------------------------------|                                      |
      v                                v                                      v
```

---

## API Details

### Endpoint
`/authenticate`

### Method
`GET`

### Headers
- `Authorization`: `Basic <Base64-encoded credentials>`
- `Accept`: `application/json`

### Request example (Headers)
`Authorization: Basic dXNlcjpwd2Q=`

### Response (Success)
- **Status Code**: `200 OK`
- **Body**:
  ```json
  {
      "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyIiwiaWF0IjoxNzEzOTcwMDAwLCJleHAiOjE3MTM5NzM2MDB9.s7R..."
  }
  ```

### Response (Failure - Bad Credentials)
- **Status Code**: `401 Unauthorized`
- **Body**:
  ```json
  {
      "error": "Invalid username or password"
  }
  ```

---

## Curl Command
You can test the application using this standard `curl` command:

```bash
curl -u user:pwd http://localhost:8090/authenticate
```

### Explanation of the command:
- `curl`: The command-line tool for transferring data over HTTP.
- `-u user:pwd`: The credentials flag. Curl automatically encodes `user:pwd` to Base64 (`dXNlcjpwd2Q=`) and constructs the header: `Authorization: Basic dXNlcjpwd2Q=`.
- `http://localhost:8090/authenticate`: The URL of the authentication endpoint running on local port `8090`.

---

## Testing

### 1. Browser
Open your browser and navigate to:
```text
http://localhost:8090/authenticate
```
Because no Basic Authorization headers are passed, you will receive a response:
```json
{
    "error": "Missing or invalid Authorization header. Please use HTTP Basic authentication."
}
```

### 2. Postman
1. Create a new `GET` request to `http://localhost:8090/authenticate`.
2. Go to the **Authorization** tab.
3. Select **Basic Auth** from the Type dropdown.
4. Enter `user` in Username and `pwd` in Password.
5. Click **Send**.
6. View the JSON response containing the `"token"` field.

### 3. curl
Run the command in your command line:
```bash
curl -i -u user:pwd http://localhost:8090/authenticate
```

---

## Running the Project

### Clone Repository
```bash
git clone <repository-url>
```

### Navigate
```bash
cd week3/jwt-auth-service
```

### Build
```bash
mvn clean install
```

### Run
```bash
mvn spring-boot:run
```

---

## GitHub Setup (IDE Import)
1. **Clone & Open**: Clone the repository and open the workspace folder in IntelliJ IDEA or Eclipse.
2. **Maven Import**: Let the IDE automatically detect the Maven configuration (`pom.xml`) and import the dependencies.
3. **Run Application**: Find `Application.java` under `src/main/java/com/cognizant/jwt/Application.java`, right-click it, and select **Run 'Application.main()'**.
4. **No Additional Config**: No database or external brokers are required. The project starts immediately on port `8090`.

---

## Code Explanation

### 1. SecurityConfig.java
- `@Configuration`: Marks this class as a source of bean definitions for the application context.
- `@EnableWebSecurity`: Enables web security support in Spring Boot.
- `securityFilterChain(HttpSecurity http)`: Disables CSRF protection, opens access to the `/authenticate` endpoint, and configures the session strategy to be stateless.

### 2. AuthenticationController.java
- `@RestController`: Configures this class as a controller where every method returns data directly as JSON.
- `@RequestHeader("Authorization")`: Binds the incoming HTTP header to a method parameter.
- `Base64.getDecoder().decode(...)`: Decodes the credentials from Base64.
- `log.info(...)` & `log.error(...)`: Standard logging hooks using SLF4J at critical startup and shutdown stages.

### 3. JwtUtil.java
- `@Component`: Registers this class as a Spring Bean so it can be injected using `@Autowired`.
- `@Value("${jwt.secret}")`: Injects the secret signature key from `application.properties`.
- `Jwts.builder()`: Fluent API to create a new JWT instance.
- `signWith(SecretKey)`: Cryptographically signs the token with HMAC-SHA256.

### 4. JwtResponse.java
- Standard POJO that contains a getter for `"token"`. Spring automatically converts this object into JSON: `{"token":"..."}`.

### 5. Application.java
- `@SpringBootApplication`: Combines `@Configuration`, `@EnableAutoConfiguration`, and `@ComponentScan`.

---

## Common Errors

### 401 Unauthorized
* **Cause**: Credentials (`user` / `pwd`) are incorrect, or the `Authorization` header is missing/incorrectly structured.
* **Solution**: Ensure your curl request includes `-u user:pwd` or check your spelling.

### 403 Forbidden
* **Cause**: Attempting to access an endpoint other than `/authenticate` without setting up a token or configuration access rules.
* **Solution**: Only access `/authenticate` publicly.

### 404 Not Found
* **Cause**: Misspelled endpoint url or incorrect server port.
* **Solution**: Check that the URL is `http://localhost:8090/authenticate` (and not `/authentication` or other ports).

### Port Already In Use
* **Cause**: Port `8090` is occupied by another application.
* **Solution**: Stop the other application, or edit `server.port` inside `src/main/resources/application.properties` to a free port (e.g. `8091`).

### Invalid JWT
* **Cause**: Token signature verification failed (e.g., expired token, altered payload, or modified secret key).
* **Solution**: Generate a fresh token by calling `/authenticate` again.

---

## Interview Questions & Answers

### 1. What is Spring Security?
Spring Security is a powerful and highly customizable authentication and access-control framework for Spring applications. It is the de-facto standard for securing Spring-based applications.

### 2. What is the difference between Authentication and Authorization?
Authentication verifies the identity of a user (who they are), while Authorization determines their access rights (what they are allowed to do).

### 3. What is JWT?
JWT (JSON Web Token) is a compact, URL-safe, and self-contained standard (RFC 7519) to securely transmit information between parties as a JSON object.

### 4. What are the three parts of a JWT?
Header, Payload, and Signature.

### 5. How are the three parts of a JWT separated?
By dot characters (`.`).

### 6. What information is stored in the JWT Header?
The Header typically contains the signing algorithm (like HS256) and the token type (JWT).

### 7. What is stored in the JWT Payload?
The Payload contains "claims", which are statements about an entity (like the user) and additional metadata (like issues at and expiration times).

### 8. How is the JWT Signature generated?
By taking the encoded Header, the encoded Payload, a secret key, and signing them using the algorithm specified in the Header.

### 9. Why is a JWT signature important?
It allows the receiver to verify that the token was not manipulated or tampered with during transit.

### 10. What does "stateless authentication" mean?
It means the server does not store any session state or user authentication records. The client provides a JWT on every request, which contains all the information needed to authenticate it.

### 11. What is Basic Authentication?
Basic Authentication is a simple authentication scheme built into the HTTP protocol, where credentials are sent in the request header as a Base64-encoded string: `Basic <username:password>`.

### 12. Is Basic Authentication secure on its own?
No. Because Base64 is easily reversible, Basic Authentication is not secure unless transmitted over HTTPS (SSL/TLS).

### 13. How does Spring Security 6.x define security filters?
Instead of subclassing `WebSecurityConfigurerAdapter` (which is deprecated and removed), Spring Security 6.x uses a component-based model where we register a `SecurityFilterChain` bean.

### 14. What does `.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))` do?
It tells Spring Security not to create or use an `HttpSession` to store the security context, which is mandatory for stateless JWT-based systems.

### 15. What library did we use to build JWT in this project?
We used the standard Java JWT (JJWT) library by `io.jsonwebtoken`.

### 16. What is the role of `Keys.hmacShaKeyFor(secret.getBytes())`?
It generates a cryptographically secure HMAC secret key based on the bytes of the secret string specified in `application.properties`.

### 17. How can you read an HTTP header in a Spring Boot controller?
By using the `@RequestHeader` annotation in the controller method arguments.

### 18. Why do we disable CSRF in JWT-based APIs?
CSRF (Cross-Site Request Forgery) attacks exploit browser cookie behaviors. Since JWT is typically stored in local storage/session storage and sent via the `Authorization` header rather than cookies, CSRF protection is not needed.

### 19. What HTTP status code is returned when authentication fails?
`401 Unauthorized`.

### 20. What is a Bearer Token?
A Bearer Token is a security token that grants access to the "bearer" (the holder of the token). It is sent in the HTTP header as: `Authorization: Bearer <token>`.

---

## Key Takeaways
- **Http Basic Authentication** is used initially to securely supply credentials.
- **JWT** provides a compact, stateless token for subsequent authentication.
- **Spring Security** config controls endpoint visibility, disabling CSRF and enforcing statelessness.
- Proper logging of the **start** and **end** of lifecycle events improves traceability and debugging.
