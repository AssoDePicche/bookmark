package com.bookmark.iam.domain;

public record Password(String value) {
  @Override
  public String toString() {
    return value;
  }
}
