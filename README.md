# RestApplication

A Spring Boot REST API for managing users with PostgreSQL, JPA, and Liquibase.

## Overview

This project is a lightweight backend application built with Java and Spring Boot. It exposes CRUD endpoints for user management and persists data in a PostgreSQL database. Liquibase is used to manage the database schema automatically during startup.

## Features

- User CRUD operations
- RESTful JSON API
- Spring Data JPA persistence
- Bean validation for request payloads
- PostgreSQL integration
- Liquibase database migrations
- Java 21 + Spring Boot 4

## Tech Stack

- Java 21
- Spring Boot 4.0.3
- Spring Web MVC
- Spring Data JPA
- Spring Validation
- PostgreSQL
- Liquibase
- Maven

## Project Structure

```text
RestApplication/
├── .mvn/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/train/rest/
│   │   │       ├── controller/
│   │   │       ├── dto/
│   │   │       ├── model/
│   │   │       ├── repository/
│   │   │       ├── service/
│   │   │       └── RestApplication.java
│   │   └── resources/
│   │       ├── application.yml
│   │       └── db/
│   │           └── changelog/
│   │               └── changelog-master.xml
│   └── test/
├── docker-compose.yml
├── pom.xml
├── mvnw
├── mvnw.cmd
├── .gitignore
└── .gitattributes
