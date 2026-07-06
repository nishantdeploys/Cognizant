# User and Order Management System (Microservices)

This project contains two Spring Boot microservices designed to showcase REST APIs, database persistence in MySQL/PostgreSQL, and synchronous inter-service communication using `WebClient` (Spring WebFlux).

---

## Architecture Overview

```mermaid
graph TD
    Client[Client / REST Tool] -->|Port 8081| UserService[User Service]
    Client -->|Port 8082| OrderService[Order Service]
    OrderService -->|HTTP GET /users/{id} via WebClient| UserService
    UserService -->|Read/Write| UserDB[(MySQL: user_service_db)]
    OrderService -->|Read/Write| OrderDB[(MySQL: order_service_db)]
```

- **User Service (`port 8081`)**: Manages user profiles (ID, Name, Email, Role).
- **Order Service (`port 8082`)**: Manages orders placed by users (ID, UserId, Product, Price, Status). When creating an order, it calls the User Service using `WebClient` to verify the user exists before saving the order.

---

## Technology Stack

1. **Spring Boot (3.3.1)**: Core framework.
2. **Spring Web**: To build REST APIs.
3. **Spring Data JPA**: For ORM and database operations.
4. **Spring WebFlux (WebClient)**: For reactive/non-blocking HTTP communication.
5. **MySQL Database**: Default persistence storage (automated schema creation and DB provisioning using `createDatabaseIfNotExist=true`).
6. **H2 Database**: Provided as a commented fallback in `application.properties` for easy in-memory zero-configuration testing.

---

## Database Configuration

By default, the microservices expect a running **MySQL** server on `localhost:3306` with username `root` and password `password`.

### Automated MySQL Setup
The database connection URLs are configured with `createDatabaseIfNotExist=true`. Therefore, you only need to ensure MySQL is running, and the databases (`user_service_db` and `order_service_db`) will be automatically created on startup.

### Fallback to H2 (In-Memory Database)
If you do not have MySQL running locally, you can run the applications using the in-memory **H2 Database** instead. To do this, open the `application.properties` file for each service and swap the commented lines:

#### User Service `application.properties`
```properties
# H2 Database for testing
spring.datasource.url=jdbc:h2:mem:userdb;DB_CLOSE_DELAY=-1
spring.datasource.username=sa
spring.datasource.password=
spring.datasource.driver-class-name=org.h2.Driver
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
```

#### Order Service `application.properties`
```properties
# H2 Database for testing
spring.datasource.url=jdbc:h2:mem:orderdb;DB_CLOSE_DELAY=-1
spring.datasource.username=sa
spring.datasource.password=
spring.datasource.driver-class-name=org.h2.Driver
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
```

---

## API Documentation

### 1. User Service (`http://localhost:8081`)

#### Create a User
* **Method**: `POST`
* **Path**: `/users`
* **Headers**: `Content-Type: application/json`
* **Request Body**:
```json
{
  "name": "Nishant Kumar",
  "email": "nishant@example.com",
  "role": "CUSTOMER"
}
```
* **Response (201 Created)**:
```json
{
  "id": 1,
  "name": "Nishant Kumar",
  "email": "nishant@example.com",
  "role": "CUSTOMER"
}
```

#### Get All Users
* **Method**: `GET`
* **Path**: `/users`
* **Response (200 OK)**:
```json
[
  {
    "id": 1,
    "name": "Nishant Kumar",
    "email": "nishant@example.com",
    "role": "CUSTOMER"
  }
]
```

#### Get User by ID
* **Method**: `GET`
* **Path**: `/users/{id}` (e.g. `/users/1`)
* **Response (200 OK or 404 Not Found)**:
```json
{
  "id": 1,
  "name": "Nishant Kumar",
  "email": "nishant@example.com",
  "role": "CUSTOMER"
}
```

#### Delete User
* **Method**: `DELETE`
* **Path**: `/users/{id}` (e.g. `/users/1`)
* **Response (204 No Content or 404 Not Found)**

---

### 2. Order Service (`http://localhost:8082`)

#### Create an Order
* **Method**: `POST`
* **Path**: `/orders`
* **Headers**: `Content-Type: application/json`
* **Request Body**:
```json
{
  "userId": 1,
  "product": "Gaming Laptop",
  "price": 1299.99,
  "status": "PENDING"
}
```
* **Process**: The Order Service calls `GET http://localhost:8081/users/1` using WebClient.
  - If the user exists (200 OK), the order is saved and returned.
  - If the user does not exist (404 Not Found), the order creation is rejected with a `400 Bad Request`.
* **Response (201 Created)**:
```json
{
  "id": 1,
  "userId": 1,
  "product": "Gaming Laptop",
  "price": 1299.99,
  "status": "PENDING"
}
```
* **Response (400 Bad Request - User Not Found)**:
```text
Cannot create order. User with ID 1 does not exist.
```

#### Get All Orders
* **Method**: `GET`
* **Path**: `/orders`
* **Response (200 OK)**:
```json
[
  {
    "id": 1,
    "userId": 1,
    "product": "Gaming Laptop",
    "price": 1299.99,
    "status": "PENDING"
  }
]
```

#### Get Order by ID
* **Method**: `GET`
* **Path**: `/orders/{id}`
* **Response (200 OK or 404 Not Found)**:
```json
{
  "id": 1,
  "userId": 1,
  "product": "Gaming Laptop",
  "price": 1299.99,
  "status": "PENDING"
}
```

#### Get Orders by User ID
* **Method**: `GET`
* **Path**: `/orders/user/{userId}` (e.g. `/orders/user/1`)
* **Response (200 OK)**:
```json
[
  {
    "id": 1,
    "userId": 1,
    "product": "Gaming Laptop",
    "price": 1299.99,
    "status": "PENDING"
  }
]
```

---

## How to Build and Run

### Step 1: Compile the entire Workspace
Navigate to the root directory `c:\luffy\LPUU\Projects\Cognizant` and compile the project to ensure everything is downloaded and built correctly:
```bash
mvn clean install -DskipTests
```

### Step 2: Start the User Service
Go to the `week4/OrderManagementSystem/user-service` directory and run:
```bash
mvn spring-boot:run
```

### Step 3: Start the Order Service
Go to the `week4/OrderManagementSystem/order-service` directory and run:
```bash
mvn spring-boot:run
```
