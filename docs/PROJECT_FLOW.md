# React + Spring Boot User Authentication Project

## Complete Development Flow — Step 1 to 26

This document contains the complete step-by-step development flow of the React + Spring Boot User Authentication project.

---

# Step 1 — Project Requirements

## Goal

The main goal of the project is to implement:

```text
User Registration
       ↓
User Login
       ↓
JWT Token
       ↓
Protected Home Page
       ↓
Logout
```

## Frontend

* React.js
* Vite
* Axios
* React Router

## Backend

* Spring Boot
* Spring Security
* JWT
* Spring Data JPA
* Hibernate
* MySQL

## Basic Backend Flow

```text
React
  ↓
Spring Boot
  ↓
Controller
  ↓
Service
  ↓
Repository
  ↓
MySQL
```

---

# Step 2 — Spring Boot Project Setup

A Spring Boot project was created with the required dependencies.

## Required Dependencies

* Spring Web
* Spring Data JPA
* MySQL Driver
* Spring Security
* Validation
* JWT

## Backend Architecture

```text
React
  ↓
Spring Boot
  ↓
Controller
  ↓
Service
  ↓
Repository
  ↓
MySQL
```

The Controller receives the request, Service handles business logic, Repository communicates with the database, and MySQL stores the data.

---

# Step 3 — Database Setup

A MySQL database was created.

## Database

```text
user_auth
```

The user table contains:

```text
id
name
email
password
```

## Database Flow

```text
Spring Boot
     ↓
JPA
     ↓
Hibernate
     ↓
MySQL
```

JPA provides the abstraction for database operations, while Hibernate acts as the ORM implementation that communicates with MySQL.

---

# Step 4 — User Entity

A `User` entity was created to represent users in the application.

## User Fields

```text
id
name
email
password
```

The `@Entity` annotation tells JPA that the Java class should be mapped to a database table.

## Mapping Flow

```text
Java User Class
      ↓
     JPA
      ↓
  Hibernate
      ↓
Database Table
```

Example:

```text
User Object
     ↓
JPA / Hibernate
     ↓
Database Row
```

---

# Step 5 — User Repository

A `UserRepository` was created by extending:

```java
JpaRepository<User, Long>
```

This provides built-in CRUD operations.

## Available Operations

```text
save()
findById()
findAll()
deleteById()
```

A custom method was also created:

```java
findByEmail(String email)
```

This method is used to find a user by email during login.

## Flow

```text
Controller
    ↓
Service
    ↓
Repository
    ↓
Database
```

---

# Step 6 — User Service

A `UserService` was created to contain the business logic for user registration and login.

## Registration Flow

```text
User Data
    ↓
Password Encoding using BCrypt
    ↓
Repository.save()
    ↓
MySQL
```

The plain-text password should never be stored directly in the database.

Example:

```text
123456
   ↓
BCrypt
   ↓
$2a$10$...................
```

The database stores the BCrypt hash instead of the original password.

---

# Step 7 — Auth Controller

An `AuthController` was created to expose authentication APIs.

## Base URL

```text
/api/auth
```

## Registration Endpoint

```text
POST /api/auth/register
```

## Registration Flow

```text
React
  ↓
POST /api/auth/register
  ↓
AuthController
  ↓
UserService
  ↓
UserRepository
  ↓
MySQL
```

---

# Step 8 — React Project Setup

A React frontend project was created using Vite.

## Project

```text
user-auth-frontend
```

## Important Packages

```text
axios
react-router-dom
```

## Frontend Responsibilities

The frontend handles:

* User interface
* API calls
* JWT storage
* Page navigation
* Form validation
* Protected routes

---

# Step 9 — Register Page

A `Register.jsx` page was created.

## Registration Fields

```text
Name
Email
Password
```

## Flow

```text
User fills the form
        ↓
handleSubmit()
        ↓
registerUser()
        ↓
Axios
        ↓
Backend API
```

---

# Step 10 — Axios Service

An `authService.js` file was created to handle API communication.

An Axios instance was created with the backend base URL:

```text
http://localhost:8080/api/auth
```

## Why Use a Base URL?

Without a base URL, every API request would require the complete backend URL.

Instead of:

```javascript
axios.post("http://localhost:8080/api/auth/register")
```

we can use:

```javascript
api.post("/register")
```

The actual request becomes:

```text
http://localhost:8080/api/auth/register
```

This keeps API-related code cleaner.

---

# Step 11 — Register API Connection

The React frontend was connected to the Spring Boot registration API.

## Complete Flow

```text
React
  ↓
registerUser(userData)
  ↓
Axios POST
  ↓
/api/auth/register
  ↓
AuthController
  ↓
UserService
  ↓
BCrypt Password Encoding
  ↓
UserRepository.save()
  ↓
MySQL
```

After successful processing, the user is registered in the database.

---

# Step 12 — BCrypt Password Security

Passwords are not stored as plain text.

## Registration

If the user enters:

```text
123456
```

BCrypt converts it into a secure hash:

```text
123456
   ↓
BCrypt
   ↓
$2a$10$xxxxxxxxxxxxxxxx
```

The hash is stored in MySQL.

## Login Verification

During login:

```text
User Password
      ↓
BCrypt.matches()
      ↓
Stored Database Hash
      ↓
true / false
```

If the passwords match, authentication can continue.

---

# Step 13 — Login DTO

A `LoginRequest` DTO was created for login requests.

## Fields

```text
email
password
```

Frontend sends:

```json
{
  "email": "user@example.com",
  "password": "password"
}
```

The request is converted into a `LoginRequest` object.

## Flow

```text
React
  ↓
Login Request
  ↓
LoginRequest DTO
  ↓
Controller
```

## Purpose of DTO

A DTO (Data Transfer Object) represents the data being transferred between the frontend and backend.

---

# Step 14 — Login API

The login endpoint was created:

```text
POST /api/auth/login
```

## Login Flow

```text
React Login
     ↓
Axios
     ↓
AuthController
     ↓
UserService
     ↓
findByEmail()
     ↓
User Found?
   ↙       ↘
 NO        YES
 ↓          ↓
Error    BCrypt.matches()
             ↓
       Password Correct?
          ↙       ↘
        NO         YES
        ↓           ↓
      Error      Generate JWT
                    ↓
                 Response
```

The backend first finds the user using the email and then verifies the password using BCrypt.

---

# Step 15 — JWT Token

After successful login, the backend generates a JWT.

## JWT Contains

The generated token contains information such as:

```text
Subject      → User Email
Issued At    → Token Creation Time
Expiration   → Token Expiry Time
```

## JWT Flow

```text
User Email
    ↓
JwtUtil
    ↓
JWT Token
    ↓
AuthResponse
    ↓
React
```

Example response:

```json
{
  "token": "eyJhbGci...",
  "name": "Madhav"
}
```

The frontend stores the token for authenticated requests.

---

# Step 16 — React Router + Protected Route

React Router was configured for navigation.

## Routes

```text
/login
/register
/home
```

The `/home` route is protected.

## ProtectedRoute

The `ProtectedRoute` component checks whether a JWT token exists in `localStorage`.

```text
Token exists?
    ↙       ↘
  YES        NO
   ↓          ↓
 /home      /login
```

## Important

`ProtectedRoute` provides **frontend-level protection**.

Actual API security is handled by **Spring Security on the backend**.

---

# Step 17 — Axios Request Interceptor

An Axios Request Interceptor was created.

Before sending an API request, it checks `localStorage` for the JWT token.

## Flow

```text
React API Request
       ↓
Axios Request Interceptor
       ↓
Get JWT from localStorage
       ↓
Authorization Header
       ↓
Backend
```

The header is:

```text
Authorization: Bearer JWT
```

## Why Use Request Interceptor?

Without an interceptor, the token would have to be manually added to every protected API request.

The interceptor automatically attaches it.

---

# Step 18 — Spring Security + JWT Filter

Spring Security configuration was added to protect the backend APIs.

## Public Endpoints

```text
/api/auth/register → permitAll()
/api/auth/login    → permitAll()
```

These endpoints can be accessed without authentication.

## Protected Endpoints

Other endpoints require authentication:

```text
authenticated()
```

## JWT Filter

A `JwtAuthenticationFilter` was created.

## Authentication Flow

```text
Request
  ↓
Authorization Header
  ↓
Bearer Token
  ↓
JwtAuthenticationFilter
  ↓
Validate JWT
  ↓
Extract Email
  ↓
SecurityContext
  ↓
Controller
```

The filter reads the JWT from the Authorization header and validates it before allowing access to protected resources.

---

# Step 19 — Logout

A Logout button was added to the Navbar.

When the user logs out:

```text
localStorage.removeItem("token")
localStorage.removeItem("name")
```

Then the user is navigated to:

```text
/login
```

## Logout Flow

```text
Logout
  ↓
Remove JWT
  ↓
Remove Username
  ↓
Navigate to /login
```

---

# Step 20 — Axios Response Interceptor

An Axios Response Interceptor was added to handle authentication errors.

If the backend returns:

```text
401 Unauthorized
```

the response interceptor handles it.

## Flow

```text
Backend
   ↓
401 Unauthorized
   ↓
Axios Response Interceptor
   ↓
Check Status
   ↓
Remove Token
   ↓
Remove Name
   ↓
/login
```

This is especially useful when a JWT is invalid or expired.

---

# Step 21 — Navbar + Dynamic Home

A `Navbar` component was created.

The Navbar displays:

```text
User Auth App
Welcome, Username
Logout
```

The Home page requests a welcome message from the backend.

## Request

```text
GET /api/auth/home
```

## Complete Flow

```text
React
  ↓
GET /api/auth/home
  ↓
Axios Request Interceptor
  ↓
JWT Attached
  ↓
Spring Security
  ↓
JwtAuthenticationFilter
  ↓
Extract Email
  ↓
SecurityContext
  ↓
Authentication.getName()
  ↓
UserRepository.findByEmail()
  ↓
User Name
  ↓
"Welcome Madhav!"
```

The backend uses the authenticated user's email to find the corresponding user and return the user's name.

---

# Step 22 — Register ↔ Login Navigation

React Router's `Link` component was used for navigation between authentication pages.

## Register Page

```text
Already have an account?
        ↓
      Login
        ↓
     /login
```

## Login Page

```text
Don't have an account?
        ↓
      Register
        ↓
    /register
```

## Link vs useNavigate

### `Link`

Used when the user clicks a navigation link.

### `useNavigate()`

Used when navigation needs to happen programmatically through JavaScript.

---

# Step 23 — UI / CSS

Styling was added using `index.css`.

Some important CSS classes include:

```text
auth-container
auth-card
navbar
home-container
```

## Result

### Login

```text
Login Page
    ↓
Authentication Card
```

### Register

```text
Register Page
    ↓
Authentication Card
```

### Home

```text
Navbar
   +
Welcome Message
```

---

# Step 24 — Form Validation

Frontend validation was added to improve the user experience.

## Registration Validation

```text
Name required
Email required
Valid email format
Password required
Password minimum 6 characters
```

## Login Validation

```text
Email required
Valid email format
Password required
Password minimum 6 characters
```

## Validation Flow

```text
Submit
  ↓
Frontend Validation
  ↓
Data Correct?
  ↙       ↘
NO         YES
↓           ↓
Error      Backend API
```

## Important

Frontend validation and backend security have different purposes.

```text
Frontend Validation
        ↓
Better User Experience


Backend Validation/Security
        ↓
Actual Application Security
```

Frontend validation should never be considered a replacement for backend validation.

---

# Step 25 — JWT Expiry + 401 Handling

JWT expiration was configured.

Example:

```text
1000 * 60 * 60
```

This represents:

```text
1 hour
```

## Valid JWT

```text
Request
  ↓
JWT
  ↓
Valid
  ↓
Authentication
  ↓
Controller
  ↓
Response
```

## Expired / Invalid JWT

```text
Request
  ↓
JWT
  ↓
Invalid / Expired
  ↓
Authentication Not Created
  ↓
Spring Security
  ↓
401 Unauthorized
  ↓
Axios Response Interceptor
  ↓
Remove Token
  ↓
Remove Name
  ↓
/login
```

This ensures that an expired or invalid token cannot continue to access protected resources.

---

# Step 26 — Complete End-to-End Testing

The complete application was tested using multiple scenarios.

---

## Test 1 — Register Valid User

```text
React
 ↓
Backend
 ↓
BCrypt
 ↓
MySQL
```

### Result

Registration successful.

---

## Test 2 — Database Password Check

The password stored in the database was checked.

### Expected

The database should contain a BCrypt hash instead of the original password.

```text
Password
   ↓
BCrypt Hash
   ↓
MySQL
```

### Result

Password stored securely.

---

## Test 3 — Login With Valid Credentials

```text
Email + Password
       ↓
findByEmail()
       ↓
BCrypt.matches()
       ↓
JWT
       ↓
localStorage
       ↓
/home
```

### Result

Login successful and JWT stored in the browser.

---

## Test 4 — Protected Home API

Request:

```text
GET /api/auth/home
```

Authorization:

```text
Bearer JWT
```

### Result

```text
Welcome Madhav!
```

---

## Test 5 — Logout

```text
Remove Token
Remove Name
      ↓
   /login
```

### Result

User successfully logged out.

---

## Test 6 — Direct `/home` After Logout

After logout, the token no longer exists.

```text
Token does not exist
       ↓
ProtectedRoute
       ↓
/login
```

### Result

Unauthenticated user cannot access the Home page.

---

## Test 7 — Wrong Password

```text
BCrypt.matches()
       ↓
false
       ↓
Login Failed
```

### Result

Login rejected.

---

## Test 8 — Wrong Email

```text
findByEmail()
       ↓
User Not Found
       ↓
Login Failed
```

### Result

Login rejected.

---

## Test 9 — Empty Fields

```text
Empty Fields
     ↓
Frontend Validation
     ↓
Error
     ↓
Backend Request Not Sent
```

### Result

Validation prevents invalid form submission.

---

## Test 10 — Invalid JWT

```text
Invalid JWT
    ↓
401 Unauthorized
    ↓
Axios Response Interceptor
    ↓
Clear localStorage
    ↓
/login
```

### Result

User is redirected to login.

---

## Test 11 — Expired JWT

```text
Expired JWT
    ↓
401 Unauthorized
    ↓
Axios Response Interceptor
    ↓
Clear Token
    ↓
/login
```

### Result

Expired authentication is handled automatically.

---

# Final Project Flow

## Registration

```text
                    REGISTER
                       │
                       ▼
                React Register
                       │
                       ▼
                    Axios
                       │
                       ▼
             Spring Boot Controller
                       │
                       ▼
                  UserService
                       │
                       ▼
                BCrypt Password
                       │
                       ▼
                 UserRepository
                       │
                       ▼
                    MySQL
                       │
                       ▼
                   SUCCESS
```

---

# Login

```text
                     LOGIN
                       │
                       ▼
                  React Login
                       │
                       ▼
                     Axios
                       │
                       ▼
              AuthController
                       │
                       ▼
                  UserService
                       │
                       ▼
                findByEmail()
                       │
                       ▼
               BCrypt.matches()
                       │
                       ▼
                 Password OK
                       │
                       ▼
                  JwtUtil
                       │
                       ▼
                  JWT Token
                       │
                       ▼
                  AuthResponse
                       │
                       ▼
                    React
                       │
                       ▼
                 localStorage
```

---

# Protected API Request

```text
              PROTECTED API REQUEST
                       │
                       ▼
                     React
                       │
                       ▼
                     Axios
                       │
                       ▼
             Request Interceptor
                       │
                       ▼
             Get JWT from Storage
                       │
                       ▼
       Authorization: Bearer JWT
                       │
                       ▼
              Spring Security
                       │
                       ▼
          JwtAuthenticationFilter
                       │
                 ┌─────┴─────┐
                 │           │
              VALID       INVALID
                 │           │
                 ▼           ▼
        Extract Email       401
                 │           │
                 ▼           ▼
        SecurityContext    Response
                 │          Interceptor
                 ▼           │
             Controller      ▼
                 │       Clear Storage
                 ▼           │
             Repository       ▼
                 │          /login
                 ▼
              Database
                 │
                 ▼
              Response
```

---

# Logout

```text
                    LOGOUT
                       │
                       ▼
                 Logout Button
                       │
                       ▼
              Remove JWT Token
                       │
                       ▼
              Remove User Name
                       │
                       ▼
                    /login
```

---

# Complete Architecture

```text
              ┌─────────────────────┐
              │      FRONTEND       │
              │   React + Vite      │
              └──────────┬──────────┘
                         │
                       Axios
                         │
                         ▼
              ┌─────────────────────┐
              │       BACKEND       │
              │     Spring Boot     │
              └──────────┬──────────┘
                         │
              ┌──────────┴──────────┐
              │                     │
              ▼                     ▼
       Spring Security           Controller
              │                     │
              ▼                     ▼
       JWT Filter                Service
                                    │
                                    ▼
                               Repository
                                    │
                                    ▼
                              JPA / Hibernate
                                    │
                                    ▼
                                 MySQL
```

---

# Complete Authentication Architecture

```text
                    USER
                     │
                     ▼
              React Frontend
                     │
                     ▼
                   Axios
                     │
          ┌──────────┴──────────┐
          │                     │
          ▼                     ▼
      Register                 Login
          │                     │
          ▼                     ▼
     Spring Boot          Spring Boot
          │                     │
          ▼                     ▼
      Controller             Controller
          │                     │
          ▼                     ▼
       Service                Service
          │                     │
          ▼                     ▼
       BCrypt              findByEmail()
          │                     │
          ▼                     ▼
     Repository           BCrypt.matches()
          │                     │
          ▼                     ▼
        MySQL                JwtUtil
                                │
                                ▼
                             JWT Token
                                │
                                ▼
                          localStorage
                                │
                                ▼
                       Protected Requests
                                │
                                ▼
                         JWT Filter
                                │
                                ▼
                       SecurityContext
                                │
                                ▼
                         Protected API
```

---

# Key Concepts Learned

Through this project, the following concepts were implemented and practiced:

## Frontend

* React.js
* Vite
* React Router
* Protected Routes
* Axios
* Axios Request Interceptor
* Axios Response Interceptor
* `localStorage`
* Form validation
* Component-based architecture

## Backend

* Spring Boot
* REST APIs
* Controller-Service-Repository architecture
* Spring Security
* JWT authentication
* JWT validation
* JWT expiration
* BCrypt password hashing
* DTOs
* Spring Data JPA
* Hibernate
* CORS
* Stateless authentication

## Database

* MySQL
* JPA entity mapping
* Repository operations
* User data persistence

## Development Tools

* Maven
* Git
* GitHub
* Postman
* VS Code

---

# Overall Learning Flow

```text
React
  ↓
Axios
  ↓
REST API
  ↓
Spring Boot
  ↓
Controller
  ↓
Service
  ↓
Spring Security
  ↓
JWT
  ↓
Repository
  ↓
JPA / Hibernate
  ↓
MySQL
```

This project provides a complete practical understanding of how a modern full-stack JWT authentication system works from frontend to backend and database.
