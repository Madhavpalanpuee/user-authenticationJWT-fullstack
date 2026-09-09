package com.madhav.user_auth.dto; // Ye batata hai ki LoginRequest class kis package ke andar hai.

public class LoginRequest { // LoginRequest naam ki class create ho rahi hai; ye login ke time client se aane wale data ko hold karegi.

    private String email; // User ka email store karega jo login request mein frontend/Postman se aayega.

    private String password; // User ka password store karega jo login request mein frontend/Postman se aayega.


    public LoginRequest() { // Ye no-argument/default constructor hai, jisse object bina kisi value ke create kiya ja sakta hai.
    }


    public String getEmail() { // Ye getter method hai jo email ki value return karega.
        return email; // LoginRequest object ke andar stored email return karega.
    }


    public void setEmail(String email) { // Ye setter method hai jo email ki value set karega.
        this.email = email; // this.email class ka variable hai aur email method mein aayi hui value hai.
    }


    public String getPassword() { // Ye getter method hai jo password ki value return karega.
        return password; // LoginRequest object ke andar stored password return karega.
    }


    public void setPassword(String password) { // Ye setter method hai jo password ki value set karega.
        this.password = password; // this.password class ka variable hai aur password method mein aayi hui value hai.
    }

} // LoginRequest class yahan close ho rahi hai.