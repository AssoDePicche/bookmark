package com.bookmark.application;

@SuppressWarnings({"serial"})
public class DuplicateEntryException extends RuntimeException {
  public DuplicateEntryException(String message) {
    super(message);
  }
}
