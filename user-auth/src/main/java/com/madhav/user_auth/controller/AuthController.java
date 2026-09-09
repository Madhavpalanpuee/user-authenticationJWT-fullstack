package com.madhav.user_auth.controller; // Ye batata hai ki AuthController class kis package ke andar hai.

import org.springframework.security.core.Authentication; // Authenticated user ki information provide karta hai.
import com.madhav.user_auth.dto.AuthResponse; // Login ke baad token aur name return karne ke liye AuthResponse import kar rahe hain.
import com.madhav.user_auth.dto.LoginRequest; // Login request se email aur password lene ke liye LoginRequest import kar rahe hain.

import com.madhav.user_auth.entity.User; // User Entity ko import karta hai, jiska data registration ke time receive aur return hoga.
import com.madhav.user_auth.repository.UserRepository; // Database se user find karne ke liye.
import com.madhav.user_auth.service.UserService; // UserService ko import karta hai, jisme user registration ka actual business logic hai.

import org.springframework.web.bind.annotation.*; // Spring ke REST API related annotations ko import karta hai, jaise @RestController, @RequestMapping, @PostMapping aur @RequestBody.


@RestController // Spring ko batata hai ki ye class REST API Controller hai aur methods HTTP requests ko handle karenge.

@RequestMapping("/api/auth") // Is Controller ke sabhi API endpoints ka common base URL "/api/auth" hoga.

public class AuthController { // AuthController naam ki Controller class create ho rahi hai.


    private final UserService userService; // UserService ka reference store kar raha hai, jiske through registration ka business logic call hoga.
    private final UserRepository userRepository; // UserRepository ka reference store kar rahe hain


    public AuthController(UserService userService, UserRepository userRepository) { // Constructor hai; Spring automatically UserService ka object yahan provide karega.
        this.userService = userService; // Constructor se mila UserService object class ke userService variable mein store kar raha hai.
         this.userRepository = userRepository; // UserRepository ko variable mein store kar rahe hain
    }


    @PostMapping("/register") // POST request ke "/register" endpoint ko handle karega; complete URL "/api/auth/register" hoga.

    public User registerUser(@RequestBody User user) { // registerUser method request body se User object receive karega aur ek User object return karega.

        return userService.registerUser(user); // Received User object ko UserService ke registerUser() method mein bhejta hai aur saved User ko response mein return karta hai.
    }

  @PostMapping("/login") // Ye POST request ke "/login" endpoint ko handle karta hai; complete URL "/api/auth/login" hoga.

public AuthResponse login(@RequestBody LoginRequest request) { // login method request body se email aur password ko LoginRequest object mein receive karta hai aur User object return karta hai.

    return userService.loginUser( // UserService ke loginUser() method ko call karta hai aur uska result return karta hai.

            request.getEmail(), // LoginRequest object se user ka email nikalta hai aur loginUser() method ko pass karta hai.

            request.getPassword() // LoginRequest object se user ka password nikalta hai aur loginUser() method ko pass karta hai.

    ); // loginUser() method ka call yahan complete hota hai.
} // login method yahan close ho raha hai.


  @GetMapping("/home") // GET /api/auth/home endpoint create kar rahe hain.
    public String home(Authentication authentication) { // Spring Security authenticated user ka Authentication object dega.

        String email = authentication.getName(); // JWT/SecurityContext se authenticated user's email nikal rahe hain.

        User user = userRepository.findByEmail(email) // Email ke through database me user search kar rahe hain.
                .orElseThrow(() -> new RuntimeException("User not found")); // User nahi mila to exception throw karenge.

        return "Welcome " + user.getName() + "!"; // Database se actual name lekar welcome message return kar rahe hain.
}

} // AuthController class yahan close ho rahi hai.