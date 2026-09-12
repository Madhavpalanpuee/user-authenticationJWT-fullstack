package com.madhav.user_auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UserAuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserAuthApplication.class, args);

		System.out.println("hello world");
	  System.out.println("==============================================");
        System.out.println("       USER AUTHENTICATION PROJECT");
        System.out.println("==============================================");

        System.out.println("STEP 01  → Project Planning");
        System.out.println("STEP 02  → Spring Boot Backend");
        System.out.println("STEP 03  → MySQL Database");
        System.out.println("STEP 04  → User Entity");
        System.out.println("STEP 05  → UserRepository");
        System.out.println("STEP 06  → UserService");
        System.out.println("STEP 07  → AuthController");
        System.out.println("STEP 08  → Registration API Testing");
        System.out.println("STEP 09  → React + Vite");
        System.out.println("STEP 10  → Register Page");
        System.out.println("STEP 11  → Axios Setup");
        System.out.println("STEP 12  → React → Spring Boot Connection");
        System.out.println("STEP 13  → Spring Security + BCrypt");
        System.out.println("STEP 14  → Login");
        System.out.println("STEP 15  → React Router + Home");
        System.out.println("STEP 16  → ProtectedRoute");
        System.out.println("STEP 17  → Axios Request Interceptor");
        System.out.println("STEP 18  → Dynamic Welcome User");
        System.out.println("STEP 19  → Logout");
        System.out.println("STEP 20  → Axios Response Interceptor");
        System.out.println("STEP 21  → Navbar");
        System.out.println("STEP 22  → Login ↔ Register Navigation");
        System.out.println("STEP 23  → CSS/UI");
        System.out.println("STEP 24  → Form Validation");
        System.out.println("STEP 25  → JWT Expiry + 401");
        System.out.println("STEP 26  → Complete Testing");

        System.out.println("==============================================");
        System.out.println("       PROJECT ROADMAP COMPLETED");
        System.out.println("==============================================");
	}

}

//🔥 Sabse important project flow — Interview ke liye                     


// REGISTER                                                 
//    ↓
// React
//    ↓
// Axios
//    ↓
// Controller
//    ↓
// Service
//    ↓
// BCrypt
//    ↓
// Repository
//    ↓
// MySQL


// LOGIN
//    ↓
// React
//    ↓
// Axios
//    ↓
// Controller
//    ↓
// Service
//    ↓
// findByEmail()
//    ↓
// BCrypt.matches()
//    ↓
// JwtUtil
//    ↓
// JWT
//    ↓
// React
//    ↓
// localStorage


// PROTECTED REQUEST
//    ↓
// React
//    ↓
// Axios Request Interceptor
//    ↓
// Bearer JWT
//    ↓
// Spring Security
//    ↓
// JwtAuthenticationFilter
//    ↓
// Validate JWT
//    ↓
// Extract Email
//    ↓
// SecurityContext
//    ↓
// Controller
//    ↓
// Service / Repository
//    ↓
// Database
//    ↓
// Response


// INVALID / EXPIRED JWT
//    ↓
// 401 Unauthorized
//    ↓
// Axios Response Interceptor
//    ↓
// Remove JWT
//    ↓
// Remove Name
//    ↓
// Login Page


// LOGOUT
//    ↓
// Remove JWT
//    ↓
// Remove Name
//    ↓
// Login

// Final Project Architecture


            //         ┌──────────────────────┐
            //         │       REACT          │
            //         │                      │
            //         │ Login                │
            //         │ Register             │
            //         │ Home                 │
            //         │ Navbar               │
            //         │ ProtectedRoute       │
            //         └──────────┬───────────┘
            //                    │
            //                  Axios
            //                    │
            //                    ▼
            //         ┌──────────────────────┐
            //         │    SPRING BOOT       │
            //         │                      │
            //         │ Controller           │
            //         │ Service              │
            //         │ Repository           │
            //         └──────────┬───────────┘
            //                    │
            //         ┌──────────┴──────────┐
            //         │                     │
            //         ▼                     ▼
            //  Spring Security          JPA/Hibernate
            //         │                     │
            //         ▼                     ▼
            //     JWT Filter              MySQL
            //         │
            //         ▼
            //   SecurityContext