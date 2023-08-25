package com.medicare.controller;

import com.medicare.entity.Medicine;
import com.medicare.exception.MedicineException;
import com.medicare.service.MedicineService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.SortDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@RequestMapping("/medicare/api/medicine")
public class MedicineController {
  private final MedicineService medicineService;
  @PreAuthorize("hasAnyRole('ADMIN','USER')")
  @GetMapping
  public Page<Medicine> findAll(@SortDefault(sort = "id", direction = Sort.Direction.ASC) final Pageable pageable) { return medicineService.findAll(pageable); }
  @PreAuthorize("hasAnyRole('ADMIN','USER')")
  @GetMapping("{id}")
  public Medicine findById(@PathVariable Long id) {
    return medicineService.findById(id);
  }
  @PreAuthorize("hasRole('ADMIN')")
  @PostMapping
  public Medicine create(@RequestBody Medicine medicine) throws MedicineException { return medicineService.create(medicine); }
  @PreAuthorize("hasRole('ADMIN')")
  @PutMapping
  public Medicine update(@RequestBody Medicine medicine) throws MedicineException { return medicineService.update(medicine); }
  @PreAuthorize("hasRole('ADMIN')")
  @DeleteMapping("{id}")
  public void delete(@PathVariable Long id) { medicineService.deleteById(id); }

}
