package com.bookmark.booklist.domain;

public record Notes(String value, boolean containSpoilers) {
  public Notes() {
    this("", false);
  }

  @Override
  public String toString() {
    return value;
  }
}
