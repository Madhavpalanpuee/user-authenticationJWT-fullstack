package com.madhav.user_auth.repository;       // Ye batata hai ki UserRepository kis package ke andar hai.

import org.springframework.data.jpa.repository.JpaRepository;     // JpaRepository ko import karta hai, jiske through hume database ke ready-made CRUD methods milte hain.

import com.madhav.user_auth.entity.User;     // Hamari User Entity class ko import karta hai, jise repository database table ke saath use karegi.

import java.util.Optional; // Optional class ko import karta hai, jo value present ya absent hone ko safely handle karti hai.


public interface UserRepository extends JpaRepository<User, Long> {     // UserRepository interface bana rahe hain aur JpaRepository ko extend kar rahe hain; User = Entity aur Long = User ki ID ka data type hai.
    Optional<User> findByEmail(String email); // Email ke basis par database se User find karega aur result ko Optional<User> ke andar return karega.

} // UserRepository interface yahan close ho raha hai.