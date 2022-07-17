package com.example.cryptocurrencywatcher.repository;

import com.example.cryptocurrencywatcher.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, String> {
    User findByName(String name);
}


