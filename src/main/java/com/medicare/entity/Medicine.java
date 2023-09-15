package com.medicare.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Medicine {
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
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
  @NotNull
  @Column(nullable = false)
  private Integer stock;
  @OneToMany(mappedBy = "medicine",fetch = FetchType.LAZY)
  @JsonIgnore
  private List<PurchaseMedicine> purchaseMedicines = new ArrayList<>();
}
