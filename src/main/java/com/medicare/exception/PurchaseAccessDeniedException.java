package com.medicare.exception;

import jakarta.validation.constraints.NotNull;

public class PurchaseAccessDeniedException extends Exception {
  public PurchaseAccessDeniedException(@NotNull String message) {
    super(message);
  }
}
