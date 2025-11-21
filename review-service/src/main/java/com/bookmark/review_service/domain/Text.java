package com.bookmark.review_service.domain;

public record Text(String value) {
  @Override
  public String toString() {
    return value;
  }
}
