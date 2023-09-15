package com.medicare.repository;

import com.medicare.entity.Purchase;
import com.medicare.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PurchaseRepository  extends PagingAndSortingRepository<Purchase, Long> {
  @Query("select p from Purchase p where p.user = :user")
  Page<Purchase> search(Pageable pageable, User user);

  Purchase findByIdAndUser(Long id, User user);

  Purchase save(Purchase purchase);
}
