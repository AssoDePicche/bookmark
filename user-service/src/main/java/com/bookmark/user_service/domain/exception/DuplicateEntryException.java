package com.bookmark.user_service.domain.exception;

@SuppressWarnings({"serial"})
public class DuplicateEntryException extends RuntimeException {
  public DuplicateEntryException(String message) {
    super(message);
  }
}
