# Web project with Spring Boot

### Using MVC pattern

<div align="center">
 <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/spring/spring-original-wordmark.svg" width="128" />
</div>

## Project description

    - Spring Boot
    - Spring Security
    - Spring Data JPA
    - Spring Validation
    - Model Mapper
    - PostgreSQL
    - Docker
    - Thymeleaf
    - Bootstrap

- [x] [Spring Boot](https://spring.io/projects/spring-boot)
- [x] [Spring Security](https://spring.io/projects/spring-security)
- [x] [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
- [x] [Spring Validation](https://spring.io/projects/spring-framework)
- [x] [Model Mapper](http://modelmapper.org/)
- [x] [Postgres](https://www.postgresql.org/)
- [x] [Docker](https://www.docker.com/)
- [x] [Thymeleaf](https://www.thymeleaf.org/)
- [x] [Bootstrap](https://getbootstrap.com/)

## Requirements

- [x] [Java 11](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)
- [x] [Maven](https://maven.apache.org/)
- [x] [Docker](https://www.docker.com/)

## Features

### Database tables

- [x] Categories
- [x] Addresses
- [x] Users
- [x] Houses
- [x] Chat
- [x] Users
- [x] User Permissions

## Running the application

### Docker

```bash
docker-compose up
```

### Spring boot application

```bash
$ mvn spring-boot:run or ./mvnw spring-boot:run
$ mvn clean package -DskipTests or ./mvnw clean package -DskipTests 
$ java -jar JAR_FILE_NAME.jar
```

## Environment variables

- Application-dev.yml

```yml
spring:
  config:
    import: optional:file:.env.[.properties]
  datasource:
    username: ${DB_USERNAME}
    password: ${DB_PASSWORD}
    url: jdbc:postgresql://${DB_HOST}:${DB_PORT}/${DB_NAME}
    driver-class-name: org.postgresql.Driver
  jpa:
    hibernate:
      ddl-auto: none
    database-platform: org.hibernate.dialect.PostgreSQLDialect
    show-sql: false
server:
  error:
    include-message: always
    include-stacktrace: never
 ```

# This project is MVC pattern

### Just configure your database and run the application
