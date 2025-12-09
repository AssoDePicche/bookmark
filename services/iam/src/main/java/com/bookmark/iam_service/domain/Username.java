package com.bookmark.iam_service.domain;

public record Username(String value) {
  public Username {
    if (null == value) {
      throw new IllegalArgumentException("Username Must Not Be Null");
    }

    if (value.isEmpty()) {
      throw new IllegalArgumentException("Username Must Not Be Blank");
    }
  }

  @Override
  public String toString() {
    return value;
  }
}
