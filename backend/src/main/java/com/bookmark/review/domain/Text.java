package com.bookmark.review.domain;

public record Text(String value) {
  @Override
  public String toString() {
    return value;
  }
}
