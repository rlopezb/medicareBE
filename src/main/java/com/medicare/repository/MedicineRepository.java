package com.medicare.repository;

import com.medicare.entity.Medicine;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface MedicineRepository extends PagingAndSortingRepository<Medicine, Long> {
  Medicine findById(Long id);
  Medicine save(Medicine medicine);
  void deleteById(Long id);
  boolean existsByName(String name);
}
