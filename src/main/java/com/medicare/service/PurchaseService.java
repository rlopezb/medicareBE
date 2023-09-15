package com.medicare.service;

import com.medicare.entity.Medicine;
import com.medicare.entity.Purchase;
import com.medicare.entity.PurchaseMedicine;
import com.medicare.entity.User;
import com.medicare.exception.MedicineNotExistsException;
import com.medicare.exception.PurchaseAccessDeniedException;
import com.medicare.repository.MedicineRepository;
import com.medicare.repository.PurchaseMedicineRepository;
import com.medicare.repository.PurchaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class PurchaseService {
  private final PurchaseRepository purchaseRepository;
  private final MedicineRepository medicineRepository;
  private final PurchaseMedicineRepository purchaseMedicineRepository;

  public Page<Purchase> search(Pageable pageable, User user) {
    return purchaseRepository.search(pageable, user);
  }

  public Purchase findByIdAndUser(Long id, User user) {
    return purchaseRepository.findByIdAndUser(id, user);
  }

  public Purchase create(User user) {
    Purchase purchase = new Purchase();
    purchase.setUser(user);
    return purchaseRepository.save(purchase);
  }

  public Purchase add(Long purchaseId, Long medicineId, User user) throws PurchaseAccessDeniedException, MedicineNotExistsException {
    Purchase purchase = purchaseRepository.findByIdAndUser(purchaseId, user);
    if (purchase == null)
      throw new PurchaseAccessDeniedException("The purchase with id " + purchaseId + " is not for the user " + user.getId());
    Medicine medicine = medicineRepository.findById(medicineId);
    if (medicine == null)
      throw new MedicineNotExistsException("The medicine with id " + medicineId + " does not exists");

    if (purchase.getPurchaseMedicines() == null) {
      purchase.setPurchaseMedicines(new ArrayList<>());
    }

    List<PurchaseMedicine> purchaseMedicines = purchase.getPurchaseMedicines();
    PurchaseMedicine purchaseMedicine = purchaseMedicines.stream()
        .filter(pm -> Objects.equals(pm.getMedicine().getId(), medicineId))
        .findFirst()
        .orElse(null);
    if (purchaseMedicine == null) {
      purchaseMedicine = new PurchaseMedicine();
      purchaseMedicine.setMedicine(medicine);
      purchaseMedicine.setPurchase(purchase);
      purchaseMedicine.setQuantity(1);
      purchaseMedicine.setPrice(medicine.getPrice());
      purchaseMedicines.add(purchaseMedicine);
    } else {
      purchaseMedicine.setQuantity(purchaseMedicine.getQuantity() + 1);
    }
    medicine.setStock(medicine.getStock() - 1);

    purchaseMedicineRepository.save(purchaseMedicine);
    medicineRepository.save(medicine);
    return purchaseRepository.save(purchase);
  }

  public Purchase remove(Long purchaseId, Long purchaseMedicineId, User user) throws PurchaseAccessDeniedException, MedicineNotExistsException {
    Purchase purchase = purchaseRepository.findByIdAndUser(purchaseId, user);
    if (purchase == null)
      throw new PurchaseAccessDeniedException("The purchase with id " + purchaseId + " is not for the user " + user.getId());

    List<PurchaseMedicine> purchaseMedicines = purchase.getPurchaseMedicines();
    PurchaseMedicine purchaseMedicine = purchaseMedicines.stream()
        .filter(pm -> Objects.equals(pm.getId(), purchaseMedicineId))
        .findFirst()
        .orElse(null);
    if (purchaseMedicine != null) {
      Medicine medicine = purchaseMedicine.getMedicine();
      medicine.setStock(medicine.getStock() + 1);
      medicineRepository.save(medicine);
      if (purchaseMedicine.getQuantity() == 1) {
        purchase.getPurchaseMedicines().remove(purchaseMedicine);
        purchaseMedicineRepository.delete(purchaseMedicine);
      } else {
        purchaseMedicine.setQuantity(purchaseMedicine.getQuantity() - 1);
        purchaseMedicineRepository.save(purchaseMedicine);
      }
    } else {
      throw new MedicineNotExistsException("There is no purchased medicine with id " + purchaseMedicineId + " in the purchase number " + purchase.getId());
    }
    return  purchaseRepository.save(purchase);
  }

  public Purchase pay(Long purchaseId, User user) throws PurchaseAccessDeniedException {
    Purchase purchase = purchaseRepository.findByIdAndUser(purchaseId, user);
    if (purchase == null)
      throw new PurchaseAccessDeniedException("The purchase with id " + purchaseId + " is not for the user " + user.getId());
    purchase.setPayed(LocalDateTime.now());
    return purchaseRepository.save(purchase);
  }

  public Purchase cancel(Long purchaseId, User user) throws PurchaseAccessDeniedException, MedicineNotExistsException {
    Purchase purchase = purchaseRepository.findByIdAndUser(purchaseId, user);
    if (purchase == null)
      throw new PurchaseAccessDeniedException("The purchase with id " + purchaseId + " is not for the user " + user.getId());
    purchase.setCancelled(LocalDateTime.now());
    for (PurchaseMedicine purchaseMedicine : purchase.getPurchaseMedicines()) {
      Medicine medicine = medicineRepository.findById(purchaseMedicine.getMedicine().getId());
      if (medicine == null)
        throw new MedicineNotExistsException("There is no medicine with id " + purchaseMedicine.getMedicine().getId());
      medicine.setStock(medicine.getStock() + purchaseMedicine.getQuantity());
      medicineRepository.save(medicine);
    }
    return purchaseRepository.save(purchase);
  }
}
