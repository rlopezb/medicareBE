package com.medicare.exception;

import jakarta.validation.constraints.NotNull;

public class UserDefinitionException extends Exception {
    public UserDefinitionException(@NotNull String message) {
    super(message);
  }
}
