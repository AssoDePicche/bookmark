package com.bookmark.user.domain;

public record Password(String value) {
  @Override
  public String toString() {
    return value;
  }
}
