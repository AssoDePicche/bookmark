package com.bookmark.book_list_service.domain.exception;

@SuppressWarnings({"serial"})
public class ForbiddenException extends RuntimeException {
  public ForbiddenException(String message) {
    super(message);
  }
}
