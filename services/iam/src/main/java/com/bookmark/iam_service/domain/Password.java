package com.bookmark.iam_service.domain;

public record Password(String value) {
  @Override
  public String toString() {
    return value;
  }
}
