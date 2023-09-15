package com.medicare.exception;

import jakarta.validation.constraints.NotNull;

public class MedicineExistsException extends Exception {
  public MedicineExistsException(@NotNull String message) {
    super(message);
  }
}
