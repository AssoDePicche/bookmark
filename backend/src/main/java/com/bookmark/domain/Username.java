package com.bookmark.domain;

public record Username(String value) {
  @Override
  public String toString() {
    return value;
  }
}
