package com.medicare.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Seller {
  @Id
  @GeneratedValue
  private Long id;
  @NotNull
  @Column(nullable = false, unique = true)
  private String name;
  @JsonIgnore
  @OneToMany(mappedBy = "seller")
  private List<Medicine> medicines;
}