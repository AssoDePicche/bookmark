package com.bookmark.book_list_service.domain;

import java.util.UUID;

public record BookListId(UUID value) {
  public BookListId {
    if (null == value) {
      throw new IllegalArgumentException("Book List Id Must Not Be Null");
    }
  }

  public BookListId(String value) {
    this(UUID.fromString(value));
  }

  public BookListId() {
    this(UUID.randomUUID());
  }

  @Override
  public String toString() {
    return value.toString();
  }
}
