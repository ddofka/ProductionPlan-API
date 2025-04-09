package org.codeacademy.productionplanapi.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String criteria) {
      super("User not found by: " + criteria);
    }
}
