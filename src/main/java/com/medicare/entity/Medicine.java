package com.medicare.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
public class Medicine {
  @Id
  @GeneratedValue
  private Long id;
  @NotNull
  @Column(nullable = false, unique = true)
  private String name;
  @NotNull
  @Column(nullable = false)
  private Float price;
  @NotNull
  @Column(nullable = false)
  private String seller;
  @NotNull
  @Column(columnDefinition = "TEXT", nullable = false)
  private String description;
  @NotNull
  @Column(nullable = false)
  private Boolean enabled;
}
