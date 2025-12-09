package com.bookmark.book_list_service.domain;

public record Description(String value) {
  public Description {
    if (null == value) {
      throw new IllegalArgumentException("The Book List Description Must Not Be Null");
    }
  }

  @Override
  public String toString() {
    return value;
  }
}
