package com.bookmark.domain;

public record Password(String value) {
  @Override
  public String toString() {
    return value;
  }
}
