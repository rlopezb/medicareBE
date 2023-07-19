package com.medicare.controller;

import com.medicare.entity.User;
import com.medicare.exception.UnknownUserException;
import com.medicare.security.JwtUtils;
import com.medicare.security.MedicareUserDetails;
import com.medicare.security.MedicareUserDetailsService;
import com.medicare.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
  private final AuthenticationManager authenticationManager;
  private final MedicareUserDetailsService medicareUserDetailsService;
  private final UserService userService;
  private final JwtUtils jwtUtils;

  @PostMapping("/login")
  public User login(@RequestBody User user) throws UnknownUserException {
      authenticationManager
          .authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(), new ArrayList<>()));
      final MedicareUserDetails medicareUserDetails = medicareUserDetailsService.loadUserByUsername(user.getUsername());
      if (medicareUserDetails != null) {
        String token = jwtUtils.generateToken(medicareUserDetails);
        medicareUserDetails.setToken(token);
        return medicareUserDetails.getUser();
      }
      else{
        throw new UnknownUserException(user);
      }
  }

  @PostMapping("/register")
  public User register(@RequestBody User user) {
    return userService.register(user);
  }
}
