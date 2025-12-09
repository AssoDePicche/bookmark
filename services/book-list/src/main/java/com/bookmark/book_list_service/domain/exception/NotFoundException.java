package com.bookmark.book_list_service.domain.exception;

@SuppressWarnings({"serial"})
public class NotFoundException extends RuntimeException {
  public NotFoundException(String message) {
    super(message);
  }
}
