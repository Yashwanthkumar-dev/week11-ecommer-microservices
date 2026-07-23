# 🛒 E-Commerce Microservices Platform

## 📌 Project Overview

This project is a Microservices-based E-Commerce application developed using Spring Boot and Spring Cloud. The application follows the Microservices Architecture pattern where each service has its own responsibility and database.

The project demonstrates service discovery, API Gateway routing, inter-service communication using OpenFeign, DTO architecture, exception handling, and PostgreSQL integration.

---

# 🚀 Tech Stack

- Java 21
- Spring Boot 3.x
- Spring Cloud
- Spring Data JPA
- OpenFeign
- Netflix Eureka
- Spring Cloud Gateway
- PostgreSQL
- Maven
- Lombok
- Postman

---

# 🏗 Project Architecture

```
                API Gateway
                     │
         ┌───────────┼────────────┐
         │           │            │
         ▼           ▼            ▼
 Product Service  User Service  Order Service
         │           │            │
         ▼           ▼            ▼
 PostgreSQL     PostgreSQL    PostgreSQL

               Eureka Server
```

---

# 📦 Services

## Product Service

Responsibilities

- Product CRUD
- Product Validation
- Product Search
- Stock Management

---

## User Service

Responsibilities

- User CRUD
- User Validation

---

## Order Service

Responsibilities

- Place Order
- Update Order
- Delete Order
- Get Orders
- User Validation
- Product Validation
- Total Price Calculation
- Stock Validation
- Reduce Product Stock

---

# 🔄 Microservice Communication

Order Service communicates with

- User Service
- Product Service

using **OpenFeign Client**

Flow

```
Client
    │
    ▼
API Gateway
    │
    ▼
Order Service
    │
    ├────────► User Service
    │
    └────────► Product Service
```

---

# 🛠 Features

✅ Product CRUD

✅ User CRUD

✅ Order CRUD

✅ DTO

✅ Response DTO

✅ Global Exception Handler

✅ API Gateway

✅ Eureka Service Discovery

✅ OpenFeign Communication

✅ Stock Validation

✅ Stock Reduction

---

# 📂 Project Structure

```
week11-ecommerce-microservices

│── api-gateway
│── eureka-server
│── product-services
│── user-service
│── order-services
```

---

# ⚙ Installation

## Clone Repository

```bash
git clone https://github.com/Yashwanthkumar-dev/week11-ecommer-microservices.git
```

## Start Services

1. Eureka Server

2. Product Service

3. User Service

4. Order Service

5. API Gateway

---

# 🌐 API Endpoints

## Product

GET

```
/api/product
```

POST

```
/api/product
```

PUT

```
/api/product/{id}
```

DELETE

```
/api/product/{id}
```

---

## User

GET

```
/api/user
```

POST

```
/api/user
```

PUT

```
/api/user/{id}
```

DELETE

```
/api/user/{id}
```

---

## Order

POST

```
/api/orders
```

GET

```
/api/orders
```

GET

```
/api/orders/{id}
```

PUT

```
/api/orders/{id}
```

DELETE

```
/api/orders/{id}
```

---

# 📸 Screenshots

Add screenshots of

- Eureka Dashboard
- API Gateway
- Product CRUD
- User CRUD
- Order CRUD
- PostgreSQL Tables
- Postman Testing

---

# 👨‍💻 Developed By

Yashwanth Kumar
