package com.bookmark.common.domain.exception;

@SuppressWarnings({"serial"})
public class DuplicateEntryException extends RuntimeException {
  public DuplicateEntryException(String message) {
    super(message);
  }
}
