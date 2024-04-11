package com.example.busbooking.repository;


import com.example.busbooking.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepo extends JpaRepository<User,Long> {

    Optional<User> findUserByEmail(String email);

}
