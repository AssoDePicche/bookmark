package com.bookmark.domain;

public record ISBN(String value) {
  public ISBN {
    if (value.isEmpty()) {
      throw new IllegalArgumentException("Invalid ISBN format.");
    }
  }

  @Override
  public String toString() {
    return value;
  }
}
