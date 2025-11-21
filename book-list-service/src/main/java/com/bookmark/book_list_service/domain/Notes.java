package com.bookmark.book_list_service.domain;

public record Notes(String value, boolean containSpoilers) {
  public Notes() {
    this("", false);
  }

  @Override
  public String toString() {
    return value;
  }
}
