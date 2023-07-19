package com.medicare.controller;
import com.medicare.entity.User;
import com.medicare.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
  private final UserService usersService;

  @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
  @GetMapping
  public List<User> findAll() {
    return usersService.findAll();
  }

  @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
  @PostMapping
  public User save(@RequestBody User user) {
    return usersService.save(user);
  }
}
