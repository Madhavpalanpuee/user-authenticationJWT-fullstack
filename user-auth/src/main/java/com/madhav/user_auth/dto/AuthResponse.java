package com.madhav.user_auth.dto; // Ye batata hai ki AuthResponse class dto package ke andar hai.


public class AuthResponse { // Login successful hone ke baad frontend ko response bhejne ke liye ye class banayi hai.

    private String token; // JWT token store karega jo successful login ke baad user ko diya jayega.

    private String name; // Logged-in user ka name store karega.


    public AuthResponse() { // Ye no-argument/default constructor hai, jisme koi value pass nahi karni padti.
    }


    public AuthResponse(String token, String name) { // Ye parameterized constructor hai jo token aur name lekar object create karega.

        this.token = token; // Method mein aaya hua token class ke token variable mein store kar rahe hain.

        this.name = name; // Method mein aaya hua name class ke name variable mein store kar rahe hain.
    }


    public String getToken() { // Ye getter method token ki value return karega.

        return token; // Current object ka JWT token return kar raha hai.
    }


    public void setToken(String token) { // Ye setter method token ki value set ya change karega.

        this.token = token; // Method se aaya token class ke token variable mein store kar rahe hain.
    }


    public String getName() { // Ye getter method name ki value return karega.

        return name; // Current object ka name return kar raha hai.
    }


    public void setName(String name) { // Ye setter method name ki value set ya change karega.

        this.name = name; // Method se aaya name class ke name variable mein store kar rahe hain.
    }
}