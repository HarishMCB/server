
# MCB-Assessment Project

This project is a Java application built using Maven and Spring Boot. It serves as a basic institute management system, handling students, teachers, subjects, and their relationships, with features like JWT-based security, login attempt management, and scheduled services.

## Prerequisites

Ensure the following are installed on your system:
- **JDK 17**
- **Maven 3.x**

## How to Run the Application

1. **Clone the repository:**
   ```bash
   git clone <repository-url>
   ```

2. **Build the project and run tests:**
   ```bash
   mvn clean package
   ```

3. **Run the application:**
   After a successful build, you can start the application using the following command:
   ```bash
   mvn spring-boot:run -Drun.arguments="spring.profiles.active=test"
   ```

4. **Confirmation:**
   You should see the following log in the console indicating the application has started successfully:
   ```plaintext
   2017-08-29 17:31:23.091  INFO 19387 --- [main] s.b.c.e.t.TomcatEmbeddedServletContainer : Tomcat started on port(s): 8081 (http)
   2017-08-29 17:31:23.097  INFO 19387 --- [main] com.khoubyari.example.Application        : Started Application in 22.285 seconds (JVM running for 23.032)
   ```

## Application Overview

This application manages institute entities like students, teachers, and subjects. Data is stored in an in-memory H2 database, and Hibernate is used for ORM (Object-Relational Mapping). It features JWT-based authentication and authorization, along with functionality to track failed login attempts, reset invalid logins after 24 hours, and manage session security using Spring Security.

### Key Features:
- **JWT Authentication**: Secure APIs with token-based authentication.
- **Login Attempt Limits**: Limits failed login attempts and locks the user for 24 hours after 3 invalid attempts.
- **Scheduled Service**: A background job resets invalid login attempts after 24 hours.
- **Swagger**: Integrated for automatic API documentation.

## Available Endpoints

### 1. View Swagger API Documentation
You can explore the APIs through Swagger UI by visiting:
```
http://localhost:8081/swagger-ui/
```

### 2. Login and Generate JWT Token
**Endpoint:**
```
POST /api/login
```
**Request Body:**
```json
{
  "password": "MCB",
  "username": "Harish"
}
```
**Response:**
- HTTP 200: JWT token returned in the body

### 3. Authorization Header Setup
Once you have the JWT token, include it in the Authorization header of subsequent requests:
```
Authorization: Bearer <generated-jwt-token>
```

### 4. Register a New User
**Endpoint:**
```
POST /api/v1/register
```
**Request Body:**
```json
{
  "businessTitle": "consultant",
  "email": "dummyxyz@gmail.com",
  "password": "password",
  "phone": "1234567890",
  "username": "john"
}
```
**Response:**
- HTTP 201: User successfully created

### 5. Get Total Marks for a Student
**Endpoint:**
```
GET /api/v1/students/{studentId}/marks
```
**Response:**
- HTTP 200: Total marks of the student across all subjects

### 6. List Subjects with Marks for a Student
**Endpoint:**
```
GET /api/v1/students/{studentId}/subjects/marks
```
**Response:**
- HTTP 200: List of subjects with marks for the given student

### 7. Get Number of Students Under a Teacher
**Endpoint:**
```
GET /api/students/teachers/{teacherId}
```
**Response:**
- HTTP 200: Number of students assigned to the teacher

---

This project provides a simple example of using Spring Boot with security, scheduling, and persistence, ideal for managing basic institute-related data.
