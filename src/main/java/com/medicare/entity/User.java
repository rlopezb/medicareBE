package com.medicare.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import lombok.Data;

@Entity
@Data
public class User {
  @Id
  private Long id;
  private String username;
  private String password;
  private String email;
  private String firstName;
  private String lastName;
  private String role;
  @Transient
  private String token;
}
