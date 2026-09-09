package com.madhav.user_auth.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private String password;

       public User() { // Ye no-argument/default constructor hai, jise JPA/Hibernate Entity object banane ke liye use kar sakta hai.
    }

       public Long getId() { // Ye getter method hai jo id ki value return karega.
        return id; // Current User object ki id return karega.
    }

    public void setId(Long id) { // Ye setter method hai jo id ki value set karega.
        this.id = id; // this.id class ka variable hai aur id method mein aayi hui value hai.
    }


       public String getName() { // Ye getter method hai jo name ki value return karega.
        return name; // Current User object ka name return karega.
    }

    public void setName(String name) { // Ye setter method hai jo name ki value set karega.
        this.name = name; // this.name class ka variable hai aur name method mein aayi hui value hai.
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
