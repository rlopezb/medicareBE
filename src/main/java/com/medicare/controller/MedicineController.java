package com.medicare.controller;

import com.medicare.entity.Medicine;
import com.medicare.exception.MedicineException;
import com.medicare.service.MedicineService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/medicare/api/medicine")
@RequiredArgsConstructor
public class MedicineController {
  private final MedicineService medicineService;

  @PreAuthorize("hasAnyRole('ADMIN','USER')")
  @GetMapping
  public Page<Medicine> search(@PageableDefault(size = 12) @SortDefault(sort = "id", direction = Sort.Direction.ASC) final Pageable pageable,
                                @RequestParam(required = false) Long seller,
                                @RequestParam(required = false) String search,
                                @RequestParam(required = false) Float price) {
    if (seller == null) {
      if (search == null && price == null)
        return medicineService.search(pageable);
      else if (search != null && price == null)
        return medicineService.search(pageable, search);
      else if (search == null)
        return medicineService.search(pageable, price);
      else return medicineService.search(pageable, search, price);
    } else {
      if (search == null && price == null)
        return medicineService.search(pageable, seller);
      else if (search != null && price == null)
        return medicineService.search(pageable, seller, search);
      else if (search == null)
        return medicineService.search(pageable, seller, price);
      else return medicineService.search(pageable, seller, search, price);
    }
  }

  @PreAuthorize("hasAnyRole('ADMIN','USER')")
  @GetMapping("max")
  public Float max() {
    return medicineService.max();
  }

  @PreAuthorize("hasAnyRole('ADMIN','USER')")
  @GetMapping("{id}")
  public Medicine findById(@PathVariable Long id) {
    return medicineService.findById(id);
  }

  @PreAuthorize("hasRole('ADMIN')")
  @PostMapping
  public Medicine create(@RequestBody Medicine medicine) throws MedicineException {
    return medicineService.create(medicine);
  }

  @PreAuthorize("hasRole('ADMIN')")
  @PutMapping
  public Medicine update(@RequestBody Medicine medicine) throws MedicineException {
    return medicineService.update(medicine);
  }

  @PreAuthorize("hasRole('ADMIN')")
  @DeleteMapping("{id}")
  public void delete(@PathVariable Long id) {
    medicineService.deleteById(id);
  }

}
