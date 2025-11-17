package com.bookmark.booklist.domain;

import java.util.UUID;

public record BookListEntryId(UUID value) {
  public BookListEntryId {
    if (null == value) {
      throw new IllegalArgumentException("Book List Entry Id Must Not Be Null");
    }
  }

  public BookListEntryId(String value) {
    this(UUID.fromString(value));
  }

  public BookListEntryId() {
    this(UUID.randomUUID());
  }

  @Override
  public String toString() {
    return value.toString();
  }
}
