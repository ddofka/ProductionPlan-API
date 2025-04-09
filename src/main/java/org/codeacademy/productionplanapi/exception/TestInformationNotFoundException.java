package org.codeacademy.productionplanapi.exception;

public class TestInformationNotFoundException extends RuntimeException {
    public TestInformationNotFoundException(String criteria) {
      super("Test Information not found by: " + criteria);
    }
}
