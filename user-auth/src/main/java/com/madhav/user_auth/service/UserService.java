package com.madhav.user_auth.service; // Ye batata hai ki UserService class kis package ke andar hai.

import com.madhav.user_auth.dto.AuthResponse;
import com.madhav.user_auth.entity.User; // User Entity class ko import karta hai, jiske data ke saath service kaam karegi.
import com.madhav.user_auth.repository.UserRepository; // UserRepository ko import karta hai, jiske through database ke operations perform honge.
import com.madhav.user_auth.security.JwtUtil; // JWT token generate karne ke liye JwtUtil import kar rahe hain.

import org.springframework.security.crypto.password.PasswordEncoder; // Password ko BCrypt hash aur verify karne ke liye.
import org.springframework.stereotype.Service; // Spring ka @Service annotation import karta hai.

import java.util.Optional; // Java ki Optional class ko import karta hai, jisse hum kisi value ke present ya absent hone ko safely handle kar sakte hain.

@Service // Spring ko batata hai ki UserService ek Service class hai aur iska object Spring khud manage karega.
public class UserService { // UserService naam ki class create ho rahi hai.


    private final UserRepository userRepository; // UserRepository ka reference store kar raha hai; final ka matlab hai ki ise baad mein kisi doosre object se replace nahi kar sakte.
    private final JwtUtil jwtUtil; // JWT generate karne wale JwtUtil object ko store kar rahe hain.

       private final PasswordEncoder passwordEncoder; // Password ko BCrypt se hash/verify karne ke liye.

                                                                        // jo ye passwordEncoder he ye SecurityConfig se PasswordEncoder Bean milega.
    public UserService(UserRepository userRepository, JwtUtil jwtUtil , PasswordEncoder passwordEncoder ) { // Constructor ke through Spring dono dependencies provide karega.

        this.userRepository = userRepository; // Repository object ko class variable mein assign kar rahe hain.

        this.jwtUtil = jwtUtil; // JwtUtil object ko class variable mein assign kar rahe hain.

                this.passwordEncoder = passwordEncoder; // Inject hua PasswordEncoder class variable mein store kar rahe hain.

    }

    // pehle registretion ese ho rha tha 
    // public User registerUser(User user) { // registerUser method ek User object input leta hai aur registration ke baad ek User object return karta hai.

    //     return userRepository.save(user); // User object ko database mein save karta hai aur saved User object return karta hai.
    // }

    // jb hum ne password ko bycrypt kara to is type se pass wordencode kar rahe he 

        public User registerUser(User user) { // Naye User ko register karne ka method.

        String encodedPassword = // Hashed password ko store karne ke liye String variable bana rahe hain.
                passwordEncoder.encode(user.getPassword()); // User ke plain password ko BCrypt hash mein convert kar rahe hain.

        user.setPassword(encodedPassword); // User object ke plain password ki jagah hashed password set kar rahe hain.

        return userRepository.save(user); // Hashed password ke saath User ko database mein save kar rahe hain.
    }


    public AuthResponse loginUser(String email, String password) { // Login ke baad AuthResponse yani token aur name return karega.

    Optional<User> userOptional = userRepository.findByEmail(email); // Database mein diye gaye email se User ko search karta hai aur result Optional<User> mein store karta hai.

    if (userOptional.isEmpty()) { // Check karta hai ki email se database mein koi User mila ya nahi.

        throw new RuntimeException("User not found"); // Agar User nahi mila to exception throw karta hai aur "User not found" message deta hai.
    }

    User user = userOptional.get(); // Optional ke andar jo User mila hai us User object ko bahar nikal kar user variable mein store karta hai.

   boolean passwordMatches = // Password match hua ya nahi, uska true/false result store kar rahe hain.
            passwordEncoder.matches( // Plain password ko stored BCrypt hash ke against verify kar rahe hain.
                    password, // Login request se aaya hua plain password.
                    user.getPassword() // Database mein stored BCrypt hashed password.
            );

    if (!passwordMatches) { // Agar password match nahi hua to check kar rahe hain.
        throw new RuntimeException("Invalid password"); // Agar dono passwords match nahi karte to exception throw karta hai aur "Invalid password" message deta hai.
    }

    String token = jwtUtil.generateToken(user.getEmail()); // Successful login ke baad user ke email se JWT token generate kar rahe hain.

        return new AuthResponse(token, user.getName()); // Token aur user ka name AuthResponse object mein bhej/return rahe hain.
}

} // UserService class yahan close ho rahi hai.