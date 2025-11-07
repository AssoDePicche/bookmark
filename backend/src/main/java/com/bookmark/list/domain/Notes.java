package com.bookmark.list.domain;

public record Notes(String value, boolean containSpoilers) {
  public Notes() {
    this("", false);
  }

  @Override
  public String toString() {
    return value;
  }
}
