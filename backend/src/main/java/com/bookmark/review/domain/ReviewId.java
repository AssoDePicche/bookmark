package com.bookmark.review.domain;

import java.util.UUID;

public record ReviewId(UUID value) {
  public ReviewId {
    if (null == value) {
      throw new IllegalArgumentException("Review Id Must Not Be Null");
    }
  }

  public ReviewId(String value) {
    this(UUID.fromString(value));
  }

  public ReviewId() {
    this(UUID.randomUUID());
  }

  @Override
  public String toString() {
    return value.toString();
  }
}
