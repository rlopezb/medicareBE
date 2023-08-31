package com.medicare.service;

import com.medicare.entity.Seller;
import com.medicare.repository.SellerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SellerService {
  private final SellerRepository sellerRepository;
  public List<Seller> search(){
    return sellerRepository.findAll();
  }

}
