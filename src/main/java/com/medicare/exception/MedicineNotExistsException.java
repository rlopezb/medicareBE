package com.medicare.exception;

import jakarta.validation.constraints.NotNull;

public class MedicineNotExistsException extends Exception {
  public MedicineNotExistsException(@NotNull String message) {
    super(message);
  }
}
