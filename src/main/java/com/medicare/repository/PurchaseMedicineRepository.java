package com.medicare.repository;

import com.medicare.entity.PurchaseMedicine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseMedicineRepository extends JpaRepository<PurchaseMedicine,Long> {
}
