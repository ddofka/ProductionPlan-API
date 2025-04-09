package org.codeacademy.productionplanapi.exception;

public class ReleaseNotFoundException extends RuntimeException {
    public ReleaseNotFoundException(String criteria) {
      super("Release info not found by: " + criteria);
    }
}
