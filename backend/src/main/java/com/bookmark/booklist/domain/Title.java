package com.bookmark.booklist.domain;

public record Title(String value) {
  public Title {
    if (null == value) {
      throw new IllegalArgumentException("The Book List Title Must Not Be Null");
    }

    if (value.isEmpty()) {
      throw new IllegalArgumentException("The Book List Title Must Not Be Empty");
    }
  }

  @Override
  public String toString() {
    return value;
  }
}
