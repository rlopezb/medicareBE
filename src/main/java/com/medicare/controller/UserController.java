package com.medicare.controller;
import com.medicare.entity.User;
import com.medicare.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@RequestMapping("/medicare/api/user")
public class UserController {
  private final UserService userService;

  @PreAuthorize("hasRole('ADMIN')")
  @GetMapping
  public List<User> findAll() {
    return userService.findAll();
  }

  @PreAuthorize("hasRole('ADMIN')")
  @PostMapping
  public User save(@RequestBody User user) {
    return userService.save(user);
  }
}
