package com.madhav.user_auth.security; // Ye class security package ke andar hai.

import jakarta.servlet.FilterChain; // Multiple filters ki chain ko represent karta hai.
import jakarta.servlet.ServletException; // Servlet/filter ke errors ko handle karne ke liye use hota hai.
import jakarta.servlet.http.HttpServletRequest; // Incoming HTTP request ko represent karta hai.
import jakarta.servlet.http.HttpServletResponse; // HTTP response ko represent karta hai.

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken; // Spring Security mein authenticated user object banane ke liye.
import org.springframework.security.core.context.SecurityContextHolder; // Current request ka authentication/security information store karta hai.
import org.springframework.stereotype.Component; // Spring ko is class ka object automatically create karne ke liye.
import org.springframework.web.filter.OncePerRequestFilter; // Ye ensure karta hai ki filter ek request ke liye sirf ek baar chale.

import java.io.IOException; // Input/Output related exception ko handle karne ke liye import kar rahe hain.
import java.util.Collections; // Empty list banane ke liye Collections class import kar rahe hain.


@Component // Spring is JwtAuthenticationFilter ka object automatically create aur manage karega.
public class JwtAuthenticationFilter extends OncePerRequestFilter { // Ye class har HTTP request par ek baar JWT check karne wala filter hai.

    private final JwtUtil jwtUtil; // JWT generate, extract aur validate karne ke liye JwtUtil object ki reference.

    public JwtAuthenticationFilter(JwtUtil jwtUtil) { // Constructor Injection ke through JwtUtil object receive kar rahe hain.

        this.jwtUtil = jwtUtil; // Received JwtUtil object ko class variable mein store kar rahe hain.
    }


    @Override // Parent class ke doFilterInternal() method ko override kar rahe hain.
    protected void doFilterInternal( // Har HTTP request ke liye filter ka main logic yahan chalega.
            HttpServletRequest request, // Client/React/Postman se aane wali request.
            HttpServletResponse response, // Client ko bheja jane wala response.
            FilterChain filterChain // Request ko next filter ya controller tak bhejne ke liye filter chain.
    ) throws ServletException, IOException { // Method servlet aur IO exceptions throw kar sakta hai.

        String authHeader = request.getHeader("Authorization"); // Request ke Authorization header ki value nikal rahe hain.


        if (authHeader == null || !authHeader.startsWith("Bearer ")) { // Check kar rahe hain ki Authorization header hai aur "Bearer " se start hota hai ya nahi.

            filterChain.doFilter(request, response); // Agar JWT nahi mila to request ko next filter ki taraf bhej rahe hain.

            return; // Is method ko yahin stop kar rahe hain taaki neeche ka JWT code execute na ho.
        }


        String token = authHeader.substring(7); // "Bearer " ke 7 characters hata kar actual JWT token nikal rahe hain.


        if (jwtUtil.isTokenValid(token)) { // JwtUtil ki help se check kar rahe hain ki token valid aur expired nahi hai.

            String email = jwtUtil.extractEmail(token); // Valid JWT ke subject se user ka email nikal rahe hain.


            UsernamePasswordAuthenticationToken authentication = // Spring Security ke liye authentication object bana rahe hain.
                    new UsernamePasswordAuthenticationToken(
                            email, // Authenticated user ki identity ke roop mein email set kar rahe hain.
                            null, // Password ki zarurat nahi hai kyunki JWT already verify ho chuka hai.
                            Collections.emptyList() // Abhi user ko koi roles/authorities assign nahi kar rahe hain.
                    );


            SecurityContextHolder // Current request ke Security Context ko access kar rahe hain.
                    .getContext() // Current security context nikal rahe hain.
                    .setAuthentication(authentication); // Current request ke liye user ko authenticated set kar rahe hain.
        }


        filterChain.doFilter(request, response); // JWT check hone ke baad request ko next filter ya controller tak bhej rahe hain.
    }
}