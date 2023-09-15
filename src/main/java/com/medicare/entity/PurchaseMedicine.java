package com.medicare.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
public class PurchaseMedicine {
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Long id;
  @ManyToOne
  @JoinColumn(name = "purchase_id", nullable = false)
  @JsonIgnore
  private Purchase purchase;
  @ManyToOne
  @JoinColumn(name = "medicine_id", nullable = false)
  private Medicine medicine;
  @NotNull
  @Column(nullable = false)
  private Float price;
  @NotNull
  @Column(nullable = false)
  private Integer quantity;
}
