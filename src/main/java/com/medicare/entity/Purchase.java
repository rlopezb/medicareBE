package com.medicare.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Purchase {
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Long id;
  @NotNull
  @Column(nullable = false)
  private LocalDateTime created = LocalDateTime.now();
  private LocalDateTime payed;
  private LocalDateTime cancelled;
  @ManyToOne
  @JoinColumn(name = "user_id", nullable = false)
  private User user;
  @OneToMany(mappedBy = "purchase")
  private List<PurchaseMedicine> purchaseMedicines = new ArrayList<>();
}