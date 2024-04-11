package com.example.busbooking.service;

import com.example.busbooking.domain.User;
import com.example.busbooking.exception.NotFoundException;
import com.example.busbooking.repository.UserRepo;
import com.example.busbooking.security.UserPrincipal;
import jakarta.transaction.Transactional;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepo userRepo;

    public CustomUserDetailsService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findUserByEmail(username).orElseThrow(() -> new UsernameNotFoundException("Username not found"));
        return UserPrincipal.create(user);
    }
    @Transactional
    public UserDetails loadUserById(Long id) {
        User user = userRepo.findById(id).orElseThrow(() -> new NotFoundException("Can't find user by ID"));

        return UserPrincipal.create(user);
    }
}
