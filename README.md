# Employee Management Flyway API

A Spring Boot REST API for managing employee records with PostgreSQL database integration and Flyway database migrations.

This project demonstrates a production-style backend architecture using Spring Boot, Spring Data JPA, PostgreSQL, Flyway, DTOs, validation, and layered design.

---

# Technology Stack

- **Java:** 21
- **Framework:** Spring Boot 3.3.1
- **Build Tool:** Maven Wrapper
- **Database:** PostgreSQL
- **Database Migration:** Flyway
- **ORM:** Spring Data JPA + Hibernate
- **Validation:** Jakarta Bean Validation
- **Utility:** Lombok

---

# Project Features

- RESTful CRUD API for Employee management
- PostgreSQL database integration
- Database schema management using Flyway
- Automatic database migrations
- Sample data seeding
- DTO-based request and response handling
- Input validation
- Global exception handling
- Clean layered architecture

---

# Project Structure

```
employee-management-flyway-api/

├── src/
│   └── main/
│       ├── java/
│       │   └── com/himanshu/employeeflyway/
│       │       │
│       │       ├── controller/
│       │       │   └── EmployeeController.java
│       │       │
│       │       ├── service/
│       │       │   ├── EmployeeService.java
│       │       │   └── impl/
│       │       │       └── EmployeeServiceImpl.java
│       │       │
│       │       ├── repository/
│       │       │   └── EmployeeRepository.java
│       │       │
│       │       ├── entity/
│       │       │   └── Employee.java
│       │       │
│       │       ├── dto/
│       │       │   ├── EmployeeRequestDTO.java
│       │       │   └── EmployeeResponseDTO.java
│       │       │
│       │       ├── exception/
│       │       │   ├── ErrorResponse.java
│       │       │   ├── GlobalExceptionHandler.java
│       │       │   └── ResourceNotFoundException.java
│       │       │
│       │       └── EmployeeManagementApplication.java
│       │
│       └── resources/
│           │
│           ├── application.properties
│           │
│           └── db/
│               └── migration/
│                   ├── V1__create_employee_table.sql
│                   └── V2__insert_employee_data.sql
│
├── sample-queries.sql
├── pom.xml
├── mvnw
├── mvnw.cmd
└── README.md
```

---

# Database Configuration

This project uses PostgreSQL.

## Database Details

```
Host: localhost
Port: 5432
Database: employee_db
Username: postgres
```

The database password is not stored inside the source code.

The application reads it from an environment variable:

```
DB_PASSWORD
```

---

# Setting Database Password

## Windows PowerShell

Set the PostgreSQL password:

```powershell
$env:DB_PASSWORD="your_postgresql_password"
```

Then run the application:

```powershell
.\mvnw.cmd spring-boot:run
```

---

## Linux / macOS

```bash
export DB_PASSWORD="your_postgresql_password"

./mvnw spring-boot:run
```

---

# Creating Database

Create the PostgreSQL database:

```sql
CREATE DATABASE employee_db;
```

Flyway will automatically create the required tables when the application starts.

---

# JPA Configuration

The project uses:

```properties
spring.jpa.hibernate.ddl-auto=validate
```

This means:

- Hibernate validates the database schema.
- Hibernate does not create or modify tables.
- Flyway is responsible for database changes.

Database ownership flow:

```
Flyway
   |
   |
Creates and updates schema

Hibernate
   |
   |
Validates schema
```

---

# Flyway Migration

Migration files are located at:

```
src/main/resources/db/migration
```

## V1__create_employee_table.sql

Creates the employee table.

Schema:

| Column | Type |
|---|---|
| id | BIGSERIAL PRIMARY KEY |
| first_name | VARCHAR(100) |
| last_name | VARCHAR(100) |
| email | VARCHAR(150) |
| department | VARCHAR(100) |
| salary | DOUBLE PRECISION |

---

## V2__insert_employee_data.sql

Seeds the database with sample employee records.

Example data:

- John Doe
- Jane Smith
- Robert Johnson
- Emily Davis
- Michael Wilson

---

# Running the Application

## Compile

Windows:

```powershell
.\mvnw.cmd clean compile
```

Linux/macOS:

```bash
./mvnw clean compile
```

---

## Start Application

Windows:

```powershell
.\mvnw.cmd spring-boot:run
```

Linux/macOS:

```bash
./mvnw spring-boot:run
```

Application runs on:

```
http://localhost:8080
```

---

# API Documentation

Base URL:

```
http://localhost:8080/api/employees
```

---

# 1. Create Employee

### Request

```
POST /api/employees
```

Body:

```json
{
  "firstName": "Alice",
  "lastName": "Brown",
  "email": "alice@example.com",
  "department": "Engineering",
  "salary": 95000
}
```

Response:

```
201 Created
```

---

# 2. Get All Employees

```
GET /api/employees
```

Response:

```
200 OK
```

---

# 3. Get Employee By ID

```
GET /api/employees/{id}
```

Response:

```
200 OK
```

---

# 4. Update Employee

```
PUT /api/employees/{id}
```

Response:

```
200 OK
```

---

# 5. Delete Employee

```
DELETE /api/employees/{id}
```

Response:

```
204 No Content
```

---

# Error Handling

## Validation Error

Status:

```
400 Bad Request
```

Example:

```json
{
  "status":400,
  "message":"Validation failed"
}
```

---

## Employee Not Found

Status:

```
404 Not Found
```

Example:

```json
{
  "status":404,
  "message":"Employee not found"
}
```

---

# Sample SQL Queries

Additional database queries are available in:

```
sample-queries.sql
```

Includes:

- Retrieve all employees
- Search by department
- Search by salary
- Update salary
- Delete employee

---

# Architecture

```
Client

  |

  v

Controller

  |

  v

Service

  |

  v

Repository

  |

  v

PostgreSQL Database


Flyway manages schema migrations
```

---

# License

This project is created for educational and demonstration purposes.