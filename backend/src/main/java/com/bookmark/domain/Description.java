package com.bookmark.domain;

import jakarta.persistence.Embeddable;

@Embeddable
public record Description(String value) {
  public Description {
    if (value.isEmpty()) {
      String message = "The Book Description Must Not Be Empty";

      throw new IllegalArgumentException(message);
    }
  }

  @Override
  public String toString() {
    return value;
  }
}
