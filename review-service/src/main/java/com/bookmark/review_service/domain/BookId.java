package com.bookmark.review_service.domain;

import java.util.UUID;

public record BookId(UUID value) {
  public BookId {
    if (null == value) {
      throw new IllegalArgumentException("Book Id Must Not Be Null");
    }
  }

  public BookId(String value) {
    this(UUID.fromString(value));
  }

  public BookId() {
    this(UUID.randomUUID());
  }

  @Override
  public String toString() {
    return value.toString();
  }
}
