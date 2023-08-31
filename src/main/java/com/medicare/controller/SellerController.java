package com.medicare.controller;

import com.medicare.entity.Seller;
import com.medicare.service.SellerService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/medicare/api/seller")
@RequiredArgsConstructor
public class SellerController {
  private final SellerService sellerService;

  @PreAuthorize("hasAnyRole('ADMIN','USER')")
  @GetMapping
  List<Seller> sellers(){
    return sellerService.search();
  }
}
