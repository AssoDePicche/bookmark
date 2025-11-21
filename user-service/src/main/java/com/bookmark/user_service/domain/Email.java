package com.bookmark.user.domain;

public record Email(String value) {
  @Override
  public String toString() {
    return value;
  }
}
