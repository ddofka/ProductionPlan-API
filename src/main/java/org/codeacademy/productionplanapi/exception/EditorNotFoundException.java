package org.codeacademy.productionplanapi.exception;

public class EditorNotFoundException extends RuntimeException {
    public EditorNotFoundException(String criteria) {
      super("Editor not found by: " + criteria);
    }
}
