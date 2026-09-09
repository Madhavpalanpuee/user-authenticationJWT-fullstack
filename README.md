# 🔐 React + Spring Boot JWT User Authentication

A full-stack **User Authentication System** built using **React.js** and **Spring Boot**, implementing secure authentication with **JWT (JSON Web Token)**, **Spring Security**, **BCrypt password hashing**, and **MySQL**.

The project demonstrates a complete authentication flow including user registration, login, JWT token generation, protected routes, logout, automatic token handling with Axios interceptors, and JWT expiry handling.

---

## 🚀 Features

* 👤 User Registration
* 🔑 User Login
* 🔒 Password encryption using BCrypt
* 🎟️ JWT token generation
* 🛡️ JWT-based authentication using Spring Security
* 🚪 Protected Home Page
* 🔄 Automatic JWT token attachment using Axios Request Interceptor
* ⚠️ Automatic logout on `401 Unauthorized` using Axios Response Interceptor
* 🚪 Logout functionality
* 🧑 Dynamic welcome message using authenticated user's name
* ✅ Frontend form validation
* 🌐 React Router protected routes
* 🗄️ MySQL database integration
* 🔐 Environment variables for sensitive configuration
* ⚡ REST API based architecture

---

# 🛠️ Tech Stack

## Frontend

* React.js
* Vite
* JavaScript
* Axios
* React Router DOM
* HTML
* CSS

## Backend

* Java
* Spring Boot
* Spring Security
* Spring Data JPA
* Hibernate
* JWT
* BCrypt
* Maven

## Database

* MySQL

---

# 🏗️ Project Architecture

```text
                    ┌─────────────────────┐
                    │      React.js       │
                    │      Frontend       │
                    └──────────┬──────────┘
                               │
                               │ HTTP / REST API
                               ▼
                    ┌─────────────────────┐
                    │    Spring Boot      │
                    │      Backend        │
                    └──────────┬──────────┘
                               │
                 ┌─────────────┼─────────────┐
                 │             │             │
                 ▼             ▼             ▼
           Controller       Service      Security
                 │             │             │
                 │             ▼             ▼
                 │         Repository    JWT Filter
                 │             │
                 ▼             ▼
                    ┌─────────────────────┐
                    │       MySQL         │
                    │     user_auth       │
                    └─────────────────────┘
```

---

# 📂 Project Structure

```text
react+spring boot crud/
│
├── .gitignore
│
├── user-auth/                         # Spring Boot Backend
│   │
│   ├── src/
│   │   └── main/
│   │       ├── java/
│   │       │   └── com/madhav/user_auth/
│   │       │       │
│   │       │       ├── controller/
│   │       │       │   └── AuthController.java
│   │       │       │
│   │       │       ├── dto/
│   │       │       │   ├── LoginRequest.java
│   │       │       │   └── AuthResponse.java
│   │       │       │
│   │       │       ├── entity/
│   │       │       │   └── User.java
│   │       │       │
│   │       │       ├── repository/
│   │       │       │   └── UserRepository.java
│   │       │       │
│   │       │       ├── service/
│   │       │       │   └── UserService.java
│   │       │       │
│   │       │       └── security/
│   │       │           ├── JwtUtil.java
│   │       │           ├── JwtAuthenticationFilter.java
│   │       │           └── SecurityConfig.java
│   │       │
│   │       └── resources/
│   │           └── application.properties
│   │
│   └── pom.xml
│
│
└── user-auth-frontend/                # React Frontend
    │
    ├── src/
    │   │
    │   ├── components/
    │   │   ├── ProtectedRoute.jsx
    │   │   ├── Navbar.jsx
    │   │   └── Button.jsx
    │   │
    │   ├── pages/
    │   │   ├── Register.jsx
    │   │   ├── Login.jsx
    │   │   └── Home.jsx
    │   │
    │   ├── services/
    │   │   └── authService.js
    │   │
    │   ├── App.jsx
    │   ├── main.jsx
    │   └── index.css
    │
    ├── package.json
    └── vite.config.js
```

---

# 🔄 Application Flow

## 1️⃣ User Registration

```text
React Register Page
        │
        ▼
User enters name, email & password
        │
        ▼
Axios POST Request
        │
        ▼
POST /api/auth/register
        │
        ▼
AuthController
        │
        ▼
UserService
        │
        ▼
BCrypt Password Encryption
        │
        ▼
UserRepository
        │
        ▼
MySQL Database
```

The user's password is **never stored as plain text**.

Instead:

```text
Original Password
       │
       ▼
BCrypt
       │
       ▼
Encrypted Password
       │
       ▼
MySQL
```

---

# 🔑 Login Flow

```text
React Login Page
        │
        ▼
Email + Password
        │
        ▼
Axios POST Request
        │
        ▼
POST /api/auth/login
        │
        ▼
AuthController
        │
        ▼
UserService
        │
        ▼
Find User by Email
        │
        ▼
BCrypt Password Verification
        │
        ▼
JWT Token Generation
        │
        ▼
Token + Username
        │
        ▼
React
        │
        ▼
localStorage
```

After successful login, the JWT token is stored in the browser's `localStorage`.

---

# 🎟️ JWT Authentication Flow

For protected API requests:

```text
React Application
       │
       ▼
Axios Request
       │
       ▼
Request Interceptor
       │
       ▼
Get JWT from localStorage
       │
       ▼
Authorization Header
       │
       ▼
Bearer <JWT_TOKEN>
       │
       ▼
Spring Security
       │
       ▼
JwtAuthenticationFilter
       │
       ▼
Validate JWT
       │
       ▼
Extract User Email
       │
       ▼
SecurityContext
       │
       ▼
Protected API
```

---

# 🔒 Protected Route

The `/home` page is protected using React Router.

```text
User opens /home
       │
       ▼
ProtectedRoute
       │
       ├── Token exists
       │      │
       │      ▼
       │    Home Page
       │
       └── Token does not exist
              │
              ▼
          Login Page
```

---

# 🚪 Logout Flow

When the user clicks the logout button:

```text
Logout Button
      │
      ▼
Remove JWT Token
      │
      ▼
Remove Username
      │
      ▼
Navigate to /login
```

The frontend removes:

```javascript
localStorage.removeItem("token");
localStorage.removeItem("name");
```

---

# ⚠️ JWT Expiry & 401 Handling

The JWT token has a limited expiration time.

When an expired or invalid token is used:

```text
Frontend Request
       │
       ▼
Spring Security
       │
       ▼
Invalid / Expired JWT
       │
       ▼
401 Unauthorized
       │
       ▼
Axios Response Interceptor
       │
       ▼
Remove Token
       │
       ▼
Redirect to Login
```

This prevents the user from continuing with an invalid authentication token.

---

# 🌐 Axios Interceptors

The project uses two Axios interceptors.

## Request Interceptor

Before sending a request:

```text
Request
   │
   ▼
Get Token from localStorage
   │
   ▼
Attach Authorization Header
   │
   ▼
Send Request
```

Example:

```text
Authorization: Bearer <JWT_TOKEN>
```

## Response Interceptor

If the backend returns:

```text
401 Unauthorized
```

the frontend:

1. Removes the token
2. Removes the stored username
3. Redirects the user to `/login`

---

# 🔐 Security Features

### BCrypt Password Hashing

Passwords are encrypted using:

```text
BCryptPasswordEncoder
```

### JWT Authentication

JWT is used for stateless authentication.

### Stateless Session

Spring Security uses:

```text
SessionCreationPolicy.STATELESS
```

The server does not maintain traditional login sessions.

### Protected APIs

Authentication is required for protected endpoints.

### CORS Configuration

The backend allows requests from the React development server.

```text
http://localhost:5173
```

### Environment Variables

Sensitive information such as:

* MySQL password
* JWT secret

is not hardcoded in the source code.

---

# 📡 API Endpoints

| Method | Endpoint             | Authentication | Description            |
| ------ | -------------------- | -------------- | ---------------------- |
| POST   | `/api/auth/register` | ❌ No           | Register a new user    |
| POST   | `/api/auth/login`    | ❌ No           | Login and generate JWT |
| GET    | `/api/auth/home`     | ✅ Yes          | Access protected home  |

---

# 🗄️ Database Configuration

Create a MySQL database:

```sql
CREATE DATABASE user_auth;
```

The application uses:

```text
Database Name: user_auth
Username: root
```

The MySQL password is read from the environment variable:

```text
DB_PASSWORD
```

---

# 🔐 Environment Variables

The backend requires the following environment variables:

```text
DB_PASSWORD
JWT_SECRET
```

## Windows CMD

Set your MySQL password:

```cmd
setx DB_PASSWORD "your_mysql_password"
```

Set your JWT secret:

```cmd
setx JWT_SECRET "your_long_jwt_secret"
```

After using `setx`, **close and reopen the terminal** so the new environment variables are available.

> Never commit your real database password or JWT secret to GitHub.

---

# ⚙️ Backend Configuration

`application.properties` uses the environment variable:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/user_auth
spring.datasource.username=root
spring.datasource.password=${DB_PASSWORD}

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
```

JWT secret is loaded from:

```text
JWT_SECRET
```

---

# ▶️ How to Run the Project

## Prerequisites

Make sure you have installed:

* Java JDK
* Maven
* Node.js
* npm
* MySQL
* Git

---

## 1️⃣ Clone the Repository

Clone the project repository to your local machine.

Then open the project folder in VS Code or IntelliJ IDEA.

---

## 2️⃣ Start MySQL

Make sure MySQL is running.

Create the database:

```sql
CREATE DATABASE user_auth;
```

---

## 3️⃣ Configure Environment Variables

Windows CMD:

```cmd
setx DB_PASSWORD "your_mysql_password"
setx JWT_SECRET "your_long_jwt_secret"
```

Restart your terminal after setting them.

---

## 4️⃣ Start Spring Boot Backend

Open a terminal inside:

```text
user-auth
```

Run:

```cmd
mvnw.cmd spring-boot:run
```

The backend will start on:

```text
http://localhost:8080
```

---

## 5️⃣ Start React Frontend

Open another terminal and go to:

```text
user-auth-frontend
```

Install dependencies:

```bash
npm install
```

Start the development server:

```bash
npm run dev
```

The frontend will run on:

```text
http://localhost:5173
```

---

# 🧪 Testing Flow

The complete application can be tested using the following flow:

```text
1. Open Register Page
          ↓
2. Create New Account
          ↓
3. Password Stored as BCrypt Hash
          ↓
4. Go to Login
          ↓
5. Login with Email & Password
          ↓
6. JWT Token Generated
          ↓
7. Token Stored in localStorage
          ↓
8. Open Protected Home Page
          ↓
9. Axios Adds JWT Automatically
          ↓
10. Backend Validates JWT
          ↓
11. Welcome Message Displayed
          ↓
12. Logout
          ↓
13. Token Removed
          ↓
14. User Redirected to Login
```

---

# 🧩 Backend Request Flow

```text
Client
  │
  ▼
Controller
  │
  ▼
Service
  │
  ▼
Repository
  │
  ▼
MySQL
```

For authentication:

```text
Client
  │
  ▼
Spring Security
  │
  ▼
JwtAuthenticationFilter
  │
  ▼
JwtUtil
  │
  ▼
SecurityContext
  │
  ▼
Protected Controller
```

---

# 📁 Main Backend Components

### `User.java`

Entity class representing users in the database.

### `UserRepository.java`

Provides database operations using Spring Data JPA.

### `UserService.java`

Contains registration and login business logic.

### `AuthController.java`

Provides authentication REST APIs.

### `LoginRequest.java`

DTO used for receiving login credentials.

### `AuthResponse.java`

DTO used for returning JWT token and username.

### `JwtUtil.java`

Responsible for:

* Generating JWT
* Extracting email from JWT
* Validating JWT
* Checking token expiration

### `JwtAuthenticationFilter.java`

Reads the JWT from the Authorization header and authenticates the user.

### `SecurityConfig.java`

Configures:

* Spring Security
* CORS
* CSRF
* Stateless sessions
* Public endpoints
* Protected endpoints
* JWT filter

---

# 📁 Main Frontend Components

### `Register.jsx`

Provides the user registration form.

### `Login.jsx`

Provides the login form.

### `Home.jsx`

Displays the protected home page and welcome message.

### `ProtectedRoute.jsx`

Prevents unauthenticated users from accessing protected pages.

### `Navbar.jsx`

Displays navigation and logout functionality.

### `authService.js`

Handles API communication using Axios and JWT interceptors.

---

# 🔄 Complete Full-Stack Flow

```text
                    USER
                     │
                     ▼
              React Frontend
                     │
          ┌──────────┴──────────┐
          │                     │
          ▼                     ▼
      Register                 Login
          │                     │
          ▼                     ▼
       Axios                 Axios
          │                     │
          └──────────┬──────────┘
                     ▼
              Spring Boot API
                     │
                     ▼
                Controller
                     │
                     ▼
                 Service
                     │
            ┌────────┴────────┐
            │                 │
            ▼                 ▼
          BCrypt             JWT
            │                 │
            └────────┬────────┘
                     ▼
                 Repository
                     │
                     ▼
                   MySQL
```

---

# 🎯 Learning Outcomes

By building this project, I learned and practiced:

* React component development
* React Router
* Protected routes
* Axios
* Axios interceptors
* REST APIs
* Spring Boot
* Spring Security
* JWT authentication
* BCrypt password hashing
* Spring Data JPA
* Hibernate
* MySQL integration
* DTOs
* Controller-Service-Repository architecture
* CORS
* CSRF
* Stateless authentication
* Environment variables
* Error handling
* Git & GitHub

---

# 🔮 Future Improvements

Possible improvements for the project:

* 🔄 Refresh Token implementation
* 📧 Email verification
* 🔑 Forgot Password functionality
* 🔐 Password Reset using email
* 👥 Role-based authorization
* 🛡️ User/Admin roles
* 🚀 Deploy frontend and backend
* ☁️ Cloud database integration
* 🌍 Production environment configuration
* 🧪 Unit and integration testing
* 📊 Admin dashboard

---

# 📚 Project Documentation

The detailed step-by-step development process from project setup to JWT authentication and testing can be maintained separately in:

```text
docs/PROJECT_FLOW.md
```

This keeps the main README clean while preserving the complete learning/development documentation.

---

# 👨‍💻 Author

**Madhav Palanpure**

Software Developer

---

# ⭐ Project

If you find this project useful for learning full-stack authentication, feel free to explore the source code and use it as a reference for your own projects.
