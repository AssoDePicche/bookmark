package com.bookmark.catalog_service.domain;

public record Isbn(String value) {
  public Isbn {
    if (value.isEmpty()) {
      throw new IllegalArgumentException("Invalid ISBN format.");
    }
  }

  @Override
  public String toString() {
    return value;
  }
}
