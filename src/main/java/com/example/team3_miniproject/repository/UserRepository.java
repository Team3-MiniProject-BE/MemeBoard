package com.example.team3_miniproject.repository;

import com.example.team3_miniproject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    boolean existsByUsername(String Username);
    boolean existsByNickname(String Nickname);
}
