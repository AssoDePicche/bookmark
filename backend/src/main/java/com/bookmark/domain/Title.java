package com.bookmark.domain;

import jakarta.persistence.Embeddable;

@Embeddable
public record Title(String value) {
  public Title {
    if (value.isEmpty()) {
      String message = "The Book Title Must Not Be Empty";

      throw new IllegalArgumentException(message);
    }
  }

  @Override
  public String toString() {
    return value;
  }
}
