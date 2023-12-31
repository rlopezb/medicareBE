package com.medicare.service;

import com.medicare.entity.Medicine;
import com.medicare.exception.MedicineExistsException;
import com.medicare.exception.MedicineNotExistsException;
import com.medicare.repository.MedicineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MedicineService {
  private final MedicineRepository medicineRepository;

  public Page<Medicine> search(Pageable pageable) {
    return medicineRepository.findAll(pageable);
  }

  public Page<Medicine> search(Pageable pageable, String search) {
    return medicineRepository.search(pageable, search);
  }

  public Page<Medicine> search(Pageable pageable, Float price) {
    return medicineRepository.search(pageable, price);
  }

  public Page<Medicine> search(Pageable pageable, String search, Float price) {
    return medicineRepository.search(pageable, search, price);
  }

  public Page<Medicine> search(Pageable pageable, Long seller) {
    return medicineRepository.search(pageable, seller);
  }

  public Page<Medicine> search(Pageable pageable, Long seller, String search) {
    return medicineRepository.search(pageable, seller, search);
  }

  public Page<Medicine> search(Pageable pageable, Long seller, Float price) {
    return medicineRepository.search(pageable, seller, price);
  }

  public Page<Medicine> search(Pageable pageable, Long seller, String search, Float price) {
    return medicineRepository.search(pageable, seller, search, price);
  }

  public Float max() {
    return medicineRepository.max();
  }

  public Medicine findById(Long id) {
    return medicineRepository.findById(id);
  }

  public Medicine create(Medicine medicine) throws MedicineExistsException {
    if (medicine.getId()!=null && medicineRepository.existsById(medicine.getId())) {
      throw new MedicineExistsException("Medicine with id " + medicine.getId() + " already exists. Did you mean PUT?");
    }
    if (medicineRepository.existsByName(medicine.getName())) {
      throw new MedicineExistsException("Medicine with name " + medicine.getName() + " already exists");
    }
    return medicineRepository.save(medicine);
  }

  public Medicine update(Medicine medicine) throws MedicineNotExistsException {
    Medicine dbMedicine = medicineRepository.findById(medicine.getId());
    if (dbMedicine == null) {
      throw new MedicineNotExistsException("Medicine with id " + medicine.getId() + " does not exists");
    }
    if (medicine.getPrice() != null) dbMedicine.setPrice(medicine.getPrice());
    if (medicine.getSeller() != null) dbMedicine.setSeller(medicine.getSeller());
    if (medicine.getDescription() != null) dbMedicine.setDescription(medicine.getDescription());
    if (medicine.getEnabled() != null) dbMedicine.setEnabled(medicine.getEnabled());
    if (medicine.getStock() != null) dbMedicine.setStock(medicine.getStock());
    return medicineRepository.save(dbMedicine);
  }

  public void deleteById(Long id) throws MedicineExistsException {
    try {
      medicineRepository.deleteById(id);
    } catch (DataIntegrityViolationException exception) {
      throw new MedicineExistsException("Cannot delete medicine with id "+id+" because it has a purchase");
    }
  }
}
