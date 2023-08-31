package com.medicare.entity;

import jakarta.persistence.*;
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
  @ManyToOne
  @JoinColumn(name = "seller_id", nullable = false)
  private Seller seller;
  @NotNull
  @Column(columnDefinition = "TEXT", nullable = false)
  private String description;
  @NotNull
  @Column(nullable = false)
  private Boolean enabled;
}
