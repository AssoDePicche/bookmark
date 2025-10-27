package com.bookmark.domain;

public record Email(String value) {
  @Override
  public String toString() {
    return value;
  }
}
