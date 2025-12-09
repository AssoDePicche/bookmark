package com.bookmark.iam_service.domain.exception;

@SuppressWarnings({"serial"})
public class NotFoundException extends RuntimeException {
  public NotFoundException(String message) {
    super(message);
  }
}
