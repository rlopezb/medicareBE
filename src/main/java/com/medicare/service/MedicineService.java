package com.medicare.service;

import com.medicare.entity.Medicine;
import com.medicare.exception.MedicineException;
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

  public Medicine findById(Long id) {
    return medicineRepository.findById(id);
  }

  public Medicine create(Medicine medicine) throws MedicineException {
    medicine.setId(null);
    if (medicineRepository.existsByName(medicine.getName())) {
      throw new MedicineException("Medicine with name " + medicine.getName() + " already exists");
    }
    return medicineRepository.save(medicine);
  }

  public Medicine update(Medicine medicine) throws MedicineException {
    Medicine dbMedicine = medicineRepository.findById(medicine.getId());
    if (dbMedicine == null) {
      throw new MedicineException("Medicine with id " + medicine.getId() + " does not exists");
    }
    if (medicine.getPrice() != null) dbMedicine.setPrice(medicine.getPrice());
    if (medicine.getSeller() != null) dbMedicine.setSeller(medicine.getSeller());
    if (medicine.getDescription() != null) dbMedicine.setDescription(medicine.getDescription());
    if (medicine.getEnabled() != null) dbMedicine.setEnabled(medicine.getEnabled());
    return medicineRepository.save(dbMedicine);
  }

  public void deleteById(Long id) {
    medicineRepository.deleteById(id);
  }

}
