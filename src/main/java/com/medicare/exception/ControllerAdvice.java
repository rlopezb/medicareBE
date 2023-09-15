package com.medicare.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {
  @ExceptionHandler(PurchaseAccessDeniedException.class)
  @ResponseStatus(HttpStatus.UNAUTHORIZED)
  public ErrorResponse purchaseAccessDeniedException(PurchaseAccessDeniedException exception) {
    return ErrorResponse.create(exception, HttpStatus.UNAUTHORIZED, exception.getMessage());
  }

  @ExceptionHandler(MedicineNotExistsException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public ErrorResponse medicineNotExistsException(MedicineNotExistsException exception) {
    return ErrorResponse.create(exception, HttpStatus.NOT_FOUND, exception.getMessage());
  }

  @ExceptionHandler(MedicineExistsException.class)
  @ResponseStatus(HttpStatus.CONFLICT)
  public ErrorResponse medicineExistsException(MedicineExistsException exception) {
    return ErrorResponse.create(exception, HttpStatus.CONFLICT, exception.getMessage());
  }

  @ExceptionHandler(UserExistsException.class)
  @ResponseStatus(HttpStatus.CONFLICT)
  public ErrorResponse userExistsException(UserExistsException exception) {
    return ErrorResponse.create(exception, HttpStatus.CONFLICT, exception.getMessage());
  }

  @ExceptionHandler(UserDefinitionException.class)
  @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
  public ErrorResponse userDefinitionException(UserDefinitionException exception) {
    return ErrorResponse.create(exception, HttpStatus.NOT_ACCEPTABLE, exception.getMessage());
  }

  @ExceptionHandler(Exception.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public ErrorResponse exception(Exception exception) {
    return ErrorResponse.create(exception, HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
  }
}