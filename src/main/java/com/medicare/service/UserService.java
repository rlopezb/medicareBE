package com.medicare.service;

import com.medicare.entity.User;
import com.medicare.exception.UserException;
import com.medicare.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  private void validateUser(User user) throws UserException {
    if (user.getId() != null) {
      throw new UserException("Cannot create a user with assigned id " + user.getId() + ". Maybe call update instead!");
    }
    if (userRepository.existsByUsername(user.getUsername())) {
      throw new UserException("User with username " + user.getUsername() + " already exists");
    }
    if (userRepository.existsByEmail(user.getEmail())) {
      throw new UserException("User with email " + user.getEmail() + " already exists");
    }
    user.setPassword(passwordEncoder.encode(user.getPassword()));
  }

  public List<User> findAll() {
    return userRepository.findAll();
  }

  public User create(User user) throws UserException {
    validateUser(user);
    return userRepository.save(user);
  }

  public User register(User user) throws UserException {
    validateUser(user);
    user.setRole("ROLE_USER");
    return userRepository.save(user);
  }


}
