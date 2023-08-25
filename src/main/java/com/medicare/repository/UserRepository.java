package com.medicare.repository;

import com.medicare.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
  User getUserByUsername(String username);

  boolean existsByUsername(String username);

  boolean existsByEmail(String email);
}
