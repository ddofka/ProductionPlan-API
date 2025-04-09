package org.codeacademy.productionplanapi.exception;

public class VideoNotFoundException extends RuntimeException {
    public VideoNotFoundException(String criteria) {
      super("Video not found by: " + criteria);
    }
}
