package com.example.Inventory_management.service;



import com.example.Inventory_management.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    void registerUser(User user) throws Exception;
    User findByUsername(String username);
}

