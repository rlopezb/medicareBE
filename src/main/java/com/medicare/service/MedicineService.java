package com.medicare.service;

import com.medicare.entity.Medicine;
import com.medicare.repository.MedicineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MedicineService {
  private final MedicineRepository medicineRepository;

  public Page<Medicine> findAll(Pageable pageable) {
    return medicineRepository.findAll(pageable);
  }

  public Medicine findById(Long id) { return medicineRepository.findById(id); }
  public Medicine create(Medicine medicine) {
    medicine.setId(null);
    return medicineRepository.save(medicine);
  }
  public Medicine update(Medicine medicine) {
    return medicineRepository.save(medicine);
  }

  public void deleteById(Long id) {
    medicineRepository.deleteById(id);
  }

}
