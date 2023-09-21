package com.medicare.controller;

import com.medicare.entity.Purchase;
import com.medicare.entity.User;
import com.medicare.exception.MedicineNotExistsException;
import com.medicare.exception.PurchaseAccessDeniedException;
import com.medicare.security.MedicareUserDetails;
import com.medicare.service.PurchaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/medicare/api/purchase")
@RequiredArgsConstructor
public class PurchaseController {
  private final PurchaseService purchaseService;

  private User getUser() {
    UsernamePasswordAuthenticationToken authToken = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
    return ((MedicareUserDetails) authToken.getPrincipal()).getUser();
  }

  @PreAuthorize("hasAnyRole('ADMIN','USER')")
  @GetMapping("{id}")
  public Purchase findByIdAndUser(@PathVariable Long id) {
    return purchaseService.findByIdAndUser(id, getUser());
  }

  @PreAuthorize("hasAnyRole('ADMIN','USER')")
  @GetMapping
  public Page<Purchase> search(@PageableDefault @SortDefault(sort = "id", direction = Sort.Direction.DESC) final Pageable pageable) {
    return purchaseService.search(pageable, getUser());
  }

  @PreAuthorize("hasAnyRole('ADMIN','USER')")
  @PostMapping
  public Purchase create() {
    User user = getUser();
    return purchaseService.create(user);
  }

  @PreAuthorize("hasAnyRole('ADMIN','USER')")
  @PutMapping("{purchaseId}/add/{medicineId}")
  public Purchase add(@PathVariable Long purchaseId, @PathVariable Long medicineId) throws MedicineNotExistsException, PurchaseAccessDeniedException {
    User user = getUser();
    return purchaseService.add(purchaseId, medicineId, user);
  }

  @PreAuthorize("hasAnyRole('ADMIN','USER')")
  @DeleteMapping("{purchaseId}/remove/{purchaseMedicineId}")
  public Purchase remove(@PathVariable Long purchaseId, @PathVariable Long purchaseMedicineId) throws PurchaseAccessDeniedException, MedicineNotExistsException {
    User user = getUser();
    return purchaseService.remove(purchaseId, purchaseMedicineId, user);
  }


  @PreAuthorize("hasAnyRole('ADMIN','USER')")
  @PutMapping("{purchaseId}/pay")
  public Purchase pay(@PathVariable Long purchaseId) throws PurchaseAccessDeniedException {
    User user = getUser();
    return purchaseService.pay(purchaseId, user);
  }

  @PreAuthorize("hasAnyRole('ADMIN','USER')")
  @DeleteMapping("{purchaseId}/cancel")
  public Purchase cancel(@PathVariable Long purchaseId) throws PurchaseAccessDeniedException, MedicineNotExistsException {
    User user = getUser();
    return purchaseService.cancel(purchaseId, user);
  }

}