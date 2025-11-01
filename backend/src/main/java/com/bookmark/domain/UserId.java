package com.bookmark.domain;

import java.util.UUID;

public record UserId(UUID value) {
  public UserId {
    if (null == value) {
      throw new IllegalArgumentException("User Id Must Not Be Null");
    }
  }

  public UserId(String value) {
    this(UUID.fromString(value));
  }

  public UserId() {
    this(UUID.randomUUID());
  }

  @Override
  public String toString() {
    return value.toString();
  }
}
