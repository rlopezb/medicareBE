package com.medicare.service;

import com.medicare.entity.User;
import com.medicare.exception.UserDefinitionException;
import com.medicare.exception.UserExistsException;
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

  private void validateUser(User user) throws UserDefinitionException, UserExistsException {
    if (user.getId() != null) {
      throw new UserDefinitionException("Cannot create a user with assigned id " + user.getId() + ". Maybe call update instead!");
    }
    if (userRepository.existsByUsername(user.getUsername())) {
      throw new UserExistsException("User with username " + user.getUsername() + " already exists");
    }
    if (userRepository.existsByEmail(user.getEmail())) {
      throw new UserExistsException("User with email " + user.getEmail() + " already exists");
    }
    user.setPassword(passwordEncoder.encode(user.getPassword()));
  }

  public List<User> findAll() {
    return userRepository.findAll();
  }

  public User create(User user) throws UserExistsException, UserDefinitionException {
    validateUser(user);
    return userRepository.save(user);
  }

  public User register(User user) throws UserExistsException, UserDefinitionException {
    validateUser(user);
    user.setRole("ROLE_USER");
    return userRepository.save(user);
  }


}
