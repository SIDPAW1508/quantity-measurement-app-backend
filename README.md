# Quantity Measurement API (Spring Boot)

## Overview

This project upgrades the Quantity Measurement application into a **Spring Boot REST API** using **Spring Data JPA**.
It exposes endpoints for performing conversions, comparisons, and arithmetic operations on measurements while storing history in a database.

---

## Tech Stack

* Java 11+
* Spring Boot
* Spring Data JPA (Hibernate)
* H2 Database (dev) / MySQL (prod)
* Maven
* Swagger (OpenAPI)
* Spring Security (basic setup)

---

## Features

* REST APIs for:

  * Conversion
  * Comparison
  * Arithmetic operations
* Persistent storage using JPA
* History tracking and retrieval
* Global exception handling
* Input validation
* Swagger API documentation
* Unit & integration testing
* Embedded Tomcat server

---

## Project Structure

```id="i9p0y2"
src/
 ├── controller/
 ├── service/
 ├── repository/
 ├── model/
 ├── dto/
 ├── exception/
 └── config/
```

---

## Setup Instructions

### 1. Build Project

```id="m1n8x2"
mvn clean install
```

### 2. Run Application

```id="k9s2jd"
mvn spring-boot:run
```

---

## API Base URL

```id="z0x9pl"
http://localhost:8080/api/v1/quantities
```

---

## Sample Endpoints

### Compare

```id="f82nsl"
POST /compare
```

### Convert

```id="g71kqp"
POST /convert
```

### Add

```id="p44lcm"
POST /add
```

### Get History

```id="v83mzn"
GET /history/{operation}
```

---

## Example Request

```json id="l0p9we"
{
  "thisQuantityDTO": {
    "value": 1.0,
    "unit": "FEET",
    "measurementType": "LengthUnit"
  },
  "thatQuantityDTO": {
    "value": 12.0,
    "unit": "INCHES",
    "measurementType": "LengthUnit"
  }
}
```

---

## Database

* Default: H2 (in-memory)
* Console:

```id="a9k2lw"
http://localhost:8080/h2-console
```

---

## Swagger Documentation

```id="q3w7er"
http://localhost:8080/swagger-ui.html
```

---

## Key Improvements (over JDBC version)

* Eliminates boilerplate code
* Uses ORM instead of manual SQL
* Declarative transaction management
* Clean layered architecture
* Better scalability and maintainability

---

## Testing

Run tests:

```id="t6y8ui"
mvn test
```

Includes:

* Controller tests (MockMvc)
* Integration tests (SpringBootTest)

---

## Author

Spring Boot Implementation (UC17)
