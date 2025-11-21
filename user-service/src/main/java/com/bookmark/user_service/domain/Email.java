package com.bookmark.user_service.domain;

public record Email(String value) {
  @Override
  public String toString() {
    return value;
  }
}
