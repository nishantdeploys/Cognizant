# Exercise 4: Creating and Configuring a Maven Project

This project demonstrates the creation and configuration of a Maven project for a library management application, integrating Spring dependencies and configuring the Maven Compiler Plugin for Java 1.8 compatibility.

## 📋 Scenario & Requirements

Set up a Maven project named `LibraryManagement` and configure its build environment and dependencies:
1. **Maven Project Setup**: Set up the Maven project named `LibraryManagement`.
2. **Spring Dependencies**: Add the dependencies for Spring Context, Spring AOP, and Spring WebMVC in the `pom.xml` file.
3. **Maven Compiler Plugin**: Configure the compiler plugin for Java version 1.8 in the `pom.xml` file.

---

## 🛠️ Maven Configuration (`pom.xml`)

The project's [pom.xml](file:///c:/luffy/LPUU/Projects/Cognizant/week2/LibraryManagement/pom.xml) has been configured as follows:

```xml
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>com.library</groupId>
    <artifactId>LibraryManagement</artifactId>
    <version>1.0-SNAPSHOT</version>
    <packaging>jar</packaging>

    <properties>
        <!-- Java 1.8 Source & Target Compatibility -->
        <maven.compiler.source>1.8</maven.compiler.source>
        <maven.compiler.target>1.8</maven.compiler.target>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <!-- Spring 5.x version used for Java 1.8 support -->
        <spring.version>5.3.31</spring.version>
    </properties>

    <dependencies>
        <!-- 1. Spring Context Dependency -->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-context</artifactId>
            <version>${spring.version}</version>
        </dependency>

        <!-- 2. Spring AOP Dependency -->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-aop</artifactId>
            <version>${spring.version}</version>
        </dependency>

        <!-- 3. Spring WebMVC Dependency -->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-webmvc</artifactId>
            <version>${spring.version}</version>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <!-- Maven Compiler Plugin configured for Java version 1.8 -->
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>3.8.1</version>
                <configuration>
                    <source>1.8</source>
                    <target>1.8</target>
                </configuration>
            </plugin>
        </plugins>
    </build>
</project>
```

---

## 🔍 Dependency Details

* **`spring-context`**: Provides the core container, dependency injection (DI), and application context features of Spring.
* **`spring-aop`**: Enables Aspect-Oriented Programming support, allowing separation of cross-cutting concerns (such as logging, transaction management, and security).
* **`spring-webmvc`**: Provides Spring's model-view-controller (MVC) architecture for building web applications.
* **Java 1.8 Compatibility**: Set via standard Maven compiler properties and explicitly declared in the `maven-compiler-plugin` configuration.
