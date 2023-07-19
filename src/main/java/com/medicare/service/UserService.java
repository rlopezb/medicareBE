package com.medicare.service;

import com.medicare.entity.User;
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

  public List<User> findAll() {
    return userRepository.findAll();
  }

  public User save(User user){
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    return userRepository.save(user);
  }

  public User register(User user){
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    user.setRole("ROLE_USER");
    return userRepository.save(user);
  }

}
