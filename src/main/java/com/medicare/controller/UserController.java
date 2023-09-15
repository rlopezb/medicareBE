package com.medicare.controller;
import com.medicare.entity.User;
import com.medicare.exception.UserDefinitionException;
import com.medicare.exception.UserExistsException;
import com.medicare.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
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
  public User create(@RequestBody User user) throws UserExistsException, UserDefinitionException {
    return userService.create(user);
  }
}
