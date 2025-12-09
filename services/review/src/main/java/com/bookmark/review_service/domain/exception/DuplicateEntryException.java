package com.bookmark.review_service.domain.exception;

@SuppressWarnings({"serial"})
public class DuplicateEntryException extends RuntimeException {
  public DuplicateEntryException(String message) {
    super(message);
  }
}
