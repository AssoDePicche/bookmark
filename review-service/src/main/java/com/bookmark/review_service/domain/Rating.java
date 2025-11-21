package com.bookmark.review_service.domain;

public record Rating(int value) {
  public Rating {
    if (value < 0 || value > 5) {
      var message = String.format("Rating Must Be An Integer Value Between Zero And Five");

      throw new IllegalArgumentException(message);
    }
  }

  @Override
  public String toString() {
    return String.valueOf(value);
  }
}
