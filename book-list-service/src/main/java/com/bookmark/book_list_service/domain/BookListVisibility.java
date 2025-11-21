package com.bookmark.booklist.domain;

public enum BookListVisibility {
  FRIENDS,
  PRIVATE,
  PUBLIC;

  public static BookListVisibility from(String visibility) {
    return switch (visibility.toLowerCase()) {
      case "friends" -> FRIENDS;
      case "private" -> PRIVATE;
      case "public" -> PUBLIC;
      default -> throw new IllegalArgumentException(String.format("'%s' Is Not A Valid Book List Visibility", visibility));
    };
  }
}
