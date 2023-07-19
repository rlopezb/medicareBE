package com.medicare.exception;

import com.medicare.entity.User;

public class UnknownUserException extends Exception {
  public UnknownUserException(User user) {
    super("Unknown user '"+user.getUsername()+"'");
  }
}
