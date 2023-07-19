package com.medicare.repository;

import com.medicare.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
public interface UserRepository extends JpaRepository<User, Long> {
  User getUserByUsername(@Param("username") String username);
  List<User> findAll();
}
