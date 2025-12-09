package com.bookmark.catalog_service.domain;

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
