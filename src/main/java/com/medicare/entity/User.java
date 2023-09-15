package com.medicare.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
public class User {
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Long id;
  @NotNull
  @Column(nullable = false, unique = true)
  private String username;
  @NotNull
  @Column(nullable = false)
  private String password;
  @NotNull
  @Column(nullable = false, unique = true)
  private String email;
  @NotNull
  @Column(nullable = false)
  private String firstName;
  @NotNull
  @Column(nullable = false)
  private String lastName;
  @NotNull
  @Column(nullable = false)
  private String role;
  @Transient
  private String token;
}
