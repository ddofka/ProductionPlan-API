package org.codeacademy.productionplanapi.exception;

public class DirectorNotFoundException extends RuntimeException {
    public DirectorNotFoundException(String criteria) {
      super("Director not found by: " + criteria);
    }
}
