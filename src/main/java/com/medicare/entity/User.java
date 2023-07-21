package com.medicare.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class User {
  @Id
  @GeneratedValue
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
