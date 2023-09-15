package com.medicare.exception;

import jakarta.validation.constraints.NotNull;

public class UserExistsException extends Exception {
  public UserExistsException(@NotNull String message) {
    super(message);
  }
}
