package com.bookmark.domain;

import java.util.UUID;

public record BookId(UUID value) {
  public BookId {
    if (null == value) {
      throw new IllegalArgumentException("Book Id Must Not Be Null");
    }
  }

  public BookId() {
    this(UUID.randomUUID());
  }

  @Override
  public String toString() {
    return value.toString();
  }
}
